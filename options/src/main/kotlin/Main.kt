import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.dom.append
import kotlinx.html.js.div


fun main() {
  window.onload = {
    MainScope().launch {
      document.body?.append
        ?.div {
          +"THIS IS OPTIONS PAGE"
        }
    }
  }
}
