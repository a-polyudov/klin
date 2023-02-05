package me.mmt.klin.options.listener

import kotlinx.browser.document
import me.mmt.klin.listener.EventListener
import me.mmt.klin.options.ui.ElementConstants.NEW_PROJECT_BUTTON_ID
import me.mmt.klin.options.ui.ElementConstants.PROJECT_ACTION_BUTTON_CLASS
import org.w3c.dom.Element
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.asList
import org.w3c.dom.events.Event

class OnButtonClickEventListener(override val element: HTMLButtonElement) : EventListener<HTMLButtonElement> {
  override val type = "click"

  override fun handle(event: Event) {
    document.getElementsByClassName(PROJECT_ACTION_BUTTON_CLASS)
      .asList()
      .forEach { it.hide() }
    document.getElementById(NEW_PROJECT_BUTTON_ID)?.hide()

    //TODO: create panel for new project settings with 2 buttons: 'cancel' and 'save'
  }

  private fun Element?.hide() {
    this?.asDynamic()?.style?.display = "none"
  }
}
