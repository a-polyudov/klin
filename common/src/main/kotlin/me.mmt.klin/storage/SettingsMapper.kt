package me.mmt.klin.storage

import kotlinx.serialization.json.Json

/**
 * @author a-polyudov
 */
internal val settingsMapper = Json {
  ignoreUnknownKeys = true
  coerceInputValues = true //for avoid uncatchable MissingFieldException
}
