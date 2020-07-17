package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class DoYouKnowHeadOfficeCountryFormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "doYouKnowHeadOfficeCountry.error.required"
  val invalidKey = "error.boolean"

  val form = new DoYouKnowHeadOfficeCountryFormProvider()()

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
