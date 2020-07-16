package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class IsTrusteeAUkRegisteredBusinessFormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "isTrusteeAUkRegisteredBusiness.error.required"
  val invalidKey = "error.boolean"

  val form = new IsTrusteeAUkRegisteredBusinessFormProvider()()

  ".value" - {

    val fieldName = "value"

    behave like booleanField(
      form,
      fieldName,
      invalidError = FormError(fieldName, invalidKey)
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
