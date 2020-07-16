package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class WhatIsHeadOfficeAddressNonUkFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsHeadOfficeAddressNonUk.error.required"
  val lengthKey = "whatIsHeadOfficeAddressNonUk.error.length"
  val maxLength = 100

  val form = new WhatIsHeadOfficeAddressNonUkFormProvider()()

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
