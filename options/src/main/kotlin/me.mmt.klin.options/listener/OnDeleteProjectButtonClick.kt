package me.mmt.klin.options.listener

import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.mmt.klin.listener.EventListener
import me.mmt.klin.model.Project
import me.mmt.klin.model.Settings
import me.mmt.klin.storage.StorageSettingsProvider
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.events.Event

class OnDeleteProjectButtonClick(
  override val element: HTMLButtonElement,
  private val project: Project,
) : EventListener<HTMLButtonElement> {
  override val type = "click"

  override fun handle(event: Event) {
    MainScope().launch {
      StorageSettingsProvider.read()?.let { currentSettings ->
        val newProjects = ArrayList(currentSettings.projects)
        val isAnyRemoved = newProjects.removeAll {
          it.name == project.name
        }
        if (!isAnyRemoved) {
          return@launch
        }
        StorageSettingsProvider.write(
          Settings(
            projects = newProjects,
            logoSizePx = currentSettings.logoSizePx
          )
        )
        window.location.reload()
      }
    }
  }
}
