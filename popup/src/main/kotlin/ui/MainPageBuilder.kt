package ui

import kotlinx.browser.document
import kotlinx.html.a
import kotlinx.html.dom.append
import kotlinx.html.img
import kotlinx.html.js.div
import kotlinx.html.js.span
import kotlinx.html.tabIndex
import model.Settings
import ui.ProjectElementBuilder.buildHtmlFor

private const val DEFAULT_TEXT_ERROR = "Ooops, something went wrong"

/**
 * @author a-polyudov
 */
object MainPageBuilder {
  fun loadMainPage(settings: Settings) {
    document.body?.append
      ?.div("root")
      ?.apply {
        settings.projects
          .forEachIndexed { index, project ->
            append {
              buildHtmlFor(index, project, settings.logoSizePx)
            }
          }
      }
    addGitHubLink()
  }

  fun emptyMainPage(text: String?) {
    document.body?.append
      ?.span {
        +(text ?: DEFAULT_TEXT_ERROR)
      }
    addGitHubLink()
  }

  private fun addGitHubLink() {
    document.body?.append
      ?.div("github-link") {
        a(classes = "fa") {
          href = "https://github.com/a-polyudov/klin"
          target = "_blank"
          tabIndex = "-1"
          img {
            src = "github_logo.png"
            width = "15"
            height = "15"
          }
          +" klin"
        }
      }
  }
}
