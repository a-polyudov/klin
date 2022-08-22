package listener

import kotlinx.browser.window
import model.Project
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

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
      "Enter" -> if (element.value.isNotEmpty()) {
        window.open(project.buildUrl(element.value), "_blank")
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
