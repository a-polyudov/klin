package model

import kotlinx.serialization.Serializable

/**
 * This is the default value of maximum project key size.
 *
 * [Jira Doc](https://support.atlassian.com/jira-cloud-administration/docs/configure-jira-application-options/)
 */
private const val MAX_NAME_LENGTH = 10
private const val DEFAULT_MAX_TASK_NUMBER_LENGTH = 5
private const val MAX_TOOLTIP_TEXT_LENGTH = 20
private val URL_REGEXP =
  "https?://(www\\.)?[-a-zA-Z\\d@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z\\d()@:%_+.~#?&/=]*)".toRegex()

/**
 * @author a-polyudov
 */
@Serializable
data class Project(
  val name: String,
  val baseUrl: String,
  val logoPath: String? = null,
  val tooltipText: String? = null,
  val maxTaskNumberLength: Int = DEFAULT_MAX_TASK_NUMBER_LENGTH,
) {
  init {
    require(name.isNotBlank()) {
      "Project name is blank"
    }
    require(name.length <= MAX_NAME_LENGTH) {
      "Project name '${name}' is too long"
    }
    require(baseUrl.isNotBlank()) {
      "Project '${name}' base URL is blank"
    }
    require(URL_REGEXP.matches(baseUrl)) {
      "Project '${name}' base URL is invalid"
    }
    require(maxTaskNumberLength > 0) {
      "Project '${name}' max task number length should be positive"
    }
    tooltipText?.let {
      require(it.length < MAX_TOOLTIP_TEXT_LENGTH) {
        "Project '${name}' tooltip text length is too big"
      }
    }
  }

  fun buildUrl(taskNumber: String): String = "$baseUrl/browse/$name-$taskNumber"
}
