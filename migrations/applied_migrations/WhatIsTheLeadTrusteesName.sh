#!/bin/bash

echo ""
echo "Applying migration WhatIsTheLeadTrusteesName"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheLeadTrusteesName                        controllers.WhatIsTheLeadTrusteesNameController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheLeadTrusteesName                        controllers.WhatIsTheLeadTrusteesNameController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheLeadTrusteesName                  controllers.WhatIsTheLeadTrusteesNameController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheLeadTrusteesName                  controllers.WhatIsTheLeadTrusteesNameController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesName.title = whatIsTheLeadTrusteesName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesName.heading = whatIsTheLeadTrusteesName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesName.checkYourAnswersLabel = whatIsTheLeadTrusteesName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesName.error.required = Enter whatIsTheLeadTrusteesName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesName.error.length = WhatIsTheLeadTrusteesName must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheLeadTrusteesNameUserAnswersEntry: Arbitrary[(WhatIsTheLeadTrusteesNamePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheLeadTrusteesNamePage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheLeadTrusteesNamePage: Arbitrary[WhatIsTheLeadTrusteesNamePage.type] =";\
    print "    Arbitrary(WhatIsTheLeadTrusteesNamePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheLeadTrusteesNamePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheLeadTrusteesName: Option[Row] = userAnswers.get(WhatIsTheLeadTrusteesNamePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheLeadTrusteesName.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheLeadTrusteesNameController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheLeadTrusteesName.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheLeadTrusteesName completed"
