package me.mmt.klin.ui

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import me.mmt.klin.listener.*
import me.mmt.klin.model.Project
import me.mmt.klin.ui.CommonCssClasses.PROJECT_LABEL_CLASS
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement

/**
 * @author a-polyudov
 */
object ProjectElementBuilder {
  fun TagConsumer<HTMLElement>.buildHtmlFor(index: Int, project: Project, logoSizePx: Int) {
    div("project-element") {
      id = "${project.name}-project-element"
      tabIndex = "-1"
      span("project-name") {
        id = "${project.name}-project-name"
        if (index != 0) {
          style = "padding-top: 10px;"
        }
        project.logoPath?.let {
          img {
            src = project.logoPath!!
            width = logoSizePx.toString()
            height = logoSizePx.toString()
            tabIndex = "-1"
          }
        }
        div(PROJECT_LABEL_CLASS) {
          id = "${project.name}-label"
          tabIndex = "-1"
          project.tooltipText?.let { title = it }
          +project.name.uppercase()

          onClickFunction = {
            window.open("${project.baseUrl}/projects/${project.name.uppercase()}", "_blank")
          }
        }
      }
      span("task-number") {
        input(type = InputType.text, classes = "task-number-input") {
          id = "${project.name}-task-number-input"
          tabIndex = index.toString()
          maxLength = project.maxTaskNumberLength.toString()
        }
      }
    }

    (document.getElementById("${project.name}-task-number-input") as HTMLInputElement)
      .apply {
        setCustomValidity("")

        registerEventListener(OnPasteEventListener(project, this))
        registerEventListener(OnFocusOutEventListener(this))
        registerEventListener(OnKeyPressEventListener(project, this))
        registerEventListener(OnKeyDownEventListener(this))

        if (index == 0) {
          focus()
        }
      }
  }
}
