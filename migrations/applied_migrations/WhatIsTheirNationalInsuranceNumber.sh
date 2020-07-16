#!/bin/bash

echo ""
echo "Applying migration WhatIsTheirNationalInsuranceNumber"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheirNationalInsuranceNumber                        controllers.WhatIsTheirNationalInsuranceNumberController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheirNationalInsuranceNumber                        controllers.WhatIsTheirNationalInsuranceNumberController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheirNationalInsuranceNumber                  controllers.WhatIsTheirNationalInsuranceNumberController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheirNationalInsuranceNumber                  controllers.WhatIsTheirNationalInsuranceNumberController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheirNationalInsuranceNumber.title = whatIsTheirNationalInsuranceNumber" >> ../conf/messages.en
echo "whatIsTheirNationalInsuranceNumber.heading = whatIsTheirNationalInsuranceNumber" >> ../conf/messages.en
echo "whatIsTheirNationalInsuranceNumber.checkYourAnswersLabel = whatIsTheirNationalInsuranceNumber" >> ../conf/messages.en
echo "whatIsTheirNationalInsuranceNumber.error.required = Enter whatIsTheirNationalInsuranceNumber" >> ../conf/messages.en
echo "whatIsTheirNationalInsuranceNumber.error.length = WhatIsTheirNationalInsuranceNumber must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirNationalInsuranceNumberUserAnswersEntry: Arbitrary[(WhatIsTheirNationalInsuranceNumberPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheirNationalInsuranceNumberPage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirNationalInsuranceNumberPage: Arbitrary[WhatIsTheirNationalInsuranceNumberPage.type] =";\
    print "    Arbitrary(WhatIsTheirNationalInsuranceNumberPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheirNationalInsuranceNumberPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheirNationalInsuranceNumber: Option[Row] = userAnswers.get(WhatIsTheirNationalInsuranceNumberPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheirNationalInsuranceNumber.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheirNationalInsuranceNumberController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheirNationalInsuranceNumber.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheirNationalInsuranceNumber completed"
