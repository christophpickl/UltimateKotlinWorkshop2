package com.github.christophpickl.ultimatesteps.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private lateinit var em: TestEntityManager

    @Autowired
    private lateinit var repo: AccountRepository

    @Test
    fun `Given an account When find by ID Then return that account`() {
        val saved = em.persist(AccountJpa.unsavedTestInstance())

        val found = repo.findById(saved.id)

        assertThat(found).hasValue(saved)
    }

}
