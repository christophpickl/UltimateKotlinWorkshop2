package com.github.christophpickl.ultimatesteps.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AccountControllerTest {

    @Autowired
    private lateinit var rest: TestRestTemplate
    @Autowired
    private lateinit var repo: AccountRepository

    private val anyAccount = Account.testInstance()
    private val accountsPath = "/accounts"
    private val accountsUri = URI.create(accountsPath)
    private val anyId = 42L
    private val notExistingId = 43L

    @Test
    fun `GET accounts - Then return 200`() {
        val response = rest.exchange<List<Account>>(RequestEntity.get(accountsUri).build())

        assertThat(response.statusCodeValue).isEqualTo(200)
    }

    @Test
    fun `GET accounts - Then return empty list`() {
        val response = rest.exchangeGet<List<Account>>(accountsPath)

        assertThat(response.body).isEqualTo(emptyList<Account>())
    }

    @Test
    fun `GET accounts - Given an existing account Then return that account`() {
        val savedAccount = saveAccount { copy(id = anyId) }

        val response = rest.exchangeGet<List<Account>>(accountsPath)

        assertThat(response.body).containsExactly(savedAccount)
    }

    @Test
    fun `GET account - When GET non-existing account by its ID Then return 404`() {
        val response = rest.exchangeGet<Any>("$accountsPath/$notExistingId")

        assertThat(response.statusCodeValue).isEqualTo(404)
    }

    @Test
    fun `GET account - Given an existing account When GET that account by its ID Then return that account`() {
        val savedAccount = saveAccount()

        val response = rest.exchangeGet<Account>("$accountsPath/${savedAccount.id}")

        assertThat(response.body).isEqualTo(savedAccount)
    }

    @Test
    fun `POST account - Then return that account with new ID and persist it in the database`() {
        val response = rest.exchange<Account>(
                RequestEntity.post(accountsUri).body(anyAccount))

        val expectedAccount = anyAccount.copy(id = response.body!!.id)
        assertThat(response.body).isEqualTo(expectedAccount)
        savedAccountsContainsExactly(expectedAccount)
    }

    private fun savedAccountsContainsExactly(account: Account) {
        assertThat(repo.findAll()).containsExactly(account.toAccountJpa())
    }

    private fun saveAccount(letAccount: Account.() -> Account = { this }): Account =
            anyAccount.let {
                val accountToSave = letAccount(it)
                repo.save(accountToSave.toAccountJpa()).toAccount()
            }

    private inline fun <reified T : Any> TestRestTemplate.exchangeGet(url: String): ResponseEntity<T> =
            exchange(RequestEntity.get(URI.create(url)).build())

}
