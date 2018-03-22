package com.github.christophpickl.ultimate2.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.RequestEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@IntegrationTest
class AccountControllerTest {

    @Autowired
    private lateinit var rest: TestRestTemplate
    @Autowired
    private lateinit var repo: AccountRepository

    private val accountsPath = "/accounts"
    private val accountsUri = URI.create(accountsPath)
    private val anyAlias = "anyAlias"
    private val anyAccount = Account.testInstance().copy(id = 0)

    @Test
    fun `GET accounts - Then return 200`() {
        val response = rest.exchange<List<Account>>(RequestEntity.get(accountsUri).build())

        assertThat(response.statusCodeValue).isEqualTo(200)
    }

    @Test
    fun `GET accounts - Then return empty list`() {
        val response = rest.exchangeGet<List<Account>>(accountsPath)

        assertThat(response).isEqualTo(emptyList<Account>())
    }

    @Test
    fun `GET accounts - Given an existing account Then return that account`() {
        val savedAccount = saveAccount()

        val response = rest.exchangeGet<List<Account>>(accountsPath)

        assertThat(response).containsExactly(savedAccount)
    }

    @Test
    fun `GET accounts by alias - Given an two accounts with different alias Then return only one account`() {
        saveAccount { copy(alias = "$anyAlias-not") }
        val savedAccount = saveAccount { copy(alias = anyAlias) }

        val response = rest.exchangeGet<List<Account>>("$accountsPath/?alias=$anyAlias")

        assertThat(response).containsExactly(savedAccount)
    }

    @Test
    fun `GET account - Given an existing account When GET that account by its ID Then return that account`() {
        val savedAccount = saveAccount()

        val response = rest.exchangeGet<Account>("$accountsPath/${savedAccount.id}")

        assertThat(response).isEqualTo(savedAccount)
    }

    @Test
    fun `POST account - Then return that account with new ID and persist it in the database`() {
        val response = rest.exchange<Account>(
            RequestEntity.post(accountsUri).body(anyAccount))

        val expectedAccount = anyAccount.copy(id = response.body!!.id)
        assertThat(response.body).isEqualTo(expectedAccount)
        assertThat(repo.findAll()).containsExactly(expectedAccount.toAccountJpa())
    }

    private fun saveAccount(letAccount: Account.() -> Account = { this }): Account =
        Account.testInstance().let {
            repo.save(letAccount(it).copy(id = 0).toAccountJpa()).toAccount()
        }

    private inline fun <reified T : Any> TestRestTemplate.exchangeGet(url: String): T =
        exchange<T>(RequestEntity.get(URI.create(url)).build()).body!!

    @TestConfiguration
    class TestConfig {
        @Bean
        fun restTemplateBuilder() = RestTemplateBuilder()
            .setConnectTimeout(1000)
            .setReadTimeout(1000)!!

    }
}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IntegrationTest
