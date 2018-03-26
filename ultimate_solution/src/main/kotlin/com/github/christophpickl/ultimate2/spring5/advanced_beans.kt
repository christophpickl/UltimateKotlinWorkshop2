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

/*

### Kotlin bean definition DSL

The old (Java 8) way:

```java
GenericApplicationContext context = new GenericApplicationContext();
context.registerBean(Foo.class);
context.registerBean(Bar.class, () -> newBar(context.getBean(Foo.class)));
```

With Kotlin and some syntactic sugar + reified generics:
```kotlin
val context = GenericApplicationContext().apply {
    registerBean<Foo>()
    registerBean { Bar(it.getBean<Foo>()) }
}
```

Now with a DSL:
```kotlin
beans {
    bean<Foo>()
    bean { Bar(ref()) }
}
```

BUT: "Spring Boot is based on Java Config and does not provide specific support for functional bean definition yet, but ..."
https://stackoverflow.com/questions/45935931/how-to-use-functional-bean-definition-kotlin-dsl-with-spring-boot-and-spring-w/46033685#46033685

 */
