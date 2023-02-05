package me.mmt.klin.ui

import kotlinx.browser.document
import kotlinx.html.TagConsumer
import kotlinx.html.a
import kotlinx.html.dom.append
import kotlinx.html.img
import kotlinx.html.js.div
import kotlinx.html.tabIndex
import org.w3c.dom.HTMLElement

fun TagConsumer<HTMLElement>.addGitHubLink() {
  div("github-link") {
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