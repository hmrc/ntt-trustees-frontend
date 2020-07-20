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

package navigation

import base.SpecBase
import controllers.routes
import controllers.individual.{routes => indRoutes}
import controllers.individual.lead.{routes => indLeadRoutes}
import controllers.company.{routes => companyRoutes}
import controllers.company.lead.{routes => companyLeadRoutes}
import generators.Generators
import models._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import pages.{AreYouEnteringDetailsForLeadTrusteePage, CheckYourAnswersPage, DoTheyHaveANationalInsuranceNumberPage, DoYouKnowCountryOfNationalityPage, DoYouKnowCountryOfResidencyPage, DoYouKnowHeadOfficeCountryPage, IsHeadOfficeInUkPage, IsTheirResidenceInTheUkPage, IsTrusteeAUkRegisteredBusinessPage, Page, WhatIsExpiryDatePage, WhatIsHeadOfficeAddressNonUkPage, WhatIsHeadOfficeAddressUkPage, WhatIsIdCardCountryOfIssuePage, WhatIsIdCardExpiryDatePage, WhatIsIdCardNumberPage, WhatIsPassportCountryOfIssuePage, WhatIsPassportNumberPage, WhatIsTheBusinessNamePage, WhatIsTheLeadTrusteesRegisteredNamePage, WhatIsTheUtrPage, WhatIsTheirAddressNonUkPage, WhatIsTheirAddressUkPage, WhatIsTheirDateOfBirthPage, WhatIsTheirEmailAddressPage, WhatIsTheirNamePage, WhatIsTheirNationalInsuranceNumberPage, WhatIsTheirNationalityPage, WhatIsTheirTelephoneNumberPage, WhichDetailsCanYouProvidePage, WhoManagesTheTrustPage}
import play.api.mvc.Call

class NavigatorSpec extends SpecBase with ScalaCheckPropertyChecks with Generators {

  val navigator = new Navigator

  def testRouting(currentPage: Page, nextPage: Page, expectedCall: Any) = {
    s"must go from ${currentPage} to ${nextPage}" in {
      forAll(arbitrary[UserAnswers]) {
        answers => navigator.nextPage(currentPage, NormalMode, answers).mustBe(expectedCall)
      }
    }
  }

  "Navigator" - {

    "in Normal mode" - {

      "must go from a page that doesn't exist in the route map to Index" in {

        case object UnknownPage extends Page

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(UnknownPage, NormalMode, answers)
              .mustBe(routes.IndexController.onPageLoad())
        }
      }

      s"must go from ${WhoManagesTheTrustPage} to ${AreYouEnteringDetailsForLeadTrusteePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhoManagesTheTrustPage, NormalMode, answers)
              .mustBe(routes.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${AreYouEnteringDetailsForLeadTrusteePage} to ${WhatIsTheirNamePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(AreYouEnteringDetailsForLeadTrusteePage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirNameController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirNamePage} to ${WhatIsTheirDateOfBirthPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirNamePage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirDateOfBirthController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirDateOfBirthPage} to ${DoTheyHaveANationalInsuranceNumberPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirDateOfBirthPage, NormalMode, answers)
              .mustBe(indLeadRoutes.DoTheyHaveANationalInsuranceNumberController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${DoTheyHaveANationalInsuranceNumberPage} to ${WhatIsTheirNationalInsuranceNumberPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoTheyHaveANationalInsuranceNumberPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirNationalInsuranceNumberController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirNationalInsuranceNumberPage} to ${WhatIsTheirNationalityPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirNationalInsuranceNumberPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirNationalityController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirNationalityPage} to ${WhichDetailsCanYouProvidePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirNationalityPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhichDetailsCanYouProvideController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhichDetailsCanYouProvidePage} to ${WhatIsPassportCountryOfIssuePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhichDetailsCanYouProvidePage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsPassportCountryOfIssueController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsPassportCountryOfIssuePage} to ${WhatIsPassportCountryOfIssuePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsPassportCountryOfIssuePage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsPassportNumberController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsPassportNumberPage} to ${WhatIsExpiryDatePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsPassportNumberPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsExpiryDateController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsExpiryDatePage} to ${WhatIsIdCardCountryOfIssuePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsExpiryDatePage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsIdCardCountryOfIssueController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsIdCardCountryOfIssuePage} to ${WhatIsIdCardNumberPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsIdCardCountryOfIssuePage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsIdCardNumberController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsIdCardNumberPage} to ${WhatIsIdCardExpiryDatePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsIdCardNumberPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsIdCardExpiryDateController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsIdCardExpiryDatePage} to ${IsTheirResidenceInTheUkPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsIdCardExpiryDatePage, NormalMode, answers)
              .mustBe(indLeadRoutes.IsTheirResidenceInTheUkController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${IsTheirResidenceInTheUkPage} to ${WhatIsTheirAddressUkPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(IsTheirResidenceInTheUkPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirAddressUkController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirAddressUkPage} to ${WhatIsTheirAddressNonUkPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirAddressUkPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirAddressNonUkController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirAddressNonUkPage} to ${WhatIsTheirEmailAddressPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirAddressNonUkPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirEmailAddressController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirEmailAddressPage} to ${WhatIsTheirTelephoneNumberPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirEmailAddressPage, NormalMode, answers)
              .mustBe(indLeadRoutes.WhatIsTheirTelephoneNumberController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheirTelephoneNumberPage} to ${IsTrusteeAUkRegisteredBusinessPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheirTelephoneNumberPage, NormalMode, answers)
              .mustBe(companyLeadRoutes.IsTrusteeAUkRegisteredBusinessController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${IsTrusteeAUkRegisteredBusinessPage} to ${WhatIsTheLeadTrusteesRegisteredNamePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(IsTrusteeAUkRegisteredBusinessPage, NormalMode, answers)
              .mustBe(companyLeadRoutes.WhatIsTheLeadTrusteesRegisteredNameController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheLeadTrusteesRegisteredNamePage} to ${WhatIsTheBusinessNamePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheLeadTrusteesRegisteredNamePage, NormalMode, answers)
              .mustBe(companyLeadRoutes.WhatIsTheBusinessNameController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheBusinessNamePage} to ${WhatIsTheUtrPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheBusinessNamePage, NormalMode, answers)
              .mustBe(companyLeadRoutes.WhatIsTheUtrController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsTheUtrPage} to ${IsHeadOfficeInUkPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsTheUtrPage, NormalMode, answers)
              .mustBe(companyLeadRoutes.IsHeadOfficeInUkController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${IsHeadOfficeInUkPage} to ${WhatIsHeadOfficeAddressUkPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(IsHeadOfficeInUkPage, NormalMode, answers)
              .mustBe(companyLeadRoutes.WhatIsHeadOfficeAddressUkController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsHeadOfficeAddressUkPage} to ${WhatIsHeadOfficeAddressNonUkPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsHeadOfficeAddressUkPage, NormalMode, answers)
              .mustBe(companyLeadRoutes.WhatIsHeadOfficeAddressNonUkController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsHeadOfficeAddressNonUkPage} to ${DoYouKnowCountryOfNationalityPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsHeadOfficeAddressNonUkPage, NormalMode, answers)
              .mustBe(indRoutes.DoYouKnowCountryOfNationalityController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${DoYouKnowCountryOfNationalityPage} to ${DoYouKnowCountryOfResidencyPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowCountryOfNationalityPage, NormalMode, answers)
              .mustBe(indRoutes.DoYouKnowCountryOfResidencyController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${DoYouKnowCountryOfResidencyPage} to ${DoYouKnowHeadOfficeCountryPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowCountryOfResidencyPage, NormalMode, answers)
              .mustBe(companyRoutes.DoYouKnowHeadOfficeCountryController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${DoYouKnowHeadOfficeCountryPage} to ${CheckYourAnswersPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowHeadOfficeCountryPage, NormalMode, answers)
              .mustBe(routes.CheckYourAnswersController.onPageLoad())
        }
      }

      s"must go from ${CheckYourAnswersPage} to AddATrustee" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(CheckYourAnswersPage, NormalMode, answers)
              .mustBe(routes.AddATrusteeeController.onPageLoad())
        }
      }

    }

    "in Check mode" - {

      "must go from a page that doesn't exist in the edit route map  to Check Your Answers" in {

        case object UnknownPage extends Page

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(UnknownPage, CheckMode, answers)
              .mustBe(routes.CheckYourAnswersController.onPageLoad())
        }
      }
    }
  }
}
