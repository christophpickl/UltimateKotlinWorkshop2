package com.github.christophpickl.ultimate2

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationListener {

    @EventListener
    fun handleContextRefresh(event: ContextRefreshedEvent) {
        println("Application started up.")
    }

}
