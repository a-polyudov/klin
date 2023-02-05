package me.mmt.klin.options.ui

import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import me.mmt.klin.listener.registerEventListener
import me.mmt.klin.model.Project
import me.mmt.klin.model.Settings
import me.mmt.klin.options.listener.Direction
import me.mmt.klin.options.listener.OnButtonClickEventListener
import me.mmt.klin.options.listener.OnDeleteProjectButtonClick
import me.mmt.klin.options.listener.OnMoveProjectButtonClick
import me.mmt.klin.options.ui.ElementConstants.NEW_PROJECT_BUTTON_ID
import me.mmt.klin.options.ui.ElementConstants.PROJECT_ACTION_BUTTON_CLASS
import me.mmt.klin.options.ui.ElementConstants.TABLE_CELL_CLASS
import me.mmt.klin.options.ui.ElementConstants.TABLE_HEAD_CELL_CLASS
import me.mmt.klin.ui.CommonCssClasses.PROJECT_LABEL_CLASS
import me.mmt.klin.ui.addGitHubLink
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement



object OptionsPageBuilder {
  fun build(settings: Settings) {
    document.body?.append
      ?.h2 { +"Projects" }
    document.body?.append
      ?.div("div-table grey-grid-table") {
        div("table-head") {
          div("table-row") {
            div(TABLE_HEAD_CELL_CLASS) { +"Name" }
            div(TABLE_HEAD_CELL_CLASS) { +"Base URL" }
            div(TABLE_HEAD_CELL_CLASS) { +"Logo path" }
            div(TABLE_HEAD_CELL_CLASS) { +"Tooltip text" }
            div(TABLE_HEAD_CELL_CLASS) { +"Max task number length" }
            div(TABLE_HEAD_CELL_CLASS) { +"Actions" }
          }
        }
        div("table-body") {
          settings.projects.forEachIndexed { index, project ->
            buildProjectRow(index, settings.projects.size - 1, project)
          }
        }
      }
    document.body?.append
      ?.addCreateNewProjectButton()
    document.body?.append
      ?.addGitHubLink()


    settings.projects.forEach { project ->
      (document.getElementById("${project.name}-move-down-project-button") as? HTMLButtonElement)?.apply {
        registerEventListener(OnMoveProjectButtonClick(this, project, Direction.DOWN))
      }
      (document.getElementById("${project.name}-move-up-project-button") as? HTMLButtonElement)?.apply {
        registerEventListener(OnMoveProjectButtonClick(this, project, Direction.UP))
      }
      (document.getElementById("${project.name}-delete-project-button") as? HTMLButtonElement)?.apply {
        registerEventListener(OnDeleteProjectButtonClick(this, project))
      }
    }
  }

  private fun TagConsumer<HTMLElement>.addCreateNewProjectButton() {
    button {
      id = NEW_PROJECT_BUTTON_ID
      classes = setOf("new-project-button")
      +"+"
    }
    (document.getElementById("create-new-project-button") as HTMLButtonElement).apply {
      registerEventListener(OnButtonClickEventListener(this))
    }
  }

  private fun DIV.buildProjectRow(index: Int, lastIndex: Int, project: Project) {
    div("table-row") {
      div("$TABLE_CELL_CLASS $PROJECT_LABEL_CLASS") { +project.name }
      div(TABLE_CELL_CLASS) { +project.baseUrl }
      div(TABLE_CELL_CLASS) { project.logoPath?.unaryPlus() }
      div(TABLE_CELL_CLASS) { project.tooltipText?.unaryPlus() }
      div(TABLE_CELL_CLASS) { +project.maxTaskNumberLength.toString() }
      div(TABLE_CELL_CLASS) {
        button {
          id = "${project.name}-edit-project-button"
          classes = setOf(PROJECT_ACTION_BUTTON_CLASS)
          +"✎"
        }
        if (index != lastIndex) {
          button {
            id = "${project.name}-move-down-project-button"
            classes = setOf("reposition-project-button", "move-down-project-button", PROJECT_ACTION_BUTTON_CLASS)
            +"↓"
          }
        }
        if (index != 0) {
          button {
            id = "${project.name}-move-up-project-button"
            classes = setOf("reposition-project-button", "move-up-project-button", PROJECT_ACTION_BUTTON_CLASS)
            +"↑"
          }
        }
        button {
          id = "${project.name}-delete-project-button"
          classes = setOf("delete-project-button", PROJECT_ACTION_BUTTON_CLASS)
          +"✕"
        }
      }
    }
  }
}
