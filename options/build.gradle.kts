plugins {
  kotlin("js")
  kotlin("plugin.serialization")
  id("io.gitlab.arturbosch.detekt")
}

dependencies {
  testImplementation(kotlin("test"))
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-js:1.7.20")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
}

kotlin {
  js(IR) {
    binaries.executable()
    browser {
      commonWebpackConfig {
        cssSupport.enabled = true
        outputFileName = "options.js"
      }
    }
  }
}

detekt {
  config = files("${rootDir.toPath()}/config/detekt/detekt.yml")
  autoCorrect = true
}
