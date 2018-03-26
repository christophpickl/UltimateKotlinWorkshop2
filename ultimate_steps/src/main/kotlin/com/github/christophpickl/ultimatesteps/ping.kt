package com.github.christophpickl.ultimatesteps

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
class PingController {

    @GetMapping(produces = ["text/plain"])
    fun pingPlain() = "pong"

}
