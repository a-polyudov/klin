package me.mmt.klin.model

import kotlinx.serialization.Serializable

/**
 * @author a-polyudov
 */
@Serializable
data class Settings(
  //default value is for correct error in case then "name" is not present in settings.json
  val projects: List<Project> = emptyList(),
  val logoSizePx: Int = 40,
) {
  init {
    validate(projects.isNotEmpty()) {
      "Project list is empty"
    }
  }
}
