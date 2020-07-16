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
import models.{CheckMode, UserAnswers}
import pages._
import play.api.i18n.Messages
import CheckYourAnswersHelper._
import uk.gov.hmrc.viewmodels._
import uk.gov.hmrc.viewmodels.SummaryList._
import uk.gov.hmrc.viewmodels.Text.Literal

class CheckYourAnswersHelper(userAnswers: UserAnswers)(implicit messages: Messages) {

  def whatIsPassportNumber: Option[Row] = userAnswers.get(WhatIsPassportNumberPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsPassportNumber.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsPassportNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsPassportNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsPassportCountryOfIssue: Option[Row] = userAnswers.get(WhatIsPassportCountryOfIssuePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsPassportCountryOfIssue.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsPassportCountryOfIssueController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsIdCardNumberController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsIdCardExpiryDateController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsIdCardExpiryDate.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsIdCardCountryOfIssue: Option[Row] = userAnswers.get(WhatIsIdCardCountryOfIssuePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsIdCardCountryOfIssue.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsIdCardCountryOfIssueController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsExpiryDateController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsExpiryDate.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirNationality: Option[Row] = userAnswers.get(WhatIsTheirNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirNationalityController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsTheirNameController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirName.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whichDetailsCanYouProvide: Option[Row] = userAnswers.get(WhichDetailsCanYouProvidePage) map {
    answer =>
      Row(
        key     = Key(msg"whichDetailsCanYouProvide.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhichDetailsCanYouProvideController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsTheirTelephoneNumberController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsTheirNationalInsuranceNumberController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsTheirEmailAddressController.onPageLoad(CheckMode).url,
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
            href               = routes.WhatIsTheirDateOfBirthController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirDateOfBirth.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirCountryOfNationality: Option[Row] = userAnswers.get(WhatIsTheirCountryOfNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirCountryOfNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirCountryOfNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirCountryOfNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirAddressUk: Option[Row] = userAnswers.get(WhatIsTheirAddressUkPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirAddressUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirAddressUkController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirAddressUk.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirAddressNonUk: Option[Row] = userAnswers.get(WhatIsTheirAddressNonUkPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirAddressNonUk.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirAddressNonUkController.onPageLoad(CheckMode).url,
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
            href               = routes.IsTheirResidenceInTheUkController.onPageLoad(CheckMode).url,
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
            href               = routes.DoYouKnowTheirEmailAddressController.onPageLoad(CheckMode).url,
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
            href               = routes.DoTheyHaveANationalInsuranceNumberController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doTheyHaveANationalInsuranceNumber.checkYourAnswersLabel"))
          )
        )
      )
  }

  def areYouEnteringDetailsForLeadTrustee: Option[Row] = userAnswers.get(AreYouEnteringDetailsForLeadTrusteePage) map {
    answer =>
      Row(
        key     = Key(msg"areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel"))
          )
        )
      )
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
