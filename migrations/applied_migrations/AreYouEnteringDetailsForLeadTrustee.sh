#!/bin/bash

echo ""
echo "Applying migration AreYouEnteringDetailsForLeadTrustee"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /areYouEnteringDetailsForLeadTrustee                        controllers.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /areYouEnteringDetailsForLeadTrustee                        controllers.AreYouEnteringDetailsForLeadTrusteeController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeAreYouEnteringDetailsForLeadTrustee                  controllers.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeAreYouEnteringDetailsForLeadTrustee                  controllers.AreYouEnteringDetailsForLeadTrusteeController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "areYouEnteringDetailsForLeadTrustee.title = areYouEnteringDetailsForLeadTrustee" >> ../conf/messages.en
echo "areYouEnteringDetailsForLeadTrustee.heading = areYouEnteringDetailsForLeadTrustee" >> ../conf/messages.en
echo "areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel = areYouEnteringDetailsForLeadTrustee" >> ../conf/messages.en
echo "areYouEnteringDetailsForLeadTrustee.error.required = Select yes if areYouEnteringDetailsForLeadTrustee" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryAreYouEnteringDetailsForLeadTrusteeUserAnswersEntry: Arbitrary[(AreYouEnteringDetailsForLeadTrusteePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[AreYouEnteringDetailsForLeadTrusteePage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryAreYouEnteringDetailsForLeadTrusteePage: Arbitrary[AreYouEnteringDetailsForLeadTrusteePage.type] =";\
    print "    Arbitrary(AreYouEnteringDetailsForLeadTrusteePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(AreYouEnteringDetailsForLeadTrusteePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def areYouEnteringDetailsForLeadTrustee: Option[Row] = userAnswers.get(AreYouEnteringDetailsForLeadTrusteePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.AreYouEnteringDetailsForLeadTrusteeController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"areYouEnteringDetailsForLeadTrustee.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration AreYouEnteringDetailsForLeadTrustee completed"
