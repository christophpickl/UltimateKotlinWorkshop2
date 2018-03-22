package com.github.christophpickl.ultimate2.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class AccountJpa(
    @Id @GeneratedValue
    val id: Long,
    val alias: String,
    val balance: Int,
    val type: AccountTypeJpa
)

enum class AccountTypeJpa {
    CURRENT,
    SAVING
}

@Repository
interface AccountRepository : JpaRepository<AccountJpa, Long> {
    fun findByAlias(alias: String): List<AccountJpa>
}
