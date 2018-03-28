# About

Follow up of the original [Ultimate Kotlin Workshop](https://github.com/christophpickl/UltimateKotlinWorkshop/) 
but using the latest Spring Framework 5 and Spring Boot 2 versions.

## Further reading

* https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/languages.html#languages
* https://docs.spring.io/spring-framework/docs/5.0.4.RELEASE/kdoc-api/spring-framework/
* https://spring.io/blog/2017/08/01/spring-framework-5-kotlin-apis-the-functional-way
* https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0
* https://dzone.com/articles/whats-new-in-spring-framework-5

## Moar topics

* Processing query parameters
* Processing header parameters
* Domain specific argument resolvers
* Domain specific exception handlers
* Logging with Spring-AOP

## News

### General

* Upgrade to JDK 8 (initially wanted to upgrade to 9)
    * Access method parameters
    * Nullable annotations
    * Autodetects and bridges logging to SLF4J
* Functional stuff (function web endpoints/routing)
* Reactive Strams (WebFlux)
* Supports Junit 5 (Jupiter)
* Official support for Kotlin

### Kotlin specific

* Making use of Kotlin's reified type parameters (https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters)
    * RestClient (and WebClient) use them to simplify exchange (no more annoying TypeReference)
* Lots of new extension functions
    * GenericApplicationContext.registerBean
* Easier boot startup via top level functions and generics:
    * OLD: `SpringApplication.run(Ultimate2Application::class.java, *args)`
    * NEW: `runApplication<Ultimate2Application>(*args)`
* Added standard annotations for nullness, and Kotlin detects those:
    * https://kotlinlang.org/docs/reference/java-interop.html#jsr-305-support
* infer whether a bean is optional or not based whether its type is optional (`@Inject foo: Foo?`)
* also: implicit set requiredness based on nullability: `@RequestParam optional: String?` and `@RequestParam mandatory: String`
* "a warning message will be logged if Jackson and Kotlin are detected without the Jackson Kotlin module present"
ATTENTION: "If you are using bean validation on classes with primary constructor properties, make sure to use annotation use-site targets (get)."
* "As of Spring Framework 4.3, classes with a single constructor have their parameters automatically autowired" => no @Inject constructor (like guice)

### Still to-do

* Still the final VS open hack via kotlin-spring / kotlin-allopen
* Still the 0-ctor hack for JPA via kotlin-jpa / kotlin-noarg
* Still need lateinit for e.g. injected properties (no `by spring()` feature yet)
