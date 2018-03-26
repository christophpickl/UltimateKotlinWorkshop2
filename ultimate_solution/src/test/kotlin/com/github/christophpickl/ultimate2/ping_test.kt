package com.github.christophpickl.ultimate2

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PingTest {

    @Autowired
    private lateinit var rest: TestRestTemplate

    @Test
    fun `When GET ping Then pong text returned`() {
        val response = rest.exchange(
                RequestEntity.get(URI.create("/ping")).build(),
                String::class.java)

        assertThat(response.body).isEqualTo("pong")
    }

    @Test
    fun `When GET ping accepting JSON Then JSON payload is returned`() {
        val response = rest.exchange(
                RequestEntity.get(URI.create("/ping"))
                        .header("accept", "application/json")
                        .build(),
                String::class.java
        )

        assertThat(response.body).jsisEqualTo("""{"message":"pong"}""")
    }

}
