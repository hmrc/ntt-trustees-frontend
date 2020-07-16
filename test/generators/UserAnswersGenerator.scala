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

package generators

import models.UserAnswers
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.TryValues
import pages._
import play.api.libs.json.{JsPath, JsValue, Json}

trait UserAnswersGenerator extends TryValues {
  self: Generators =>

  val generators: Seq[Gen[(QuestionPage[_], JsValue)]] =
    arbitrary[(WhatIsTheUtrPage.type, JsValue)] ::
    arbitrary[(WhatIsTheLeadTrusteesRegisteredNamePage.type, JsValue)] ::
    arbitrary[(WhatIsTheBusinessNamePage.type, JsValue)] ::
    arbitrary[(WhatIsHeadOfficeAddressUkPage.type, JsValue)] ::
    arbitrary[(WhatIsHeadOfficeAddressNonUkPage.type, JsValue)] ::
    arbitrary[(IsTrusteeAUkRegisteredBusinessPage.type, JsValue)] ::
    arbitrary[(IsHeadOfficeInUkPage.type, JsValue)] ::
    arbitrary[(WhatIsPassportNumberPage.type, JsValue)] ::
    arbitrary[(WhatIsPassportCountryOfIssuePage.type, JsValue)] ::
    arbitrary[(WhatIsIdCardNumberPage.type, JsValue)] ::
    arbitrary[(WhatIsIdCardExpiryDatePage.type, JsValue)] ::
    arbitrary[(WhatIsIdCardCountryOfIssuePage.type, JsValue)] ::
    arbitrary[(WhatIsExpiryDatePage.type, JsValue)] ::
    arbitrary[(WhatIsTheirNationalityPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirNamePage.type, JsValue)] ::
    arbitrary[(WhichDetailsCanYouProvidePage.type, JsValue)] ::
    arbitrary[(WhatIsTheirTelephoneNumberPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirNationalInsuranceNumberPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirEmailAddressPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirDateOfBirthPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirCountryOfNationalityPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirAddressUkPage.type, JsValue)] ::
    arbitrary[(WhatIsTheirAddressNonUkPage.type, JsValue)] ::
    arbitrary[(WhatIsTheLeadTrusteesNamePage.type, JsValue)] ::
    arbitrary[(IsTheirResidenceInTheUkPage.type, JsValue)] ::
    arbitrary[(DoYouKnowTheirEmailAddressPage.type, JsValue)] ::
    arbitrary[(DoTheyHaveANationalInsuranceNumberPage.type, JsValue)] ::
    arbitrary[(AreYouEnteringDetailsForLeadTrusteePage.type, JsValue)] ::
    Nil

  implicit lazy val arbitraryUserData: Arbitrary[UserAnswers] = {

    import models._

    Arbitrary {
      for {
        id      <- nonEmptyString
        data    <- generators match {
          case Nil => Gen.const(Map[QuestionPage[_], JsValue]())
          case _   => Gen.mapOf(oneOf(generators))
        }
      } yield UserAnswers (
        id = id,
        data = data.foldLeft(Json.obj()) {
          case (obj, (path, value)) =>
            obj.setObject(path.path, value).get
        }
      )
    }
  }
}
