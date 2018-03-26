package com.github.christophpickl.ultimate2.account

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts", produces = [MediaType.APPLICATION_JSON_VALUE])
class AccountController(
        private val service: AccountService
) {

    @GetMapping
    fun getAccounts(
            @RequestParam("alias")
            searchAlias: String?
    ): List<Account> = service.readAccounts(searchAlias)

    @GetMapping(path = ["/{id}"])
    fun getAccount(
            @PathVariable
            id: Long
    ): ResponseEntity<Account> {
        val found = service.readAccount(id)
        return if (found != null) ResponseEntity.ok(found)
        else ResponseEntity.notFound().build()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postAccount(
            @RequestBody
            account: Account
    ): Account = service.createAccount(account)

}
