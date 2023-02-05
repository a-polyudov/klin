package me.mmt.klin.options.ui

import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.js.div
import me.mmt.klin.model.Settings

object OptionsPageBuilder {
  fun build(settings: Settings) {
    document.body?.append
      ?.div("root")
      ?.append {
        div {
          +"ASD: $settings"
        }
      }
  }
}
