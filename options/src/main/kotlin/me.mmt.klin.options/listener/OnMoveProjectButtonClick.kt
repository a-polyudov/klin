package me.mmt.klin.options.listener

import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.mmt.klin.listener.EventListener
import me.mmt.klin.model.Project
import me.mmt.klin.model.Settings
import me.mmt.klin.options.listener.Direction.DOWN
import me.mmt.klin.options.listener.Direction.UP
import me.mmt.klin.storage.StorageSettingsProvider
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.events.Event
import kotlin.math.max
import kotlin.math.min

enum class Direction {
  UP,
  DOWN
}

class OnMoveProjectButtonClick(
  override val element: HTMLButtonElement,
  private val project: Project,
  private val direction: Direction,
) : EventListener<HTMLButtonElement> {
  override val type = "click"

  override fun handle(event: Event) {
    MainScope().launch {
      StorageSettingsProvider.read()?.let { currentSettings ->
        val currentProjects = currentSettings.projects
        val currentIndex = currentProjects.indexOfFirst { it.name == project.name }
        if (currentIndex < 0) {
          return@launch
        }

        val newIndex = when (direction) {
          UP -> max(0, currentIndex - 1)
          DOWN -> min(currentProjects.size - 1, currentIndex + 1)
        }
        if (newIndex == currentIndex) {
          return@launch
        }

        StorageSettingsProvider.write(
          Settings(
            projects = ArrayList(currentProjects).apply {
              val temp = this[newIndex]
              this[newIndex] = this[currentIndex]
              this[currentIndex] = temp
            },
            logoSizePx = currentSettings.logoSizePx
          )
        )
        window.location.reload()
      }
    }
  }
}
