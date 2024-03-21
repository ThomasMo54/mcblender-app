plugins {
  id 'java'
  id 'application'
  id 'org.jetbrains.kotlin.jvm' version '1.8.22'
  id 'org.javamodularity.moduleplugin' version '1.8.12'
  id 'org.openjfx.javafxplugin' version '0.0.13'
  id 'org.beryx.jlink' version '2.25.0'
}

group 'com.motompro.mcblender'
version '1.0-SNAPSHOT'

repositories {
  mavenCentral()
}

ext {
  junitVersion = '5.10.0'
}


tasks.withType(JavaCompile) {
  options.encoding = 'UTF-8'
}

application {
  mainModule = 'com.motompro.mcblender.app'
  mainClass = 'com.motompro.mcblender.app.HelloApplication'
}
kotlin {
  jvmToolchain( 17 )
}

javafx {
  version = '17.0.6'
  modules = ['javafx.controls', 'javafx.fxml']
}

dependencies {

  testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}

test {
useJUnitPlatform()}

jlink {
  imageZip = project.file("${buildDir}/distributions/app-${javafx.platform.classifier}.zip")
  options = ['--strip-debug', '--compress', '2', '--no-header-files', '--no-man-pages']
  launcher {
    name = 'app'
  }
}

jlinkZip {
  group = 'distribution'
}