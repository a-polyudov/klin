rootProject.name = "klin"

include(
  "common",
  "options",
  "popup"
)

pluginManagement {
  val kotlinVersion: String by settings

  plugins {
    kotlin("js") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
  }
}
