package me.mmt.klin

import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.mmt.klin.model.ValidationException
import me.mmt.klin.storage.StorageSettingsProvider
import me.mmt.klin.ui.MainPageBuilder

/**
 * @author a-polyudov
 */
@Suppress("TooGenericExceptionCaught")
fun main() {
  window.onload = {
    MainScope().launch {
      try {
//        loadSettings()
//          .also { StorageSettingsProvider.write(it) }
        StorageSettingsProvider.read()
          ?.let(MainPageBuilder::build)
          ?: MainPageBuilder.emptyMainPage("Use settings to add projects")
      } catch (e: ValidationException) {
        MainPageBuilder.emptyMainPage(e.message)
      } catch (e: Exception) {
        console.log("Error: ${e.message}")
        MainPageBuilder.emptyMainPage(null)
      }
    }
  }
}
