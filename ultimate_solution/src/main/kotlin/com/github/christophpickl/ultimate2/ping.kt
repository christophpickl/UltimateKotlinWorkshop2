package com.github.christophpickl.ultimate2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Pong(
        val message: String
)

@RestController
@RequestMapping("/ping")
class PingController {

    @GetMapping(produces = ["text/plain"])
    fun pingPlain() = "pong"

    @GetMapping(produces = ["application/json"])
    fun pingJson() = Pong("pong")

}
