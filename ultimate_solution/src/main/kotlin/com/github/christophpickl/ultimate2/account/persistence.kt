package com.github.christophpickl.ultimate2.account

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class AccountJpa(

    @GenericGenerator(
        name = "accountSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "ACCOUNT_SEQUENCE"),
            Parameter(name = "initial_value", value = "1000"),
            Parameter(name = "increment_size", value = "1")
        ]
    )
    @Id
    @GeneratedValue(generator = "accountSequenceGenerator")

//    @Id @GeneratedValue
    val id: Long,
    val alias: String,
    val balance: Int
)

@Repository
interface AccountRepository : JpaRepository<AccountJpa, Long>
//interface AccountJpaRepository : CrudRepository<AccountJpa, Long> {
//    fun findByAlias(alias: String): Iterable<AccountJpa>
//}

