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

package utils

import java.time.format.DateTimeFormatter

import controllers.routes
import controllers.individual.{routes => indRoutes}
import controllers.individual.lead.{routes => indLeadRoutes}
import controllers.company.{routes => companyRoutes}
import controllers.company.lead.{routes => companyLeadRoutes}
import models.{Address, CheckMode, IdDetailsType, TrusteeType, UserAnswers}
import pages._
import play.api.i18n.Messages
import CheckYourAnswersHelper._
import services.CountryService
import uk.gov.hmrc.viewmodels._
import uk.gov.hmrc.viewmodels.SummaryList._
import uk.gov.hmrc.viewmodels.Text.Literal

class CheckYourAnswersHelper(userAnswers: UserAnswers, countryService: CountryService)(implicit messages: Messages) {

  def doYouKnowHeadOfficeCountry: Option[Row] = userAnswers.get(DoYouKnowHeadOfficeCountryPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowHeadOfficeCountry.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyRoutes.DoYouKnowHeadOfficeCountryController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowHeadOfficeCountry.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowCountryOfResidency: Option[Row] = userAnswers.get(DoYouKnowCountryOfResidencyPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowCountryOfResidency.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indRoutes.DoYouKnowCountryOfResidencyController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowCountryOfResidency.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowCountryOfNationality: Option[Row] = userAnswers.get(DoYouKnowCountryOfNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowCountryOfNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indRoutes.DoYouKnowCountryOfNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowCountryOfNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheUtr: Option[Row] = userAnswers.get(WhatIsTheUtrPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheUtr.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.WhatIsTheUtrController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheUtr.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheLeadTrusteesRegisteredName: Option[Row] = userAnswers.get(WhatIsTheLeadTrusteesRegisteredNamePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheLeadTrusteesRegisteredName.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.WhatIsTheLeadTrusteesRegisteredNameController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheLeadTrusteesRegisteredName.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheBusinessName: Option[Row] = userAnswers.get(WhatIsTheBusinessNamePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheBusinessName.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.WhatIsTheBusinessNameController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheBusinessName.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsHeadOfficeAddressUk: Option[Row] = userAnswers.get(WhatIsHeadOfficeAddressUkPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsHeadOfficeAddressUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(address(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.WhatIsHeadOfficeAddressUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsHeadOfficeAddressUk.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsHeadOfficeAddressNonUk: Option[Row] = userAnswers.get(WhatIsHeadOfficeAddressNonUkPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsHeadOfficeAddressNonUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(address(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.WhatIsHeadOfficeAddressNonUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsHeadOfficeAddressNonUk.checkYourAnswersLabel"))
          )
        )
      )
  }

  def isTrusteeAUkRegisteredBusiness: Option[Row] = userAnswers.get(IsTrusteeAUkRegisteredBusinessPage) map {
    answer =>
      Row(
        key     = Key(msg"isTrusteeAUkRegisteredBusiness.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.IsTrusteeAUkRegisteredBusinessController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"isTrusteeAUkRegisteredBusiness.checkYourAnswersLabel"))
          )
        )
      )
  }

  def isHeadOfficeInUk: Option[Row] = userAnswers.get(IsHeadOfficeInUkPage) map {
    answer =>
      Row(
        key     = Key(msg"isHeadOfficeInUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = companyLeadRoutes.IsHeadOfficeInUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"isHeadOfficeInUk.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsPassportNumber: Option[Row] = userAnswers.get(WhatIsPassportNumberPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsPassportNumber.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsPassportNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsPassportNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsPassportCountryOfIssue: Option[Row] = userAnswers.get(WhatIsPassportCountryOfIssuePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsPassportCountryOfIssue.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsPassportCountryOfIssueController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsPassportCountryOfIssue.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsIdCardNumber: Option[Row] = userAnswers.get(WhatIsIdCardNumberPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsIdCardNumber.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsIdCardNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsIdCardNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsIdCardExpiryDate: Option[Row] = userAnswers.get(WhatIsIdCardExpiryDatePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsIdCardExpiryDate.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(Literal(answer.format(dateFormatter))),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsIdCardExpiryDateController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsIdCardExpiryDate.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsIdCardCountryOfIssue: Option[Row] = userAnswers.get(WhatIsIdCardCountryOfIssuePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsIdCardCountryOfIssue.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsIdCardCountryOfIssueController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsIdCardCountryOfIssue.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsExpiryDate: Option[Row] = userAnswers.get(WhatIsExpiryDatePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsExpiryDate.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(Literal(answer.format(dateFormatter))),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsExpiryDateController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsExpiryDate.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirNationality: Option[Row] = userAnswers.get(WhatIsTheirNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirName: Option[Row] = userAnswers.get(WhatIsTheirNamePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirName.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirNameController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirName.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whichDetailsCanYouProvide: Option[Row] = userAnswers.get(WhichDetailsCanYouProvidePage) map {
    answer =>
      Row(
        key     = Key(msg"whichDetailsCanYouProvide.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(msg"${IdDetailsType(answer)}"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhichDetailsCanYouProvideController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whichDetailsCanYouProvide.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirTelephoneNumber: Option[Row] = userAnswers.get(WhatIsTheirTelephoneNumberPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirTelephoneNumber.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirTelephoneNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirTelephoneNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirNationalInsuranceNumber: Option[Row] = userAnswers.get(WhatIsTheirNationalInsuranceNumberPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirNationalInsuranceNumber.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirNationalInsuranceNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirNationalInsuranceNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirEmailAddress: Option[Row] = userAnswers.get(WhatIsTheirEmailAddressPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirEmailAddress.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirEmailAddressController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirEmailAddress.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirDateOfBirth: Option[Row] = userAnswers.get(WhatIsTheirDateOfBirthPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirDateOfBirth.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(Literal(answer.format(dateFormatter))),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirDateOfBirthController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirDateOfBirth.checkYourAnswersLabel"))
          )
        )
      )
  }


  def whatIsTheirAddressUk: Option[Row] = userAnswers.get(WhatIsTheirAddressUkPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirAddressUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(address(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirAddressUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirAddressUk.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirAddressNonUk: Option[Row] = userAnswers.get(WhatIsTheirAddressNonUkPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirAddressNonUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(address(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.WhatIsTheirAddressNonUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirAddressNonUk.checkYourAnswersLabel"))
          )
        )
      )
  }


  def isTheirResidenceInTheUk: Option[Row] = userAnswers.get(IsTheirResidenceInTheUkPage) map {
    answer =>
      Row(
        key     = Key(msg"isTheirResidenceInTheUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.IsTheirResidenceInTheUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"isTheirResidenceInTheUk.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowTheirEmailAddress: Option[Row] = userAnswers.get(DoYouKnowTheirEmailAddressPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowTheirEmailAddress.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.DoYouKnowTheirEmailAddressController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowTheirEmailAddress.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doTheyHaveANationalInsuranceNumber: Option[Row] = userAnswers.get(DoTheyHaveANationalInsuranceNumberPage) map {
    answer =>
      Row(
        key     = Key(msg"doTheyHaveANationalInsuranceNumber.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = indLeadRoutes.DoTheyHaveANationalInsuranceNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doTheyHaveANationalInsuranceNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def areYouEnteringDetailsForLeadTrustee: Option[Row] = userAnswers.get(AreYouEnteringDetailsForLeadTrusteePage) map {
    answer =>
      Row(
        key     = Key(msg"areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(msg"${TrusteeType(answer)}"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel"))
          )
        )
      )
  }

  private def country(code: String): Content =
    lit"${countryService.getCountryByCode(code).getOrElse("")}"

  private def address(answer: Address): Content = {
     val a =  s"${answer.addressLine1}, " +
       s"${answer.addressLine2}, " +
       s"${answer.addressLine3.map(s => s"$s, ").getOrElse("")}" +
       s"${answer.addressLine4.map(s => s"$s, ").getOrElse("")}" +
       s"${answer.postcode.map(s => s"$s, ").getOrElse("")}" +
       s"${countryService.getCountryByCode(answer.country).getOrElse("")}"

    lit"$a"
  }

  private def yesOrNo(answer: Boolean): Content =
    if (answer) {
      msg"site.yes"
    } else {
      msg"site.no"
    }
}

object CheckYourAnswersHelper {

  private val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
}
