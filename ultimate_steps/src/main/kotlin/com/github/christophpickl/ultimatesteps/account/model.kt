package com.github.christophpickl.ultimatesteps.account

data class Account(
        val id: Long,
        val alias: String,
        val balance: Int,
        val type: AccountType
) {
    companion object
}

enum class AccountType {
    CURRENT,
    SAVING
}
