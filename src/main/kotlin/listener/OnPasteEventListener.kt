package listener

import model.Project
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.clipboard.ClipboardEvent
import org.w3c.dom.events.Event

/**
 * @author poludov
 */
class OnPasteEventListener(
  project: Project,
  override val input: HTMLInputElement,
) : EventListener<HTMLInputElement> {
  private val regex = Regex("(${project.name}-)?(\\d{${project.minTaskNumberLength},${project.maxTaskNumberLength}})")

  override val type = "paste"
  override fun handle(event: Event) =
    (event as ClipboardEvent)
      .also { console.log(regex.pattern) }
      .clipboardData
      ?.getData("Text")
      ?.let { regex.matchEntire(input.value + it) }
      ?.groupValues
      ?.get(2)
      ?.let { handleWithValue(event, it, true) }
      ?: run { handleWithValue(event, null, false) }

  private fun handleWithValue(event: ClipboardEvent, value: String?, isValid: Boolean) {
    value?.let { input.value = it }
    input.setCustomValidity(if (isValid) "" else "Invalid key")
    event.preventDefault()
  }
}
