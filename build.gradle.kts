plugins {
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Use Kotlin 1.9.0 here
}

buildscript {
    repositories {
        google() // Google's Maven repository
        mavenCentral() // Central repository for other dependencies
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2") // Correct Android Gradle plugin version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0") // Kotlin version 1.9.0
        classpath("com.google.gms:google-services:4.3.10") // Firebase services version
    }
}

allprojects {
    // If needed, add other configurations for your project here
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
