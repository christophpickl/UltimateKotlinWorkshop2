import org.gradle.api.plugins.*
import org.gradle.script.lang.kotlin.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

println("Hello build.gradle.kts")

object Versions {
    val kotlin = "1.2.30"
    val java = "1.8"
    val springBoot = "2.0.0.RELEASE"
    val springIoMgmt = "1.0.4.RELEASE"
}

plugins {
    val versionKotlin = "1.2.30"
    val versionSpringBoot = "2.0.0.RELEASE"
    val versionSpringIoMgmt = "1.0.4.RELEASE"

    // id("com.bmuschko.docker-java-application") version "3.1.0"
    // java
    application
//    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.kotlin.jvm") version versionKotlin
    id("org.jetbrains.kotlin.plugin.spring") version versionKotlin

    id("org.springframework.boot") version versionSpringBoot
    id("io.spring.dependency-management") version versionSpringIoMgmt

}

application {
    mainClassName = "com.github.christophpickl.ultimate2.Ultimate2Application"
}

dependencies {
    //    compile(kotlin("stdlib-jdk8"))
//    compile(kotlin("reflect"))
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")

    compile("org.springframework.boot:spring-boot-starter:${Versions.springBoot}")
    // testCompile("junit:junit:4.12")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
kotlin {

}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = Versions.java
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}
repositories {
    mavenCentral()
    jcenter()
}
