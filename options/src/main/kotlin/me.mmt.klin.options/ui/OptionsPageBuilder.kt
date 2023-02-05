package me.mmt.klin.options.ui

import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import me.mmt.klin.model.Project
import me.mmt.klin.model.Settings
import me.mmt.klin.ui.addGitHubLink

object OptionsPageBuilder {
  fun build(settings: Settings) {
    document.body?.append
      ?.h2 { +"Projects" }
    document.body?.append
      ?.div("div-table grey-grid-table") {
        div("table-head") {
          div("table-row") {
            div("table-head-cell") { +"Name" }
            div("table-head-cell") { +"Base URL" }
            div("table-head-cell") { +"Tooltip text" }
            div("table-head-cell") { +"Max task number length" }
          }
        }
        div("table-body") {
          settings.projects.forEach { buildProjectRow(it) }
        }
      }
    document.body?.append
      ?.addGitHubLink()
  }

  private fun DIV.buildProjectRow(project: Project) {
    div("table-row") {
      div("table-cell project-label") { +project.name }
      div("table-cell") { +project.baseUrl }
      div("table-cell") { project.tooltipText?.unaryPlus() }
      div("table-cell") { +project.maxTaskNumberLength.toString() }
    }
  }
}
