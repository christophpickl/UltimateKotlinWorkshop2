
## Readmes

* https://spring.io/blog/2017/08/01/spring-framework-5-kotlin-apis-the-functional-way
* !!! Official doc for Spring and Kotlin: https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/languages.html#languages
* KDoc Spring: https://docs.spring.io/spring-framework/docs/5.0.4.RELEASE/kdoc-api/spring-framework/
* Nice blog: https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0

## Ad presentation


## TODO 

* write presentation notes (code samples, step by step)
* find more spring5 news

* use JUNIT 5: https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/languages.html#testing
* read nice other workshop: https://github.com/sdeleuze/spring-kotlin-deepdive
* read https://github.com/sdeleuze/spring-kotlin-functional


## New Stuff in Spring 5 for Kotlin

* Making use of Kotlin's reified type parameters (https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters)
    * RestClient (and WebClient) use them to simplify exchange (no more annoying TypeReference)
* Lots of new extension functions
    * GenericApplicationContext.registerBean
    * ... TODO: more!!!
* Easier boot startup:
    * OLD: `SpringApplication.run(Ultimate2Application::class.java, *args)`
    * NEW: `runApplication<Ultimate2Application>(*args)`
* As Spring added standard annotation for nullness, Kotlin detects and incorporates them in its type system
    * https://kotlinlang.org/docs/reference/java-interop.html#jsr-305-support
* infer whether a bean is optional or not based whether its type is optional (`@Inject foo: Foo?`)
    * also: implicit set requiredness based on nullability: `@RequestParam optional: String?` and `@RequestParam mandatory: String`
*  "a warning message will be logged if Jackson and Kotlin are detected without the Jackson Kotlin module present"
* ATTENTION: "If you are using bean validation on classes with primary constructor properties, make sure to use annotation use-site targets (get)."
* "As of Spring Framework 4.3, classes with a single constructor have their parameters automatically autowired" => no @Inject constructor (like guice)
* change syntax of injected properties: `@Value("${property}")` => `@Value("%{property}")` by reconfiguring the `PropertySourcesPlaceholderConfigurer`
* MINOR gap with "annotation array attributes": java is dealing single elements as array whereas in kotlin need to be wrapped with [], ...

### Old stuff

* Still the final VS open hack via `kotlin-spring` / `kotlin-allopen`
* Still the 0-ctor hack for JPA via `kotlin-jpa` / `kotlin-noarg`
* Still need `lateinit` for e.g. injected properties (no `by spring()` feature yet)

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
