package listener

import kotlinx.browser.window
import model.Project
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

/**
 * @author poludov
 */
class OnKeyPressEventListener(
  private val project: Project,
  override val input: HTMLInputElement,
) : EventListener<HTMLInputElement> {
  override val type = "keypress"

  override fun handle(event: Event) {
    event as KeyboardEvent

    if (event.key == "Enter" && input.value.isNotEmpty()) {
      window.open(project.buildUrl(input.value), "_blank")
      event.preventDefault()
      return
    }
    val newValue = event.key.toLongOrNull()
    if (newValue == null) {
      input.setCustomValidity("Invalid key")
      event.preventDefault()
      return
    }
    input.setCustomValidity("")
  }
}
