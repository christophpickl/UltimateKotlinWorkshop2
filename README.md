# About

Follow up of the original [Ultimate Kotlin Workshop](https://github.com/christophpickl/UltimateKotlinWorkshop/) 
but using the latest Spring Framework 5 and Spring Boot 2 versions.

Have a look at my personal [presentation_notes.pdf](https://github.com/christophpickl/UltimateKotlinWorkshop2/blob/master/presentation_notes.pdf) which will walk you through the whole workshop and get your very own Spring Boot enabled ReSTful webservice up and running :)

For pre-solved solutions, open up the [ultimate_solution/](https://github.com/christophpickl/UltimateKotlinWorkshop2/tree/master/ultimate_solution) folder which contains the full fledged solution. Or if you prefer something more slim, have a look into the [ultimate_steps/](https://github.com/christophpickl/UltimateKotlinWorkshop2/tree/master/ultimate_steps) directory.

Happy koding, yours

Christoph :heart:

# Appendix

## Spring News

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

## Further reading

* https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/languages.html#languages
* https://docs.spring.io/spring-framework/docs/5.0.4.RELEASE/kdoc-api/spring-framework/
* https://spring.io/blog/2017/08/01/spring-framework-5-kotlin-apis-the-functional-way
* https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0
* https://dzone.com/articles/whats-new-in-spring-framework-5

## Moar (uncovered) topics

* Processing query parameters
* Processing header parameters
* Domain specific argument resolvers
* Domain specific exception handlers
* Logging with Spring-AOP


### Web Functional API with Kotlin

```kotlin
{
    ("/movie" and accept(TEXT_HTML)).nest {
        GET("/", movieHandler::findAllView)
        GET("/{card}", movieHandler::findOneView)
    }
    ("/api/movie" and accept(APPLICATION_JSON)).nest {
        GET("/", movieApiHandler::findAll)
        GET("/{id}", movieApiHandler::findOne)
    }
}
```

### Function Bean Registration

```kotlin
val context = GenericApplicationContext {
registerBean()
registerBean { Cinema(it.getBean()) }

}
```
