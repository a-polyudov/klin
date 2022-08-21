package listener

import kotlinx.browser.window
import model.Project
import org.w3c.dom.HTMLSpanElement
import org.w3c.dom.events.Event

/**
 * @author a-polyudov
 */
class OnClickEventListener(
  private val project: Project,
  override val input: HTMLSpanElement,
) : EventListener<HTMLSpanElement> {
  override val type = "click"

  override fun handle(event: Event) {
    window.open(project.baseUrl, "_blank")
  }
}
