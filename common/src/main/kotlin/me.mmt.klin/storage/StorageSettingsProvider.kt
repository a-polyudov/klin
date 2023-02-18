package me.mmt.klin.storage

import kotlinx.coroutines.await
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromDynamic
import kotlinx.serialization.json.encodeToDynamic
import me.mmt.klin.model.Settings

/**
 * @author a-polyudov
 */
@OptIn(ExperimentalSerializationApi::class)
object StorageSettingsProvider {

  fun write(settings: Settings) {
    val dataToWrite = js("{}")
    dataToWrite["settings"] = settingsMapper.encodeToDynamic(settings)
    chrome.storage.local.set(dataToWrite)
  }

  suspend fun read(): Settings? {
    val storageData = chrome.storage.local.get("settings").await()
    val storageSettingsJs = storageData["settings"]
    return if (storageSettingsJs == null) null
    else settingsMapper.decodeFromDynamic(Settings.serializer(), storageSettingsJs)
  }
}
