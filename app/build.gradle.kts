/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.4/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    
    java
    checkstyle
    pmd
}


allprojects {
    apply(plugin = "checkstyle")
    apply(plugin = "pmd")

    tasks.withType<Test> {
        ignoreFailures = true
        useJUnitPlatform()
    }


    pmd {
        ruleSets = listOf()
        ruleSetConfig = resources.text.fromFile("${project.rootProject.projectDir}/config/pmd/pmd.xml")
        isIgnoreFailures = true
    }


    checkstyle {
        isIgnoreFailures = true
    }
}

buildscript {
    repositories {
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
}


repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics",
    "media"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

val javaFxVersion = "15.0.1"
dependencies {
// JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.2")
    implementation("com.thoughtworks.xstream:xstream:1.4.19")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("com.itextpdf:itext7-core:7.1.1")
    
}

application {
    // Define the main class for the application.
    mainClass.set("unieuroop.App")
}
