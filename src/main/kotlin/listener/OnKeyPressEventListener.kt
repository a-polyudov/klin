package listener

import model.Project
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import kotlin.js.json

private external val chrome: dynamic

/**
 * @author a-polyudov
 */
class OnKeyPressEventListener(
  private val project: Project,
  override val element: HTMLInputElement,
) : EventListener<HTMLInputElement> {
  override val type = "keypress"

  override fun handle(event: Event) {
    when ((event as KeyboardEvent).key) {
      "Enter" -> {
        if (element.value.isNotEmpty()) {
          chrome.tabs.create(
            json(
              "url" to project.buildUrl(element.value),
              "active" to !event.shiftKey
            )
          )
        } else {
          element.setCustomValidity("Invalid key")
        }
      }

      else -> event.key.toLongOrNull()
        ?.let { element.setCustomValidity("") }
        ?: run {
          element.setCustomValidity("Invalid key")
          event.preventDefault()
        }
    }
  }
}
