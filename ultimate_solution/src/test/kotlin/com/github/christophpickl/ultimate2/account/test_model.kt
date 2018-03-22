package com.github.christophpickl.ultimate2.account


fun Account.Companion.testInstance() = Account(
    id = 42,
    alias = "testAlias",
    balance = 1337,
    type = AccountType.CURRENT
)


fun AccountJpa.Companion.testInstance() = AccountJpa(
    id = 43,
    alias = "testAliaz",
    balance = 1338,
    type = AccountTypeJpa.SAVING
)

fun AccountJpa.Companion.unsavedTestInstance() = testInstance().copy(id = 0)
