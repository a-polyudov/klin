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
  val name: String = "", //default value is for correct error in case then "name" is not present in settings.json
  val baseUrl: String = "", //default value is for correct error in case then "name" is not present in settings.json
  val logoPath: String? = null,
  val tooltipText: String? = null,
  val maxTaskNumberLength: Int = DEFAULT_MAX_TASK_NUMBER_LENGTH,
) {
  init {
    validate(name.isNotBlank()) {
      "Project name is blank"
    }
    validate(name.length <= MAX_NAME_LENGTH) {
      "Project name '${name}' is too long (max length is $MAX_NAME_LENGTH symbols)"
    }
    validate(baseUrl.isNotBlank()) {
      "Project '${name}' base URL is blank"
    }
    validate(URL_REGEXP.matches(baseUrl)) {
      "Project '${name}' base URL is invalid"
    }
    validate(maxTaskNumberLength > 0) {
      "Project '${name}' max task number length should be positive"
    }
    tooltipText?.let {
      validate(it.length < MAX_TOOLTIP_TEXT_LENGTH) {
        "Project '${name}' tooltip text length is too big (max length is $MAX_TOOLTIP_TEXT_LENGTH symbols)"
      }
    }
  }

  fun buildUrl(taskNumber: String): String = "$baseUrl/browse/$name-$taskNumber"
}
