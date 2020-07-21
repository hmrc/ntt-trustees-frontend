#!/bin/bash

echo ""
echo "Applying migration WhatIsTheLeadTrusteesRegisteredName"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheLeadTrusteesRegisteredName                        controllers.company.lead.WhatIsTheLeadTrusteesRegisteredNameController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheLeadTrusteesRegisteredName                        controllers.company.lead.WhatIsTheLeadTrusteesRegisteredNameController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheLeadTrusteesRegisteredName                  controllers.company.lead.WhatIsTheLeadTrusteesRegisteredNameController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheLeadTrusteesRegisteredName                  controllers.company.lead.WhatIsTheLeadTrusteesRegisteredNameController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesRegisteredName.title = whatIsTheLeadTrusteesRegisteredName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesRegisteredName.heading = whatIsTheLeadTrusteesRegisteredName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesRegisteredName.checkYourAnswersLabel = whatIsTheLeadTrusteesRegisteredName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesRegisteredName.error.required = Enter whatIsTheLeadTrusteesRegisteredName" >> ../conf/messages.en
echo "whatIsTheLeadTrusteesRegisteredName.error.length = WhatIsTheLeadTrusteesRegisteredName must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheLeadTrusteesRegisteredNameUserAnswersEntry: Arbitrary[(WhatIsTheLeadTrusteesRegisteredNamePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheLeadTrusteesRegisteredNamePage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheLeadTrusteesRegisteredNamePage: Arbitrary[WhatIsTheLeadTrusteesRegisteredNamePage.type] =";\
    print "    Arbitrary(WhatIsTheLeadTrusteesRegisteredNamePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheLeadTrusteesRegisteredNamePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheLeadTrusteesRegisteredName: Option[Row] = userAnswers.get(WhatIsTheLeadTrusteesRegisteredNamePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheLeadTrusteesRegisteredName.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheLeadTrusteesRegisteredNameController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheLeadTrusteesRegisteredName.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheLeadTrusteesRegisteredName completed"
