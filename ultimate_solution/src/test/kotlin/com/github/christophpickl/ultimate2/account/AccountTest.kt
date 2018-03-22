package com.github.christophpickl.ultimate2.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.RequestEntity
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import javax.annotation.PostConstruct

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AccountTest {

    @Autowired
    private lateinit var rest: TestRestTemplate
    @Autowired
    private lateinit var repo: AccountRepository

    private val anyAccount = Account(0, "alias", 42)

    @PostConstruct
    fun initRestTemplate() {
        rest.restTemplate.requestFactory = SimpleClientHttpRequestFactory().apply {
            // disable HTTP retry (fails when returning 401)
            setOutputStreaming(false)
        }
    }

    @Test
    fun `READ - When GET accounts without username Then return 401 Unauthorized`() {
        val response = rest.exchange<String>(RequestEntity.get(URI.create("/accounts")).build())

//        rest.getForObject<String>(URI.create(""))
        assertThat(response.statusCodeValue).isEqualTo(401)
    }

    @Test
    fun `READ - When GET accounts with wrong username Then return 401 Unauthorized`() {
        val response = rest.exchange<String>(
            RequestEntity.get(URI.create("/accounts"))
                .header("X-ultimate_username", "wrongUsername")
                .build())

        assertThat(response.statusCodeValue).isEqualTo(401)
    }

    @Test
    fun `READ - When GET accounts as customer Then return 200 OK`() {
        val response = rest.exchange<List<Account>>(
            RequestEntity.get(URI.create("/accounts"))
                .header("X-ultimate_username", "customer")
                .build())

        assertThat(response.statusCodeValue).isEqualTo(200)
    }

    @Test
    fun `READ - When GET accounts as customer Then return empty list`() {
        val response = rest.exchange<List<Account>>(
            RequestEntity.get(URI.create("/accounts"))
                .header("X-ultimate_username", "customer")
                .build())

        assertThat(response.body).isEqualTo(emptyList<Account>())
    }

    @Test
    fun `READ - Given an existing account When GET accounts as customer Then return that account`() {
        val account = repo.save(Account(0, "alias", 42).toAccountJpa()).toAccount()

        val response = rest.exchange<List<Account>>(
            RequestEntity.get(URI.create("/accounts"))
                .header("X-ultimate_username", "customer")
                .build())

        assertThat(response.body).containsExactly(account)
    }

    @Test
    fun `CREATE - When POST account without username Then return 401 Unauthorized`() {
        val response = rest.exchange<String>(RequestEntity.post(URI.create("/accounts")).body(anyAccount))

        assertThat(response.statusCodeValue).isEqualTo(401)
    }

    @Test
    fun `CREATE - When POST account with wrong username Then return 401 Unauthorized`() {
        val response = rest.exchange<String>(
            RequestEntity.post(URI.create("/accounts"))
                .header("X-ultimate_username", "wrongUsername")
                .body(anyAccount))

        assertThat(response.statusCodeValue).isEqualTo(401)
    }

    @Test
    fun `CREATE - When POST an account as customer Then return that account and persist it in the database`() {
        val account = Account(0, "alias", 42)

        val response = rest.exchange<Account>(RequestEntity.post(URI.create("/accounts"))
            .header("X-ultimate_username", "customer")
            .body(account))

        val expectedAccount = account.copy(id = response.body!!.id)
        assertThat(response.body).isEqualTo(expectedAccount)
        assertThat(repo.findAll()).containsExactly(expectedAccount.toAccountJpa())
    }

}

inline fun <reified O> TestRestTemplate.exchange(request: RequestEntity<*>) =
    exchange(request, object : ParameterizedTypeReference<O>() {})!!
