package ui

import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.js.div
import listener.OnFocusOutEventListener
import listener.OnKeyPressEventListener
import listener.OnPasteEventListener
import listener.registerEventListener
import model.Project
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement

/**
 * @author poludov
 */
object ProjectElementBuilder {
  fun TagConsumer<HTMLElement>.buildHtmlFor(index: Int, project: Project) {
    div("project-element") {
      id = "${project.name}-project-element"
      tabIndex = "-1"
      span("project-label") {
        id = "${project.name}-label"
        tabIndex = "-1"
        +project.name.uppercase()
      }
      span("task-number-span") {
        input(type = InputType.text, classes = "task-number-input") {
          id = "${project.name}-task-number-input"
          tabIndex = index.toString()
          maxLength = project.maxTaskNumberLength.toString()
          minLength = project.minTaskNumberLength.toString()
        }
      }
    }

    (document.getElementById("${project.name}-task-number-input") as HTMLInputElement)
      .apply {
        setCustomValidity("")

        registerEventListener(OnPasteEventListener(this))
        registerEventListener(OnFocusOutEventListener(this))
        registerEventListener(OnKeyPressEventListener(project, this))

        if (index == 0) {
          focus()
        }
      }
  }
}
