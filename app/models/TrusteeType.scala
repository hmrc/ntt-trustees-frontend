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

object TrusteeType extends Enumeration {
  type TrusteeType = Value
  val LeadTrustee = Value("areYouEnteringDetailsForLeadTrustee.leadtrustee")
  val Trustee = Value("areYouEnteringDetailsForLeadTrustee.trustee")

  def apply(value: String): TrusteeType = {
    value match {
      case "leadtrustee" => LeadTrustee
      case _ => Trustee
    }
  }
}


