import org.jetbrains.kotlin.incremental.cleanDirectoryContents

plugins {
  kotlin("js") version "1.7.10"
  kotlin("plugin.serialization") version "1.7.10"
  id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

group = "tech.poludov"
version = "0.1-SNAPSHOT"

repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

dependencies {
  testImplementation(kotlin("test"))
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-js:1.7.10")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}

kotlin {
  js(IR) {
    binaries.executable()
    browser {
      commonWebpackConfig {
        cssSupport.enabled = true
        outputFileName = "popup.js"
      }
    }
  }
}

tasks {
  val releaseDirPath = "${rootDir.toPath()}/release"

  val clearReleaseDir by registering(Delete::class) {
    group = "build"
    delete(releaseDirPath)
  }

  named("clean") {
    dependsOn(clearReleaseDir)
  }

  @Suppress("UNUSED_VARIABLE")
  val release by registering {
    group = "build"
    dependsOn("detekt", "assemble", "test")
    doLast {
      val releaseDir = File(releaseDirPath).apply {
        cleanDirectoryContents()
      }
      File("${buildDir.toPath()}/distributions").copyRecursively(releaseDir)
    }
  }
}

detekt {
  config = files("${rootDir.toPath()}/config/detekt/detekt.yml")
  autoCorrect = true
}
