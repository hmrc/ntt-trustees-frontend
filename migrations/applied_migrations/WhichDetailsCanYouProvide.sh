#!/bin/bash

echo ""
echo "Applying migration WhichDetailsCanYouProvide"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whichDetailsCanYouProvide                        controllers.individual.lead.WhichDetailsCanYouProvideController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whichDetailsCanYouProvide                        controllers.individual.lead.WhichDetailsCanYouProvideController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhichDetailsCanYouProvide                  controllers.individual.lead.WhichDetailsCanYouProvideController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhichDetailsCanYouProvide                  controllers.individual.lead.WhichDetailsCanYouProvideController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whichDetailsCanYouProvide.title = whichDetailsCanYouProvide" >> ../conf/messages.en
echo "whichDetailsCanYouProvide.heading = whichDetailsCanYouProvide" >> ../conf/messages.en
echo "whichDetailsCanYouProvide.checkYourAnswersLabel = whichDetailsCanYouProvide" >> ../conf/messages.en
echo "whichDetailsCanYouProvide.error.required = Select yes if whichDetailsCanYouProvide" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhichDetailsCanYouProvideUserAnswersEntry: Arbitrary[(WhichDetailsCanYouProvidePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhichDetailsCanYouProvidePage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhichDetailsCanYouProvidePage: Arbitrary[WhichDetailsCanYouProvidePage.type] =";\
    print "    Arbitrary(WhichDetailsCanYouProvidePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhichDetailsCanYouProvidePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whichDetailsCanYouProvide: Option[Row] = userAnswers.get(WhichDetailsCanYouProvidePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whichDetailsCanYouProvide.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhichDetailsCanYouProvideController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whichDetailsCanYouProvide.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhichDetailsCanYouProvide completed"
