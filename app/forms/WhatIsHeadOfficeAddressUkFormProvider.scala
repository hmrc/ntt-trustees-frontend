/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package forms

import javax.inject.Inject
import forms.mappings.Mappings
import models.Address
import play.api.data.Form
import play.api.data.Forms.{ignored, mapping}

class WhatIsHeadOfficeAddressUkFormProvider @Inject() extends Mappings {

  def apply(): Form[Address] =
    Form(
      mapping(
        "addressLine1" -> text("whatIsHeadOfficeAddressUk.error.line1.required")
          .verifying(maxLength(100, "whatIsHeadOfficeAddressUk.error.line1.length")),
        "addressLine2" -> text("whatIsHeadOfficeAddressUk.error.line2.required")
          .verifying(maxLength(100, "whatIsHeadOfficeAddressUk.error.line2.length")),
        "addressLine3" -> optionalText()
          .verifying(optMaxLength(100, "whatIsHeadOfficeAddressUk.error.line3.length")),
        "addressLine4" -> optionalText()
          .verifying(optMaxLength(100, "whatIsHeadOfficeAddressUk.error.line4.length")),
        "country" -> ignored("GB"),
        "postcode" -> requiredTextToOptional("whatIsHeadOfficeAddressUk.error.line5.required")
          .verifying(optMaxLength(10, "whatIsHeadOfficeAddressUk.error.line5.length"))
      )(Address.apply)(Address.unapply)
    )
}
