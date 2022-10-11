import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
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
    .let(mapper::decodeFromString)
