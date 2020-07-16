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
import pages._
import models._

@Singleton
class Navigator @Inject()() {

  private val normalRoutes: Page => UserAnswers => Call = {
    case StartJourneyPage                         => _ => routes.WhoManagesTheTrustController.onPageLoad()
    case WhoManagesTheTrustPage                   => _ => routes.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(NormalMode)
    case AreYouEnteringDetailsForLeadTrusteePage  => _ => routes.WhatIsTheirNameController.onPageLoad(NormalMode)
    case WhatIsTheirNamePage                      => _ => routes.WhatIsTheirDateOfBirthController.onPageLoad(NormalMode)
    case WhatIsTheirDateOfBirthPage               => _ => routes.DoTheyHaveANationalInsuranceNumberController.onPageLoad(NormalMode)
    case DoTheyHaveANationalInsuranceNumberPage   => _ => routes.WhatIsTheirNationalInsuranceNumberController.onPageLoad(NormalMode)
    case WhatIsTheirNationalInsuranceNumberPage   => _ => routes.WhatIsTheirNationalityController.onPageLoad(NormalMode)
    case WhatIsTheirNationalityPage               => _ => routes.WhichDetailsCanYouProvideController.onPageLoad(NormalMode)
    case WhichDetailsCanYouProvidePage            => _ => routes.WhatIsPassportCountryOfIssueController.onPageLoad(NormalMode)
    case WhatIsPassportCountryOfIssuePage         => _ => routes.WhatIsPassportNumberController.onPageLoad(NormalMode)
    case WhatIsPassportNumberPage                 => _ => routes.WhatIsExpiryDateController.onPageLoad(NormalMode)
    case WhatIsExpiryDatePage                     => _ => routes.WhatIsIdCardCountryOfIssueController.onPageLoad(NormalMode)
    case WhatIsIdCardCountryOfIssuePage           => _ => routes.WhatIsIdCardNumberController.onPageLoad(NormalMode)
    case WhatIsIdCardNumberPage                   => _ => routes.WhatIsIdCardExpiryDateController.onPageLoad(NormalMode)
    case WhatIsIdCardExpiryDatePage               => _ => routes.IsTheirResidenceInTheUkController.onPageLoad(NormalMode)
    case IsTheirResidenceInTheUkPage              => _ => routes.WhatIsTheirAddressUkController.onPageLoad(NormalMode)
    case WhatIsTheirAddressUkPage                 => _ => routes.WhatIsTheirAddressNonUkController.onPageLoad(NormalMode)
    case WhatIsTheirAddressNonUkPage              => _ => routes.WhatIsTheirEmailAddressController.onPageLoad(NormalMode)
    case WhatIsTheirEmailAddressPage              => _ => routes.WhatIsTheirTelephoneNumberController.onPageLoad(NormalMode)
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
