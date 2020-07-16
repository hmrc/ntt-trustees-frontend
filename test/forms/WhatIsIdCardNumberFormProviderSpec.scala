package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class WhatIsIdCardNumberFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsIdCardNumber.error.required"
  val lengthKey = "whatIsIdCardNumber.error.length"
  val maxLength = 100

  val form = new WhatIsIdCardNumberFormProvider()()

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
