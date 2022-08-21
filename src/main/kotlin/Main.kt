import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import model.Settings
import ui.MainPageBuilder

/**
 * @author a-polyudov
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
 * @author a-polyudov
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
