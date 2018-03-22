import org.gradle.api.plugins.*
import org.gradle.script.lang.kotlin.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mainClass = "com.github.christophpickl.ultimate2.Ultimate2Application"
val versionJava = "1.8"

version = "1.0.0-SNAPSHOT"
group = "com.github.christophpickl.ultimate2"

plugins {

    val versionKotlin = "1.2.30"
    val versionSpringBoot = "2.0.0.RELEASE"
    val versionSpringIoMgmt = "1.0.4.RELEASE"

    application
    // java
    // kotlin("jvm") version versionKotlin
    id("org.jetbrains.kotlin.jvm") version versionKotlin
    id("org.jetbrains.kotlin.plugin.spring") version versionKotlin
    id("org.jetbrains.kotlin.plugin.jpa") version versionKotlin

    id("org.springframework.boot") version versionSpringBoot
    id("io.spring.dependency-management") version versionSpringIoMgmt
}

// DEPENDENCIES
// =====================================================================================================================

dependencies {
    compile(kotlin("stdlib-jdk8")) // compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile(kotlin("reflect")) // compile("org.jetbrains.kotlin:kotlin-reflect")

    compile("org.springframework.boot:spring-boot-starter")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("io.github.microutils:kotlin-logging:1.4.9")
    /*
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("com.h2database:h2")
     */

    testCompile("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
    jcenter()
}

// BUILD
// =====================================================================================================================

application {
    mainClassName = mainClass
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// kotlin {}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = versionJava
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
