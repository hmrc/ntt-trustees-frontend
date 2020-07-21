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
import play.api.data.Forms._

class WhatIsHeadOfficeAddressNonUkFormProvider @Inject() extends Mappings {

  def apply(): Form[Address] =
    Form(
      mapping(
        "addressLine1" -> text("whatIsHeadOfficeAddressNonUk.error.line1.required")
          .verifying(maxLength(100, "whatIsHeadOfficeAddressNonUk.error.line1.length")),
        "addressLine2" -> text("whatIsHeadOfficeAddressNonUk.error.line2.required")
          .verifying(maxLength(100, "whatIsHeadOfficeAddressNonUk.error.line2.length")),
        "addressLine3" -> optionalText()
          .verifying(optMaxLength(100, "whatIsHeadOfficeAddressNonUk.error.line3.length")),
        "addressLine4" -> ignored(Option.empty[String]),
        "country" -> text("whatIsHeadOfficeAddressNonUk.error.line5.required"),
        "postcode" -> ignored(Option.empty[String])
      )(Address.apply)(Address.unapply)
    )
}
