package me.mmt.klin

import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.mmt.klin.model.Settings
import me.mmt.klin.model.ValidationException
import me.mmt.klin.storage.StorageSettingsProvider
import me.mmt.klin.ui.MainPageBuilder

private val mapper = Json {
  ignoreUnknownKeys = true
  coerceInputValues = true //for avoid uncatchable MissingFieldException
}

/**
 * @author a-polyudov
 */
@Suppress("TooGenericExceptionCaught")
fun main() {
  window.onload = {
    MainScope().launch {
      try {
        loadSettings()
          .also { StorageSettingsProvider.write(it) }
          .let(MainPageBuilder::build)
      } catch (e: ValidationException) {
        MainPageBuilder.emptyMainPage(e.message)
      } catch (e: Exception) {
        console.log("Error: ${e.message}")
        MainPageBuilder.emptyMainPage(null)
      }
    }
  }
}


/**
 * @author a-polyudov
 */
private suspend fun loadSettings(): Settings =
  window.fetch("./settings.json")
    .await()
    .text()
    .await()
    .let(mapper::decodeFromString)
