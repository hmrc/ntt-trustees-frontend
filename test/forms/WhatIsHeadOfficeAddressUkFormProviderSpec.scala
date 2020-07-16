package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class WhatIsHeadOfficeAddressUkFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsHeadOfficeAddressUk.error.required"
  val lengthKey = "whatIsHeadOfficeAddressUk.error.length"
  val maxLength = 100

  val form = new WhatIsHeadOfficeAddressUkFormProvider()()

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
