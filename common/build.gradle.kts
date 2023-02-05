plugins {
  kotlin("js")
  kotlin("plugin.serialization")
}

dependencies{
  implementation("org.jetbrains.kotlin:kotlin-stdlib-js:1.8.10")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")
}

kotlin {
  js(IR) {
    binaries.library()
    browser()
  }
}