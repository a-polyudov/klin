package model


inline fun validate(value: Boolean, lazyMessage: () -> String) {
  if (!value) {
    throw ValidationException(lazyMessage.invoke())
  }
}

class ValidationException(message: String) : RuntimeException(message)
