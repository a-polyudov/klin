package me.mmt.klin.model

/**
 * @author a-polyudov
 */
inline fun validate(value: Boolean, lazyMessage: () -> String) {
  if (!value) {
    throw ValidationException(lazyMessage.invoke())
  }
}

/**
 * @author a-polyudov
 */
class ValidationException(message: String) : RuntimeException(message)
