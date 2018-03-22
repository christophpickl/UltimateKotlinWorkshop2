//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

println("executing build script")

plugins {
    application
    kotlin("jvm") version "1.2.30"
}

application {
    mainClassName = "sample.App"
}

dependencies {
    compile(kotlin("stdlib"))
}

repositories {
    jcenter()
}
