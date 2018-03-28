package com.github.christophpickl.ultimatesteps.account

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts", produces = [MediaType.APPLICATION_JSON_VALUE])
class AccountController(
        private val service: AccountService
) {

    @GetMapping
    fun getAccounts(): List<Account> = service.getAccounts()

}
