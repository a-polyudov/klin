package ui

import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.js.div
import kotlinx.html.js.span
import model.Project
import model.Settings
import ui.ProjectElementBuilder.buildHtmlFor

/**
 * @author poludov
 */
object MainPageBuilder {
  fun loadMainPage(settings: Settings) =
    if (settings.projects.isEmpty()) emptyMainPage("Project list is empty")
    else projectsMainPage(settings.projects)

  fun emptyMainPage(text: String = "Ooops, something went wrong") {
    document.body?.append
      ?.span {
        +text
      }
  }

  private fun projectsMainPage(projects: List<Project>) {
    document.body?.append
      ?.div {
        id = "root-container"
      }
      ?.apply {
        projects
          .forEachIndexed { index, project ->
            append {
              buildHtmlFor(index, project)
            }
          }
      }
  }
}
