plugins {
    id("java")
    kotlin("jvm") version "1.9.22"
    id("application")
    id("org.javamodularity.moduleplugin").version("1.8.12")
    id("org.openjfx.javafxplugin").version("0.0.13")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.yaml:snakeyaml:2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

application {
    mainModule = "com.motompro.mcblender.app"
    mainClass = "com.motompro.mcblender.app.MCBlender"
}

tasks {
    withType<ProcessResources> {
        val versionFile = File(rootDir, "version.txt")
        versionFile.delete()
        versionFile.createNewFile()
        versionFile.writeText("${rootProject.version}")
        from(versionFile)
    }
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    java.sourceSets
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

javafx {
    version = "17.0.6"
    modules = listOf("javafx.controls", "javafx.fxml")
}
