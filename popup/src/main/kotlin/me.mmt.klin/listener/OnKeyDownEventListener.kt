package me.mmt.klin.listener

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

/**
 * @author a-polyudov
 */
class OnKeyDownEventListener(override val element: HTMLInputElement) : EventListener<HTMLInputElement> {
  override val type = "keydown"

  override fun handle(event: Event) {
    when ((event as KeyboardEvent).key) {
      "Backspace", "Delete" -> element.setCustomValidity("")
    }
  }
}
