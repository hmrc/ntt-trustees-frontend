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

package models

import models.IdDetailsType.Value
import org.scalatest.{FreeSpec, MustMatchers}

class IdDetailsSpec extends FreeSpec with MustMatchers {

  val passport = "passport"
  val idcard = "idcards"

  val passportMsg = "whichDetailsCanYouProvide.passport"
  val idcardMsg = "whichDetailsCanYouProvide.idcard"


  ".apply" - {
    "must return the correct IdDetails from a string" in {
      IdDetailsType(idcard) mustEqual IdDetailsType.IdCard
      IdDetailsType(passport) mustEqual IdDetailsType.Passport
    }
  }

  ".toString" - {
    "must return the correct message String from IdDetails" in {
      IdDetailsType.IdCard.toString mustEqual idcardMsg
      IdDetailsType.Passport.toString mustEqual passportMsg
    }
  }
}
