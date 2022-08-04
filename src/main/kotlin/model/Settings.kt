package model

import kotlinx.serialization.Serializable

/**
 * @author poludov
 */
@Serializable
data class Settings(
  val projects: List<Project>,
)
