plugins {
  kotlin("js") version "1.8.10"
  kotlin("plugin.serialization") version "1.8.10"
  id("io.gitlab.arturbosch.detekt") version "1.22.0"
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
  implementation("org.jetbrains.kotlin:kotlin-stdlib-js:1.8.10")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}


kotlin {
  js(IR) {
    binaries.executable()
    browser {
      commonWebpackConfig {
        cssSupport {
          enabled.set(true)
        }
        outputFileName = "popup.js"
      }
    }
  }
}

tasks {
  val releaseDirPath = "${rootDir.toPath()}/klin"

  val clearKlinDir by registering(Delete::class) {
    group = "build"
    delete(releaseDirPath)
  }

  named("clean") {
    dependsOn(clearKlinDir)
  }

  @Suppress("UNUSED_VARIABLE")
  val klin by registering {
    group = "build"
    dependsOn("detekt", "assemble", "test")
    doLast {
      val releaseDir = File(releaseDirPath).also {
        delete(it)
      }
      File("${buildDir.toPath()}/distributions").copyRecursively(releaseDir)
    }
  }
}

detekt {
  config = files("${rootDir.toPath()}/config/detekt/detekt.yml")
  autoCorrect = true
}
