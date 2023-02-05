package me.mmt.klin.storage

import kotlinx.serialization.json.Json

internal val settingsMapper = Json {
  ignoreUnknownKeys = true
  coerceInputValues = true //for avoid uncatchable MissingFieldException
}
