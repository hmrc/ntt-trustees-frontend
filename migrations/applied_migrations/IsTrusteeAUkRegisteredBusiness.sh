#!/bin/bash

echo ""
echo "Applying migration IsTrusteeAUkRegisteredBusiness"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /isTrusteeAUkRegisteredBusiness                        controllers.company.lead.IsTrusteeAUkRegisteredBusinessController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /isTrusteeAUkRegisteredBusiness                        controllers.company.lead.IsTrusteeAUkRegisteredBusinessController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeIsTrusteeAUkRegisteredBusiness                  controllers.company.lead.IsTrusteeAUkRegisteredBusinessController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeIsTrusteeAUkRegisteredBusiness                  controllers.company.lead.IsTrusteeAUkRegisteredBusinessController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "isTrusteeAUkRegisteredBusiness.title = isTrusteeAUkRegisteredBusiness" >> ../conf/messages.en
echo "isTrusteeAUkRegisteredBusiness.heading = isTrusteeAUkRegisteredBusiness" >> ../conf/messages.en
echo "isTrusteeAUkRegisteredBusiness.checkYourAnswersLabel = isTrusteeAUkRegisteredBusiness" >> ../conf/messages.en
echo "isTrusteeAUkRegisteredBusiness.error.required = Select yes if isTrusteeAUkRegisteredBusiness" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsTrusteeAUkRegisteredBusinessUserAnswersEntry: Arbitrary[(IsTrusteeAUkRegisteredBusinessPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[IsTrusteeAUkRegisteredBusinessPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsTrusteeAUkRegisteredBusinessPage: Arbitrary[IsTrusteeAUkRegisteredBusinessPage.type] =";\
    print "    Arbitrary(IsTrusteeAUkRegisteredBusinessPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(IsTrusteeAUkRegisteredBusinessPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def isTrusteeAUkRegisteredBusiness: Option[Row] = userAnswers.get(IsTrusteeAUkRegisteredBusinessPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"isTrusteeAUkRegisteredBusiness.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.IsTrusteeAUkRegisteredBusinessController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"isTrusteeAUkRegisteredBusiness.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration IsTrusteeAUkRegisteredBusiness completed"
