package listener

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.clipboard.ClipboardEvent
import org.w3c.dom.events.Event

/**
 * @author poludov
 */
class OnPasteEventListener(override val input: HTMLInputElement) : EventListener<HTMLInputElement> {
  override val type = "paste"

  override fun handle(event: Event) {
    event as ClipboardEvent

    val pastedData = event.clipboardData?.getData("Text") ?: return
    if (pastedData.isBlank()) {
      return
    }

    val newValue = pastedData.toLongOrNull()
    if (newValue == null) {
      input.setCustomValidity("Invalid key")
      event.preventDefault()
      return
    }
    input.setCustomValidity("")
  }
}
