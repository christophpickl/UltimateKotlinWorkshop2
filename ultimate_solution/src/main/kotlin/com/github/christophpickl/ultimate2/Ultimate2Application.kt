package com.github.christophpickl.ultimate2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Ultimate2Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Ultimate2Application>(*args)
        }
    }
}
