plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "org.jetbrains.plugins"
version = "1.3.2-SNAPSHOT"

fun propertiesInternal(key: String): String? {
    val value = project.findProperty(key)
    return value?.toString()
}

fun propertiesStrictBoolean(key: String): Boolean? {
    val value = propertiesInternal(key)
    return when (value?.trim()?.lowercase()) {
        null -> null
        "" -> null
        "true" -> true
        "false" -> false
        else -> throw IllegalArgumentException("Property ${key} must be 'true' or 'false'")
    }
}

val skipMirror: Boolean = propertiesStrictBoolean("skipMirror") ?: false

repositories {
    if (!skipMirror) {
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
    }
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.4")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("java"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
