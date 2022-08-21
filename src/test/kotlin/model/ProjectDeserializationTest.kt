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
