package me.mmt.klin.options.listener

import me.mmt.klin.listener.EventListener
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.events.Event

class OnButtonClickEventListener(override val element: HTMLButtonElement) : EventListener<HTMLButtonElement> {
  override val type = "click"

  override fun handle(event: Event) {
    console.log("ASDASD")
  }
}