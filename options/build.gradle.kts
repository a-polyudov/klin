plugins {
  kotlin("js")
  kotlin("plugin.serialization")
}

dependencies {
  implementation(project(":common"))
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")

  testImplementation(kotlin("test"))
}

kotlin {
  js(IR) {
    binaries.executable()
    browser {
      commonWebpackConfig {
        cssSupport {
          enabled.set(true)
        }
        outputFileName = "options.js"
      }
    }
  }
}
