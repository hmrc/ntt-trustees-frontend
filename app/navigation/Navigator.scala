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

import javax.inject.{Inject, Singleton}

import play.api.mvc.Call

import controllers.routes
import controllers.individual.{routes => indRoutes}
import controllers.individual.lead.{routes => indLeadRoutes}
import controllers.company.{routes => companyRoutes}
import controllers.company.lead.{routes => companyLeadRoutes}
import pages._
import models._

@Singleton
class Navigator @Inject()() {

  private val normalRoutes: Page => UserAnswers => Call = {
    case StartJourneyPage                         => _ => routes.WhoManagesTheTrustController.onPageLoad()
    case WhoManagesTheTrustPage                   => _ => routes.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(NormalMode)

    case AreYouEnteringDetailsForLeadTrusteePage  => _ => indLeadRoutes.WhatIsTheirNameController.onPageLoad(NormalMode)
    case WhatIsTheirNamePage                      => _ => indLeadRoutes.WhatIsTheirDateOfBirthController.onPageLoad(NormalMode)
    case WhatIsTheirDateOfBirthPage               => _ => indLeadRoutes.DoTheyHaveANationalInsuranceNumberController.onPageLoad(NormalMode)
    case DoTheyHaveANationalInsuranceNumberPage   => _ => indLeadRoutes.WhatIsTheirNationalInsuranceNumberController.onPageLoad(NormalMode)
    case WhatIsTheirNationalInsuranceNumberPage   => _ => indLeadRoutes.WhatIsTheirNationalityController.onPageLoad(NormalMode)
    case WhatIsTheirNationalityPage               => _ => indLeadRoutes.WhichDetailsCanYouProvideController.onPageLoad(NormalMode)
    case WhichDetailsCanYouProvidePage            => _ => indLeadRoutes.WhatIsPassportCountryOfIssueController.onPageLoad(NormalMode)
    case WhatIsPassportCountryOfIssuePage         => _ => indLeadRoutes.WhatIsPassportNumberController.onPageLoad(NormalMode)
    case WhatIsPassportNumberPage                 => _ => indLeadRoutes.WhatIsExpiryDateController.onPageLoad(NormalMode)
    case WhatIsExpiryDatePage                     => _ => indLeadRoutes.WhatIsIdCardCountryOfIssueController.onPageLoad(NormalMode)
    case WhatIsIdCardCountryOfIssuePage           => _ => indLeadRoutes.WhatIsIdCardNumberController.onPageLoad(NormalMode)
    case WhatIsIdCardNumberPage                   => _ => indLeadRoutes.WhatIsIdCardExpiryDateController.onPageLoad(NormalMode)
    case WhatIsIdCardExpiryDatePage               => _ => indLeadRoutes.IsTheirResidenceInTheUkController.onPageLoad(NormalMode)
    case IsTheirResidenceInTheUkPage              => _ => indLeadRoutes.WhatIsTheirAddressUkController.onPageLoad(NormalMode)
    case WhatIsTheirAddressUkPage                 => _ => indLeadRoutes.WhatIsTheirAddressNonUkController.onPageLoad(NormalMode)
    case WhatIsTheirAddressNonUkPage              => _ => indLeadRoutes.WhatIsTheirEmailAddressController.onPageLoad(NormalMode)
    case WhatIsTheirEmailAddressPage              => _ => indLeadRoutes.WhatIsTheirTelephoneNumberController.onPageLoad(NormalMode)

    case WhatIsTheirTelephoneNumberPage           => _ => companyLeadRoutes.IsTrusteeAUkRegisteredBusinessController.onPageLoad(NormalMode)
    case IsTrusteeAUkRegisteredBusinessPage       => _ => companyLeadRoutes.WhatIsTheLeadTrusteesRegisteredNameController.onPageLoad(NormalMode)
    case WhatIsTheLeadTrusteesRegisteredNamePage  => _ => companyLeadRoutes.WhatIsTheBusinessNameController.onPageLoad(NormalMode)
    case WhatIsTheBusinessNamePage                => _ => companyLeadRoutes.WhatIsTheUtrController.onPageLoad(NormalMode)
    case WhatIsTheUtrPage                         => _ => companyLeadRoutes.IsHeadOfficeInUkController.onPageLoad(NormalMode)
    case IsHeadOfficeInUkPage                     => _ => companyLeadRoutes.WhatIsHeadOfficeAddressUkController.onPageLoad(NormalMode)
    case WhatIsHeadOfficeAddressUkPage            => _ => companyLeadRoutes.WhatIsHeadOfficeAddressNonUkController.onPageLoad(NormalMode)

    case WhatIsHeadOfficeAddressNonUkPage         => _ => indRoutes.DoYouKnowCountryOfNationalityController.onPageLoad(NormalMode)
    case DoYouKnowCountryOfNationalityPage        => _ => indRoutes.DoYouKnowCountryOfResidencyController.onPageLoad(NormalMode)

    case DoYouKnowCountryOfResidencyPage          => _ => companyRoutes.DoYouKnowHeadOfficeCountryController.onPageLoad(NormalMode)

    case DoYouKnowHeadOfficeCountryPage           => _ => routes.CheckYourAnswersController.onPageLoad()
    case CheckYourAnswersPage                     => _ => routes.AddATrusteeeController.onPageLoad()

    case _ => _ => routes.IndexController.onPageLoad()
  }

  private val checkRouteMap: Page => UserAnswers => Call = {
    case _ => _ => routes.CheckYourAnswersController.onPageLoad()
  }

  def nextPage(page: Page, mode: Mode, userAnswers: UserAnswers): Call = mode match {
    case NormalMode =>
      normalRoutes(page)(userAnswers)
    case CheckMode =>
      checkRouteMap(page)(userAnswers)
  }
}
