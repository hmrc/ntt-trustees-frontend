#!/bin/bash

echo ""
echo "Applying migration DoYouKnowHeadOfficeCountry"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doYouKnowHeadOfficeCountry                        controllers.company.DoYouKnowHeadOfficeCountryController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doYouKnowHeadOfficeCountry                        controllers.company.DoYouKnowHeadOfficeCountryController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeDoYouKnowHeadOfficeCountry                  controllers.company.DoYouKnowHeadOfficeCountryController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeDoYouKnowHeadOfficeCountry                  controllers.company.DoYouKnowHeadOfficeCountryController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doYouKnowHeadOfficeCountry.title = doYouKnowHeadOfficeCountry" >> ../conf/messages.en
echo "doYouKnowHeadOfficeCountry.heading = doYouKnowHeadOfficeCountry" >> ../conf/messages.en
echo "doYouKnowHeadOfficeCountry.checkYourAnswersLabel = doYouKnowHeadOfficeCountry" >> ../conf/messages.en
echo "doYouKnowHeadOfficeCountry.error.required = Select yes if doYouKnowHeadOfficeCountry" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouKnowHeadOfficeCountryUserAnswersEntry: Arbitrary[(DoYouKnowHeadOfficeCountryPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[DoYouKnowHeadOfficeCountryPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouKnowHeadOfficeCountryPage: Arbitrary[DoYouKnowHeadOfficeCountryPage.type] =";\
    print "    Arbitrary(DoYouKnowHeadOfficeCountryPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(DoYouKnowHeadOfficeCountryPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def doYouKnowHeadOfficeCountry: Option[Row] = userAnswers.get(DoYouKnowHeadOfficeCountryPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"doYouKnowHeadOfficeCountry.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.DoYouKnowHeadOfficeCountryController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"doYouKnowHeadOfficeCountry.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration DoYouKnowHeadOfficeCountry completed"
