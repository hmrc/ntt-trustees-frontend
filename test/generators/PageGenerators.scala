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

import org.scalacheck.Arbitrary
import pages._

trait PageGenerators {

  implicit lazy val arbitraryDoYouKnowHeadOfficeCountryPage: Arbitrary[DoYouKnowHeadOfficeCountryPage.type] =
    Arbitrary(DoYouKnowHeadOfficeCountryPage)

  implicit lazy val arbitraryDoYouKnowCountryOfResidencyPage: Arbitrary[DoYouKnowCountryOfResidencyPage.type] =
    Arbitrary(DoYouKnowCountryOfResidencyPage)

  implicit lazy val arbitraryDoYouKnowCountryOfNationalityPage: Arbitrary[DoYouKnowCountryOfNationalityPage.type] =
    Arbitrary(DoYouKnowCountryOfNationalityPage)

  implicit lazy val arbitraryWhatIsTheUtrPage: Arbitrary[WhatIsTheUtrPage.type] =
    Arbitrary(WhatIsTheUtrPage)

  implicit lazy val arbitraryWhatIsTheLeadTrusteesRegisteredNamePage: Arbitrary[WhatIsTheLeadTrusteesRegisteredNamePage.type] =
    Arbitrary(WhatIsTheLeadTrusteesRegisteredNamePage)

  implicit lazy val arbitraryWhatIsTheBusinessNamePage: Arbitrary[WhatIsTheBusinessNamePage.type] =
    Arbitrary(WhatIsTheBusinessNamePage)

  implicit lazy val arbitraryWhatIsHeadOfficeAddressUkPage: Arbitrary[WhatIsHeadOfficeAddressUkPage.type] =
    Arbitrary(WhatIsHeadOfficeAddressUkPage)

  implicit lazy val arbitraryWhatIsHeadOfficeAddressNonUkPage: Arbitrary[WhatIsHeadOfficeAddressNonUkPage.type] =
    Arbitrary(WhatIsHeadOfficeAddressNonUkPage)

  implicit lazy val arbitraryIsTrusteeAUkRegisteredBusinessPage: Arbitrary[IsTrusteeAUkRegisteredBusinessPage.type] =
    Arbitrary(IsTrusteeAUkRegisteredBusinessPage)

  implicit lazy val arbitraryIsHeadOfficeInUkPage: Arbitrary[IsHeadOfficeInUkPage.type] =
    Arbitrary(IsHeadOfficeInUkPage)

  implicit lazy val arbitraryWhatIsPassportNumberPage: Arbitrary[WhatIsPassportNumberPage.type] =
    Arbitrary(WhatIsPassportNumberPage)

  implicit lazy val arbitraryWhatIsPassportCountryOfIssuePage: Arbitrary[WhatIsPassportCountryOfIssuePage.type] =
    Arbitrary(WhatIsPassportCountryOfIssuePage)

  implicit lazy val arbitraryWhatIsIdCardNumberPage: Arbitrary[WhatIsIdCardNumberPage.type] =
    Arbitrary(WhatIsIdCardNumberPage)

  implicit lazy val arbitraryWhatIsIdCardExpiryDatePage: Arbitrary[WhatIsIdCardExpiryDatePage.type] =
    Arbitrary(WhatIsIdCardExpiryDatePage)

  implicit lazy val arbitraryWhatIsIdCardCountryOfIssuePage: Arbitrary[WhatIsIdCardCountryOfIssuePage.type] =
    Arbitrary(WhatIsIdCardCountryOfIssuePage)

  implicit lazy val arbitraryWhatIsExpiryDatePage: Arbitrary[WhatIsExpiryDatePage.type] =
    Arbitrary(WhatIsExpiryDatePage)

  implicit lazy val arbitraryWhatIsTheirNationalityPage: Arbitrary[WhatIsTheirNationalityPage.type] =
    Arbitrary(WhatIsTheirNationalityPage)

  implicit lazy val arbitraryWhatIsTheirNamePage: Arbitrary[WhatIsTheirNamePage.type] =
    Arbitrary(WhatIsTheirNamePage)

  implicit lazy val arbitraryWhichDetailsCanYouProvidePage: Arbitrary[WhichDetailsCanYouProvidePage.type] =
    Arbitrary(WhichDetailsCanYouProvidePage)

  implicit lazy val arbitraryWhatIsTheirTelephoneNumberPage: Arbitrary[WhatIsTheirTelephoneNumberPage.type] =
    Arbitrary(WhatIsTheirTelephoneNumberPage)

  implicit lazy val arbitraryWhatIsTheirNationalInsuranceNumberPage: Arbitrary[WhatIsTheirNationalInsuranceNumberPage.type] =
    Arbitrary(WhatIsTheirNationalInsuranceNumberPage)

  implicit lazy val arbitraryWhatIsTheirEmailAddressPage: Arbitrary[WhatIsTheirEmailAddressPage.type] =
    Arbitrary(WhatIsTheirEmailAddressPage)

  implicit lazy val arbitraryWhatIsTheirDateOfBirthPage: Arbitrary[WhatIsTheirDateOfBirthPage.type] =
    Arbitrary(WhatIsTheirDateOfBirthPage)


  implicit lazy val arbitraryWhatIsTheirAddressUkPage: Arbitrary[WhatIsTheirAddressUkPage.type] =
    Arbitrary(WhatIsTheirAddressUkPage)

  implicit lazy val arbitraryWhatIsTheirAddressNonUkPage: Arbitrary[WhatIsTheirAddressNonUkPage.type] =
    Arbitrary(WhatIsTheirAddressNonUkPage)

  implicit lazy val arbitraryWhatIsTheLeadTrusteesNamePage: Arbitrary[WhatIsTheLeadTrusteesNamePage.type] =
    Arbitrary(WhatIsTheLeadTrusteesNamePage)

  implicit lazy val arbitraryIsTheirResidenceInTheUkPage: Arbitrary[IsTheirResidenceInTheUkPage.type] =
    Arbitrary(IsTheirResidenceInTheUkPage)

  implicit lazy val arbitraryDoYouKnowTheirEmailAddressPage: Arbitrary[DoYouKnowTheirEmailAddressPage.type] =
    Arbitrary(DoYouKnowTheirEmailAddressPage)

  implicit lazy val arbitraryDoTheyHaveANationalInsuranceNumberPage: Arbitrary[DoTheyHaveANationalInsuranceNumberPage.type] =
    Arbitrary(DoTheyHaveANationalInsuranceNumberPage)

  implicit lazy val arbitraryAreYouEnteringDetailsForLeadTrusteePage: Arbitrary[AreYouEnteringDetailsForLeadTrusteePage.type] =
    Arbitrary(AreYouEnteringDetailsForLeadTrusteePage)
}
