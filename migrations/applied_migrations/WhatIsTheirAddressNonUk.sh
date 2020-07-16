#!/bin/bash

echo ""
echo "Applying migration WhatIsTheirAddressNonUk"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheirAddressNonUk                        controllers.individual.lead.WhatIsTheirAddressNonUkController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheirAddressNonUk                        controllers.individual.lead.WhatIsTheirAddressNonUkController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheirAddressNonUk                  controllers.individual.lead.WhatIsTheirAddressNonUkController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheirAddressNonUk                  controllers.individual.lead.WhatIsTheirAddressNonUkController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheirAddressNonUk.title = whatIsTheirAddressNonUk" >> ../conf/messages.en
echo "whatIsTheirAddressNonUk.heading = whatIsTheirAddressNonUk" >> ../conf/messages.en
echo "whatIsTheirAddressNonUk.checkYourAnswersLabel = whatIsTheirAddressNonUk" >> ../conf/messages.en
echo "whatIsTheirAddressNonUk.error.required = Enter whatIsTheirAddressNonUk" >> ../conf/messages.en
echo "whatIsTheirAddressNonUk.error.length = WhatIsTheirAddressNonUk must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirAddressNonUkUserAnswersEntry: Arbitrary[(WhatIsTheirAddressNonUkPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheirAddressNonUkPage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirAddressNonUkPage: Arbitrary[WhatIsTheirAddressNonUkPage.type] =";\
    print "    Arbitrary(WhatIsTheirAddressNonUkPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheirAddressNonUkPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheirAddressNonUk: Option[Row] = userAnswers.get(WhatIsTheirAddressNonUkPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheirAddressNonUk.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheirAddressNonUkController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheirAddressNonUk.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheirAddressNonUk completed"
