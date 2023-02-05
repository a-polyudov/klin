allprojects {
  repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
  }
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
    val tasks = listOf("clean") + subprojectNames.map { ":${it}:assemble" }
    dependsOn(tasks)
    doLast {
      val releaseDir = File(releaseDirPath).also {
        delete(it)
      }
      File("${project(":common").buildDir.toPath()}/processedResources/js/main").copyRecursively(releaseDir)
      subprojectNames
        .filterNot { it == "common" }
        .onEach { File("${project(":${it}").buildDir.toPath()}/distributions").copyRecursively(releaseDir) }
    }
  }
}