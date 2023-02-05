package me.mmt.klin.listener

import me.mmt.klin.model.Project
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.clipboard.ClipboardEvent
import org.w3c.dom.events.Event

/**
 * @author a-polyudov
 */
class OnPasteEventListener(
  project: Project,
  override val element: HTMLInputElement,
) : EventListener<HTMLInputElement> {
  private val regex = Regex("(${project.name}-)?(\\d{1,${project.maxTaskNumberLength}})")

  override val type = "paste"

  override fun handle(event: Event) =
    (event as ClipboardEvent)
      .clipboardData
      ?.getData("Text")
      ?.trim()
      ?.let { regex.matchEntire(element.value + it) }
      ?.groupValues
      ?.get(2)
      ?.let { handleWithValue(event, it, true) }
      ?: run { handleWithValue(event, null, false) }

  private fun handleWithValue(event: ClipboardEvent, value: String?, isValid: Boolean) {
    value?.let { element.value = it }
    if (isValid) {
      element.setCustomValidity("")
      element.setSelectionRange(0, element.value.length)
    } else {
      element.setCustomValidity("Invalid key")
    }
    event.preventDefault()
  }
}
