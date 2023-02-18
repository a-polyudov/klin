@file:JsQualifier("chrome.storage")
@file:Suppress("PackageDirectoryMismatch")

package chrome.storage

import kotlin.js.Promise

internal external val local: StorageArea

/**
 * @author a-polyudov
 */
internal external interface StorageArea {

  fun get(keys: dynamic = definedExternally): Promise<dynamic>

  fun set(keys: dynamic): Promise<Unit>
}
