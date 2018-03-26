https://dzone.com/articles/whats-new-in-spring-framework-5

# Web Functional API with Kotlin

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

## Function Bean Registration

```kotlin
val context = GenericApplicationContext {
registerBean()
registerBean { Cinema(it.getBean()) }

}
```
