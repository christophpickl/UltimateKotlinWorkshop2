package com.github.christophpickl.ultimate2

import mu.KotlinLogging
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationListener {

    private val log = KotlinLogging.logger {}

    @EventListener
    fun handleContextRefresh(event: ContextRefreshedEvent) {
        log.info { "Application started up." }
    }

}
