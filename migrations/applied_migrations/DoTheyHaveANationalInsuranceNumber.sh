#!/bin/bash

echo ""
echo "Applying migration DoTheyHaveANationalInsuranceNumber"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doTheyHaveANationalInsuranceNumber                        controllers.DoTheyHaveANationalInsuranceNumberController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doTheyHaveANationalInsuranceNumber                        controllers.DoTheyHaveANationalInsuranceNumberController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeDoTheyHaveANationalInsuranceNumber                  controllers.DoTheyHaveANationalInsuranceNumberController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeDoTheyHaveANationalInsuranceNumber                  controllers.DoTheyHaveANationalInsuranceNumberController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doTheyHaveANationalInsuranceNumber.title = doTheyHaveANationalInsuranceNumber" >> ../conf/messages.en
echo "doTheyHaveANationalInsuranceNumber.heading = doTheyHaveANationalInsuranceNumber" >> ../conf/messages.en
echo "doTheyHaveANationalInsuranceNumber.checkYourAnswersLabel = doTheyHaveANationalInsuranceNumber" >> ../conf/messages.en
echo "doTheyHaveANationalInsuranceNumber.error.required = Select yes if doTheyHaveANationalInsuranceNumber" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoTheyHaveANationalInsuranceNumberUserAnswersEntry: Arbitrary[(DoTheyHaveANationalInsuranceNumberPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[DoTheyHaveANationalInsuranceNumberPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoTheyHaveANationalInsuranceNumberPage: Arbitrary[DoTheyHaveANationalInsuranceNumberPage.type] =";\
    print "    Arbitrary(DoTheyHaveANationalInsuranceNumberPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(DoTheyHaveANationalInsuranceNumberPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def doTheyHaveANationalInsuranceNumber: Option[Row] = userAnswers.get(DoTheyHaveANationalInsuranceNumberPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"doTheyHaveANationalInsuranceNumber.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.DoTheyHaveANationalInsuranceNumberController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"doTheyHaveANationalInsuranceNumber.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration DoTheyHaveANationalInsuranceNumber completed"
