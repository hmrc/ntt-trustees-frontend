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

import models._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import pages._
import play.api.libs.json.{JsValue, Json}

trait UserAnswersEntryGenerators extends PageGenerators with ModelGenerators {

  implicit lazy val arbitraryWhatIsTheUtrUserAnswersEntry: Arbitrary[(WhatIsTheUtrPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheUtrPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheLeadTrusteesRegisteredNameUserAnswersEntry: Arbitrary[(WhatIsTheLeadTrusteesRegisteredNamePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheLeadTrusteesRegisteredNamePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheBusinessNameUserAnswersEntry: Arbitrary[(WhatIsTheBusinessNamePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheBusinessNamePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsHeadOfficeAddressUkUserAnswersEntry: Arbitrary[(WhatIsHeadOfficeAddressUkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsHeadOfficeAddressUkPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsHeadOfficeAddressNonUkUserAnswersEntry: Arbitrary[(WhatIsHeadOfficeAddressNonUkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsHeadOfficeAddressNonUkPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryIsTrusteeAUkRegisteredBusinessUserAnswersEntry: Arbitrary[(IsTrusteeAUkRegisteredBusinessPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[IsTrusteeAUkRegisteredBusinessPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryIsHeadOfficeInUkUserAnswersEntry: Arbitrary[(IsHeadOfficeInUkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[IsHeadOfficeInUkPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsPassportNumberUserAnswersEntry: Arbitrary[(WhatIsPassportNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsPassportNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsPassportCountryOfIssueUserAnswersEntry: Arbitrary[(WhatIsPassportCountryOfIssuePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsPassportCountryOfIssuePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsIdCardNumberUserAnswersEntry: Arbitrary[(WhatIsIdCardNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsIdCardNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsIdCardExpiryDateUserAnswersEntry: Arbitrary[(WhatIsIdCardExpiryDatePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsIdCardExpiryDatePage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsIdCardCountryOfIssueUserAnswersEntry: Arbitrary[(WhatIsIdCardCountryOfIssuePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsIdCardCountryOfIssuePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsExpiryDateUserAnswersEntry: Arbitrary[(WhatIsExpiryDatePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsExpiryDatePage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirNationalityUserAnswersEntry: Arbitrary[(WhatIsTheirNationalityPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirNationalityPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirNameUserAnswersEntry: Arbitrary[(WhatIsTheirNamePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirNamePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhichDetailsCanYouProvideUserAnswersEntry: Arbitrary[(WhichDetailsCanYouProvidePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhichDetailsCanYouProvidePage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirTelephoneNumberUserAnswersEntry: Arbitrary[(WhatIsTheirTelephoneNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirTelephoneNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirNationalInsuranceNumberUserAnswersEntry: Arbitrary[(WhatIsTheirNationalInsuranceNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirNationalInsuranceNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirEmailAddressUserAnswersEntry: Arbitrary[(WhatIsTheirEmailAddressPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirEmailAddressPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirDateOfBirthUserAnswersEntry: Arbitrary[(WhatIsTheirDateOfBirthPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirDateOfBirthPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirCountryOfNationalityUserAnswersEntry: Arbitrary[(WhatIsTheirCountryOfNationalityPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirCountryOfNationalityPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirAddressUkUserAnswersEntry: Arbitrary[(WhatIsTheirAddressUkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirAddressUkPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirAddressNonUkUserAnswersEntry: Arbitrary[(WhatIsTheirAddressNonUkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirAddressNonUkPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheLeadTrusteesNameUserAnswersEntry: Arbitrary[(WhatIsTheLeadTrusteesNamePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheLeadTrusteesNamePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryIsTheirResidenceInTheUkUserAnswersEntry: Arbitrary[(IsTheirResidenceInTheUkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[IsTheirResidenceInTheUkPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowTheirEmailAddressUserAnswersEntry: Arbitrary[(DoYouKnowTheirEmailAddressPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowTheirEmailAddressPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoTheyHaveANationalInsuranceNumberUserAnswersEntry: Arbitrary[(DoTheyHaveANationalInsuranceNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoTheyHaveANationalInsuranceNumberPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryAreYouEnteringDetailsForLeadTrusteeUserAnswersEntry: Arbitrary[(AreYouEnteringDetailsForLeadTrusteePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[AreYouEnteringDetailsForLeadTrusteePage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }
}
