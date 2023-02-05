plugins {
  kotlin("js")
  kotlin("plugin.serialization")
}

dependencies {
  implementation(project(":common"))

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
        outputFileName = "popup.js"
      }
    }
  }
}