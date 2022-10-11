group = "tech.poludov"
version = "0.1-SNAPSHOT"

plugins {
  kotlin("js") version "1.7.10" apply false
  kotlin("plugin.serialization") version "1.7.10" apply false
  id("io.gitlab.arturbosch.detekt") version "1.21.0" apply false
}

allprojects {
  repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
  }
}
