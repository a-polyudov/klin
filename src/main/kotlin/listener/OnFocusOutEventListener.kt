package listener

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

/**
 * @author a-polyudov
 */
class OnFocusOutEventListener(override val input: HTMLInputElement) : EventListener<HTMLInputElement> {
  override val type = "focusout"

  override fun handle(event: Event) {
    input.setCustomValidity("")
  }
}
