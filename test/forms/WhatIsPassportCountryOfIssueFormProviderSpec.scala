package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class WhatIsPassportCountryOfIssueFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsPassportCountryOfIssue.error.required"
  val lengthKey = "whatIsPassportCountryOfIssue.error.length"
  val maxLength = 100

  val form = new WhatIsPassportCountryOfIssueFormProvider()()

  ".value" - {

    val fieldName = "value"

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      stringsWithMaxLength(maxLength)
    )

    behave like fieldWithMaxLength(
      form,
      fieldName,
      maxLength = maxLength,
      lengthError = FormError(fieldName, lengthKey, Seq(maxLength))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
