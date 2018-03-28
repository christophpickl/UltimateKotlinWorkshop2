package com.github.christophpickl.ultimatesteps.account

import org.springframework.stereotype.Service

@Service
class AccountService {

    val accountsById = mutableMapOf<Long, Account>()

    fun getAccounts(): List<Account> = accountsById.values.toList()
    fun getAccount(id: Long): Account? = accountsById[id]
    fun createAccount(account: Account): Account = account.also { accountsById[it.id] = it }

}
