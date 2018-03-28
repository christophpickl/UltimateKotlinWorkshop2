package com.github.christophpickl.ultimatesteps.account

import com.github.christophpickl.ultimatesteps.account.AccountType.CURRENT

fun Account.Companion.testInstance() = Account(
        id = 42,
        alias = "testAlias",
        balance = 1337,
        type = CURRENT
)
