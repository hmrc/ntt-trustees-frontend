#!/bin/bash

echo ""
echo "Applying migration WhatIsTheirCountryOfNationality"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheirCountryOfNationality                        controllers.WhatIsTheirCountryOfNationalityController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheirCountryOfNationality                        controllers.WhatIsTheirCountryOfNationalityController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheirCountryOfNationality                  controllers.WhatIsTheirCountryOfNationalityController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheirCountryOfNationality                  controllers.WhatIsTheirCountryOfNationalityController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheirCountryOfNationality.title = whatIsTheirCountryOfNationality" >> ../conf/messages.en
echo "whatIsTheirCountryOfNationality.heading = whatIsTheirCountryOfNationality" >> ../conf/messages.en
echo "whatIsTheirCountryOfNationality.checkYourAnswersLabel = whatIsTheirCountryOfNationality" >> ../conf/messages.en
echo "whatIsTheirCountryOfNationality.error.required = Enter whatIsTheirCountryOfNationality" >> ../conf/messages.en
echo "whatIsTheirCountryOfNationality.error.length = WhatIsTheirCountryOfNationality must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirCountryOfNationalityUserAnswersEntry: Arbitrary[(WhatIsTheirCountryOfNationalityPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheirCountryOfNationalityPage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirCountryOfNationalityPage: Arbitrary[WhatIsTheirCountryOfNationalityPage.type] =";\
    print "    Arbitrary(WhatIsTheirCountryOfNationalityPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheirCountryOfNationalityPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheirCountryOfNationality: Option[Row] = userAnswers.get(WhatIsTheirCountryOfNationalityPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheirCountryOfNationality.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheirCountryOfNationalityController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheirCountryOfNationality.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheirCountryOfNationality completed"
