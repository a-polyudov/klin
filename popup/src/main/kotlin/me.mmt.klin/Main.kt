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
        StorageSettingsProvider.read()
          ?.let(MainPageBuilder::build)
          ?: MainPageBuilder.emptyMainPage("Cannot load settings")
      } catch (e: ValidationException) {
        MainPageBuilder.emptyMainPage(e.message)
      } catch (e: Exception) {
        console.log("Error: ${e.message}")
        MainPageBuilder.emptyMainPage(null)
      }
    }
  }
}
