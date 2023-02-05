package me.mmt.klin.listener

import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

/**
 * @author a-polyudov
 */
interface EventListener<T: HTMLElement> {
  val type: String
  val element: T

  fun handle(event: Event)
}

fun <T : HTMLElement> T.registerEventListener(listener: EventListener<T>) =
  addEventListener(listener.type, listener::handle)