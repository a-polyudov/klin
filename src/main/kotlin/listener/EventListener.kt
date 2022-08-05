package listener

import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

/**
 * @author poludov
 */
interface EventListener<T: HTMLElement> {
  val type: String
  val input: T

  fun handle(event: Event)
}

fun <T : HTMLElement> T.registerEventListener(listener: EventListener<T>) {
  addEventListener(listener.type, listener::handle)
}
