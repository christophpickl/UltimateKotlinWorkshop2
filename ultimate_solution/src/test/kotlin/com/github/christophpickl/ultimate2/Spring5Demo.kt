package com.github.christophpickl.ultimate2

import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

class Foo
class Bar(foo: Foo)

// See: https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0
object Spring5Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        beans {
            bean<Foo>()
            bean { Bar(ref()) }
        }
//        GenericApplicationContext context = new GenericApplicationContext();
//        context.registerBean(Foo.class);
//        context.registerBean(Bar.class, () -> newBar(context.getBean(Foo.class)));
    }
}
