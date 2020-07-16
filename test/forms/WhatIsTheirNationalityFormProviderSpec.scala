package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class WhatIsTheirNationalityFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsTheirNationality.error.required"
  val lengthKey = "whatIsTheirNationality.error.length"
  val maxLength = 100

  val form = new WhatIsTheirNationalityFormProvider()()

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
