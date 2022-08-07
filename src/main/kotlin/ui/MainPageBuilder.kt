package ui

import kotlinx.browser.document
import kotlinx.html.a
import kotlinx.html.dom.append
import kotlinx.html.img
import kotlinx.html.js.div
import kotlinx.html.js.span
import kotlinx.html.tabIndex
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
    gitHubLink()
  }

  private fun projectsMainPage(projects: List<Project>) {
    document.body?.append
      ?.div("root")
      ?.apply {
        projects
          .forEachIndexed { index, project ->
            append {
              buildHtmlFor(index, project)
            }
          }
      }
    gitHubLink()
  }

  private fun gitHubLink() {
    document.body?.append
      ?.div("github-link") {
        a(classes = "fa") {
          href = "https://github.com/poludov/klin"
          target = "_blank"
          img {
            src = "./github_logo.png"
            width = "15"
            height = "15"
            tabIndex = "-1"
          }
          +" klin"
        }
      }
  }
}
