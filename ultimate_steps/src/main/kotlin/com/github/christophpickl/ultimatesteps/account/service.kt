package com.github.christophpickl.ultimatesteps.account

import org.springframework.stereotype.Service

@Service
class AccountService {

    val accountsById = mutableMapOf<Long, Account>()

    fun getAccounts(): List<Account> = accountsById.values.toList()

}
