@file:Suppress("UNUSED_PARAMETER")

package com.github.christophpickl.ultimate2

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.getBean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans


// https://spring.io/blog/2017/08/01/spring-framework-5-kotlin-apis-the-functional-way
//class Application {
//
//    private val httpHandler: HttpHandler
//    private val server: HttpServer
//    private var nettyContext: BlockingNettyContext? = null
//
//    constructor(port: Int = 8080) {
//        val context = GenericApplicationContext().apply {
//            beans().initialize(this)
//            refresh()
//        }
//        server = HttpServer.create(port)
//        httpHandler = WebHttpHandlerBuilder.applicationContext(context).build()
//    }
//
//    fun start() {
//        nettyContext = server.start(ReactorHttpHandlerAdapter(httpHandler))
//    }
//
//    fun startAndAwait() {
//        server.startAndAwait(ReactorHttpHandlerAdapter(httpHandler),
//            { nettyContext = it })
//    }
//
//    fun stop() {
//        nettyContext?.shutdown()
//    }
//}
//
//fun main(args: Array<String>) {
//    Application().startAndAwait()
//}


