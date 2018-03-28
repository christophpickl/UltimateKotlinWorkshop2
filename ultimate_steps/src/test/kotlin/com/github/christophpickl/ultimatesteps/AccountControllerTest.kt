package com.github.christophpickl.ultimatesteps

import com.github.christophpickl.ultimatesteps.account.Account
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @Autowired
    private lateinit var rest: TestRestTemplate

    private val accountsPath = "/accounts"
    private val accountsUri = URI.create(accountsPath)

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

    private inline fun <reified T : Any> TestRestTemplate.exchangeGet(url: String): ResponseEntity<T> =
            exchange(RequestEntity.get(URI.create(url)).build())

}
