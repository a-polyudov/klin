package ui

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.js.div
import listener.*
import model.Project
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSpanElement

/**
 * @author a-polyudov
 */
object ProjectElementBuilder {
  fun TagConsumer<HTMLElement>.buildHtmlFor(index: Int, project: Project) {
    div("project-element") {
      id = "${project.name}-project-element"
      tabIndex = "-1"
      span("project-name") {
        id = "${project.name}-project-name"
        project.logoPath?.let {
          div("project-logo") {
            img {
              src = project.logoPath
              width = "40"
              height = "40"
              tabIndex = "-1"
            }
          }
        }
        div {
          span("project-label") {
            id = "${project.name}-label"
            tabIndex = "-1"
            +project.name.uppercase()
          }
        }
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

    (document.getElementById("${project.name}-project-name") as HTMLSpanElement)
      .apply {
        registerEventListener(OnClickEventListener(project, this))
      }

    (document.getElementById("${project.name}-task-number-input") as HTMLInputElement)
      .apply {
        setCustomValidity("")

        registerEventListener(OnPasteEventListener(project, this))
        registerEventListener(OnFocusOutEventListener(this))
        registerEventListener(OnKeyPressEventListener(project, this))

        if (index == 0) {
          focus()
        }
      }
  }
}
