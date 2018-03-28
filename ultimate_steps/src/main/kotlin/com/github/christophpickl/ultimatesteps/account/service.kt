package com.github.christophpickl.ultimatesteps.account

import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(
        private val repo: AccountRepository
) {

    fun getAccounts(): List<Account> = repo.findAll().map { it.toAccount() }
    fun getAccount(id: Long): Account? = repo.findById(id).unwrap()?.toAccount()
    fun createAccount(account: Account): Account = repo.save(account.toAccountJpa().copy(id = 0)).toAccount()

}

fun <T> Optional<T>.unwrap(): T? = if (isPresent) get() else null

fun AccountJpa.toAccount() = Account(id, alias, balance, type.toAccountType())
fun Account.toAccountJpa() = AccountJpa(id, alias, balance, type.toAccountTypeJpa())

fun AccountTypeJpa.toAccountType() = when (this) {
    AccountTypeJpa.CURRENT -> AccountType.CURRENT
    AccountTypeJpa.SAVING -> AccountType.SAVING
}

fun AccountType.toAccountTypeJpa() = when (this) {
    AccountType.CURRENT -> AccountTypeJpa.CURRENT
    AccountType.SAVING -> AccountTypeJpa.SAVING
}
