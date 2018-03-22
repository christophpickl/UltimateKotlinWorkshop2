package com.github.christophpickl.ultimate2.account

import org.springframework.stereotype.Service

@Service
class AccountService(
    private val repo: AccountRepository
) {

    fun readAccounts() =
        repo.findAll().map { it.toAccount() }

    fun readAccount(id: Long): Account? =
        repo.findById(id).let {
            if (it.isPresent) it.get().toAccount() else null
        }

    fun createAccount(account: Account) =
        repo.save(account.toAccountJpa().copy(id = 0)).toAccount()

}

fun AccountJpa.toAccount() = Account(id, alias, balance)
fun Account.toAccountJpa() = AccountJpa(id, alias, balance)
