package model

import kotlinx.serialization.Serializable

/**
 * @author a-polyudov
 */
@Serializable
data class Settings(
    val projects: List<Project>,
    val logoSizePx: Int = 40,
)
