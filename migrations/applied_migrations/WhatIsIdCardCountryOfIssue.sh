#!/bin/bash

echo ""
echo "Applying migration WhatIsIdCardCountryOfIssue"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsIdCardCountryOfIssue                        controllers.individual.lead.WhatIsIdCardCountryOfIssueController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsIdCardCountryOfIssue                        controllers.individual.lead.WhatIsIdCardCountryOfIssueController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsIdCardCountryOfIssue                  controllers.individual.lead.WhatIsIdCardCountryOfIssueController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsIdCardCountryOfIssue                  controllers.individual.lead.WhatIsIdCardCountryOfIssueController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsIdCardCountryOfIssue.title = whatIsIdCardCountryOfIssue" >> ../conf/messages.en
echo "whatIsIdCardCountryOfIssue.heading = whatIsIdCardCountryOfIssue" >> ../conf/messages.en
echo "whatIsIdCardCountryOfIssue.checkYourAnswersLabel = whatIsIdCardCountryOfIssue" >> ../conf/messages.en
echo "whatIsIdCardCountryOfIssue.error.required = Enter whatIsIdCardCountryOfIssue" >> ../conf/messages.en
echo "whatIsIdCardCountryOfIssue.error.length = WhatIsIdCardCountryOfIssue must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsIdCardCountryOfIssueUserAnswersEntry: Arbitrary[(WhatIsIdCardCountryOfIssuePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsIdCardCountryOfIssuePage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsIdCardCountryOfIssuePage: Arbitrary[WhatIsIdCardCountryOfIssuePage.type] =";\
    print "    Arbitrary(WhatIsIdCardCountryOfIssuePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsIdCardCountryOfIssuePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsIdCardCountryOfIssue: Option[Row] = userAnswers.get(WhatIsIdCardCountryOfIssuePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsIdCardCountryOfIssue.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsIdCardCountryOfIssueController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsIdCardCountryOfIssue.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsIdCardCountryOfIssue completed"
