package com.github.christophpickl.ultimate2.account

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

    private val anyAlias = "anyAlias"

    @Test
    fun `Given account with some alias When find by alias Then return that account`() {
        val saved = em.persist(AccountJpa.unsavedTestInstance().copy(alias = anyAlias))

        val found = repo.findByAlias(anyAlias)

        assertThat(found).containsExactly(saved)
    }

}
