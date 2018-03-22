# UltimateKotlinWorkshop2

follow up of UltimateKotlinWorkshop using spring framework 5 and boot 2

# TODO

what does jupiter junit gives us?
    https://github.com/sdeleuze/spring-boot-kotlin-demo/blob/master/src/test/kotlin/hello/ApplicationTests.kt



# New in Spring 5

See: https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0
```java
GenericApplicationContext context = new GenericApplicationContext();
context.registerBean(Foo.class);
context.registerBean(Bar.class, () -> new 
	Bar(context.getBean(Foo.class))
);
```

```kotlin
beans {
    bean<Foo>()
    bean { Bar(ref()) }
}
```
