import io.gitlab.arturbosch.detekt.Detekt

allprojects {
  repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
  }
}

plugins {
  id("io.gitlab.arturbosch.detekt").version("1.22.0")
}

tasks {
  val releaseDirPath = "${rootDir.toPath()}/klin"

  @Suppress("UNUSED_VARIABLE")
  val clearKlinDir by registering(Delete::class) {
    group = "build"
    delete(releaseDirPath)
  }

  @Suppress("UNUSED_VARIABLE")
  val klin by registering {
    group = "build"

    val subprojectNames = subprojects.map { it.name }
    dependsOn(listOf("detektAll", "clean") + subprojectNames.map { ":${it}:assemble" })
    doLast {
      val releaseDir = File(releaseDirPath).also {
        delete(it)
      }
      File("${project(":common").buildDir.toPath()}/processedResources/js/main")
        .copyRecursively(releaseDir)
      subprojectNames
        .filterNot { it == "common" }
        .onEach {
          File("${project(":${it}").buildDir.toPath()}/distributions")
            .copyRecursively(releaseDir)
        }
    }
  }

  @Suppress("UNUSED_VARIABLE")
  val detektAll by registering(Detekt::class) {
    parallel = true
    autoCorrect = true
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    config.setFrom(files("${rootDir.toPath()}/config/detekt/detekt.yml"))
  }
}
