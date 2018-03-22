package com.github.christophpickl.ultimate2.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.RequestEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AccountTest {

    @Autowired
    private lateinit var rest: TestRestTemplate
    @Autowired
    private lateinit var repo: AccountRepository

    private val accountsPath = "/accounts"
    private val accountsUri = URI.create(accountsPath)
    private val anyAccount = Account(0, "alias", 42)

    @Test
    fun `READ - When GET accounts Then return 200 and empty list`() {
        val response = rest.exchange<List<Account>>(
            RequestEntity.get(accountsUri).build())

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(response.body).isEqualTo(emptyList<Account>())
    }

    @Test
    fun `READ - Given an existing account When GET accounts Then return that account`() {
        val savedAccount = repo.save(anyAccount.toAccountJpa()).toAccount()

        val response = rest.exchange<List<Account>>(
            RequestEntity.get(accountsUri).build())
        assertThat(response.body).containsExactly(savedAccount)
    }

    @Test
    fun `READ - Given an existing account When GET that account by its ID Then return that account`() {
        val savedAccount = repo.save(anyAccount.toAccountJpa()).toAccount()

        val response = rest.exchange<Account>(
            RequestEntity.get(URI.create("$accountsPath/${savedAccount.id}")).build())

        assertThat(response.body).isEqualTo(savedAccount)
    }

    @Test
    fun `CREATE - When POST an account Then return that account with new ID and persist it in the database`() {
        val response = rest.exchange<Account>(
            RequestEntity.post(accountsUri).body(anyAccount))

        val expectedAccount = anyAccount.copy(id = response.body!!.id)
        assertThat(response.body).isEqualTo(expectedAccount)
        assertThat(repo.findAll()).containsExactly(expectedAccount.toAccountJpa())
    }

}
