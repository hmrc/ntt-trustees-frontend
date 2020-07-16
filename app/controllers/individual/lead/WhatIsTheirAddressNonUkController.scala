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

package controllers.individual.lead

import controllers.CountryLookup
import controllers.actions._
import forms.WhatIsTheirAddressNonUkFormProvider
import javax.inject.Inject
import models.Mode
import navigation.Navigator
import pages.WhatIsTheirAddressNonUkPage
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import renderer.Renderer
import repositories.SessionRepository
import services.CountryService
import uk.gov.hmrc.play.bootstrap.controller.FrontendBaseController
import uk.gov.hmrc.viewmodels.NunjucksSupport

import scala.concurrent.{ExecutionContext, Future}

class WhatIsTheirAddressNonUkController @Inject()(
    override val messagesApi: MessagesApi,
    sessionRepository: SessionRepository,
    navigator: Navigator,
    identify: IdentifierAction,
    getData: DataRetrievalAction,
    requireData: DataRequiredAction,
    formProvider: WhatIsTheirAddressNonUkFormProvider,
    val controllerComponents: MessagesControllerComponents,
    renderer: Renderer,
    override val countryService: CountryService
)(implicit ec: ExecutionContext) extends FrontendBaseController with I18nSupport with NunjucksSupport with CountryLookup {

  private val form = formProvider()

  def onPageLoad(mode: Mode): Action[AnyContent] = (identify andThen getData andThen requireData).async {
    implicit request =>

      val existingValue = request.userAnswers.get(WhatIsTheirAddressNonUkPage)

      val preparedForm = existingValue match {
        case None => form
        case Some(value) => form.fill(value)
      }

      val json = Json.obj(
        "form" -> preparedForm,
        "mode" -> mode,
        "countries" -> countries(
          messages = request2Messages(request),
          selected = existingValue.map(address => address.country),
          excludeUk = true)
      )

      renderer.render("whatIsTheirAddressNonUk.njk", json).map(Ok(_))
  }

  def onSubmit(mode: Mode): Action[AnyContent] = (identify andThen getData andThen requireData).async {
    implicit request =>

      form.bindFromRequest().fold(
        formWithErrors => {

          val json = Json.obj(
            "form" -> formWithErrors,
            "mode" -> mode,
            "countries" -> countries(
              messages = request2Messages(request),
              excludeUk = true)
          )

          renderer.render("whatIsTheirAddressNonUk.njk", json).map(BadRequest(_))
        },
        value =>
          for {
            updatedAnswers <- Future.fromTry(request.userAnswers.set(WhatIsTheirAddressNonUkPage, value))
            _              <- sessionRepository.set(updatedAnswers)
          } yield Redirect(navigator.nextPage(WhatIsTheirAddressNonUkPage, mode, updatedAnswers))
      )
  }
}
