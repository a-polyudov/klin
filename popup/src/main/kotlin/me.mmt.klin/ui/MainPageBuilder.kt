package me.mmt.klin.ui

import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.js.div
import kotlinx.html.js.span
import me.mmt.klin.model.Settings
import me.mmt.klin.ui.ProjectElementBuilder.buildHtmlFor

private const val DEFAULT_TEXT_ERROR = "Ooops, something went wrong"

/**
 * @author a-polyudov
 */
object MainPageBuilder {
  fun build(settings: Settings) {
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
    document.body?.append?.addGitHubLink()
  }

  fun emptyMainPage(text: String?) {
    document.body?.append
      ?.span { +(text ?: DEFAULT_TEXT_ERROR) }
    document.body?.append?.addGitHubLink()
  }
}
