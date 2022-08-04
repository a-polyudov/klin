package listener

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

/**
 * @author poludov
 */
sealed interface InputEventListener {
  val type: String
  val input: HTMLInputElement

  fun handle(event: Event)
}

/**
 * @author poludov
 */
fun HTMLInputElement.registerEventListener(listener: InputEventListener) {
  addEventListener(listener.type, listener::handle)
}
