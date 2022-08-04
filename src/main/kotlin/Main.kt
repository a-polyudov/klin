import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import model.Settings
import ui.MainPageBuilder

/**
 * @author poludov
 */
fun main() {
  window.onload = {
    MainScope().launch {
      loadSettings()
        ?.let(MainPageBuilder::loadMainPage)
        ?: MainPageBuilder.emptyMainPage()
    }
  }
}

/**
 * @author poludov
 */
private suspend fun loadSettings(): Settings? =
  try {
    window.fetch("./settings.json")
      .await()
      .text()
      .await()
      .let(Json::decodeFromString)
  } catch (e: dynamic) {
    console.error(e)
    null
  }
