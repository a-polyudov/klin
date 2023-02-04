@file:JsQualifier("chrome.storage")

@file:Suppress("InvalidPackageDeclaration", "PackageDirectoryMismatch")
package chrome.storage

import kotlin.js.Promise

external val local: StorageArea

external interface StorageArea {

  fun get(keys: dynamic = definedExternally): Promise<dynamic>

  fun set(keys: dynamic): Promise<Unit>
}
