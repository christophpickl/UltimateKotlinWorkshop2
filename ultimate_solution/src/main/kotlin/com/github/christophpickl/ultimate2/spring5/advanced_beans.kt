package com.github.christophpickl.ultimate2.spring5

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.getBean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans


class Simple
class NeedsAssistedInject(simple: Simple, someMagic: Int)
class Maybe {
    override fun toString() = "... nothing because i'm heeere"
}
class Dancer(
    @Qualifier("dbUrl") private val dbUrl: String,
    val maybe: Maybe?
) {
    fun dance() {
        println("Dancing to: $dbUrl loosing ${maybe ?: "IT :'-("}")
    }
}

object Spring5Demo {
    @JvmStatic
    fun main(args: Array<String>) {

        val magicAtRuntime = 42
        val demoBeans = beans {
            bean("dbUrl") { "jdbc://foobar" }

            bean<Simple>()
            bean<Dancer>()

            bean { NeedsAssistedInject(simple = ref(), someMagic = magicAtRuntime) }

            // -Dspring.profiles.active=myProfile
            profile("myProfile") {
                bean<Maybe>()
            }
        }

        val context = GenericApplicationContext().apply {
            demoBeans.initialize(this)
            refresh()
        }

        context.getBean<Dancer>().dance()
    }
}
