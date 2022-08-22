package model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author a-polyudov
 */
class ProjectDeserializationTest {
  //<editor-fold desc="name">
  @Test
  fun shouldThrowExceptionIfProjectNameIsEmptyString() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(name = ""))
    }
    assertEquals("Project name is blank", ex.message)
  }

  @Test
  fun shouldThrowExceptionIfProjectNameIsBlank() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(name = "    "))
    }
    assertEquals("Project name is blank", ex.message)
  }

  @Test
  fun shouldThrowExceptionIfProjectNameIsTooLong() {
    val tooLongProjectName = "<someLongName>".repeat(20)
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(name = tooLongProjectName))
    }
    assertEquals("Project name '$tooLongProjectName' is too long", ex.message)
  }
  //</editor-fold>

  //<editor-fold desc="baseUrl">
  @Test
  fun shouldThrowExceptionIfBaseUrlIsEmptyString() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(baseUrl = ""))
    }
    assertEquals("Project 'JDK' base URL is blank", ex.message)
  }

  @Test
  fun shouldThrowExceptionIfBaseUrlIsBlank() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(baseUrl = "    "))
    }
    assertEquals("Project 'JDK' base URL is blank", ex.message)
  }

  @Test
  fun shouldThrowExceptionIfBaseUrlIsNotAnUrl() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(baseUrl = "somedata"))
    }
    assertEquals("Project 'JDK' base URL is invalid", ex.message)
  }
  //</editor-fold>

  //<editor-fold desc="minTaskNumberLength">
  @Test
  fun shouldThrowExceptionIfMinTaskNumberLengthIsNegative() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(minTaskNumberLength = -1))
    }
    assertEquals("Project 'JDK' min task number length should be positive", ex.message)
  }

  @Test
  fun shouldThrowExceptionIfMinTaskNumberLengthIsZEqualsToZero() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(minTaskNumberLength = 0))
    }
    assertEquals("Project 'JDK' min task number length should be positive", ex.message)
  }
  //</editor-fold>

  //<editor-fold desc="maxTaskNumberLength">
  @Test
  fun shouldThrowExceptionIfMaxTaskNumberLengthIsNegative() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(maxTaskNumberLength = -1))
    }
    assertEquals("Project 'JDK' max task number length should be positive", ex.message)
  }

  @Test
  fun shouldThrowExceptionIfMaxTaskNumberLengthIsZEqualsToZero() {
    val ex = assertFailsWith<IllegalArgumentException> {
      Json.decodeFromString<Project>(buildJson(maxTaskNumberLength = 0))
    }
    assertEquals("Project 'JDK' max task number length should be positive", ex.message)
  }
  //</editor-fold>

  private fun buildJson(
    name: String? = "JDK",
    baseUrl: String? = "https://bugs.openjdk.org",
    maxTaskNumberLength: Int? = 5,
    minTaskNumberLength: Int? = 10,
  ) = """
    {
      ${if (name != null) "\"name\":\"$name\"," else ""}
      ${if (baseUrl != null) "\"baseUrl\":\"$baseUrl\"," else ""}
      ${if (maxTaskNumberLength != null) "\"maxTaskNumberLength\":\"$maxTaskNumberLength\"," else ""}
      ${if (minTaskNumberLength != null) "\"minTaskNumberLength\":\"$minTaskNumberLength\"" else ""}
    }
  """
}
