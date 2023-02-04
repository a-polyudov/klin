import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic
import kotlinx.serialization.json.encodeToDynamic
import model.Settings
import model.ValidationException
import ui.MainPageBuilder


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
          .let(MainPageBuilder::loadMainPage)
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
    .let<String, Settings>(mapper::decodeFromString)
    .also { testLocalStorage(it) }

@OptIn(ExperimentalSerializationApi::class)
private suspend fun testLocalStorage(settings: Settings) {
  console.log("SETTINGS: $settings")
  val dataToWrite = js("{}")
  dataToWrite["settings"] = mapper.encodeToDynamic(settings)

  chrome.storage.local.set(dataToWrite)

  val storageData = chrome.storage.local.get("settings").await()
  val storageSettingsJs = storageData["settings"]
  console.log("STORAGE: $storageSettingsJs")

  when (storageSettingsJs) {
    null -> {
      console.log("UNDEFINED")
    }

    else -> {
      console.log("NOT UNDEFINED")
      val result = mapper.decodeFromDynamic(Settings.serializer(), storageSettingsJs).toString()
      console.log("STORAGE SETTING: $result")
    }
  }
}

