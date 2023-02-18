package me.mmt.klin.options

import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.mmt.klin.options.ui.OptionsPageBuilder
import me.mmt.klin.storage.StorageSettingsProvider

fun main() {
  window.onload = {
    MainScope().launch {
      StorageSettingsProvider.read()
        ?.let {
          OptionsPageBuilder.build(it)
        }
    }
  }
}
