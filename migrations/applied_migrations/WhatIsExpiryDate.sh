#!/bin/bash

echo ""
echo "Applying migration WhatIsExpiryDate"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsExpiryDate                  controllers.WhatIsExpiryDateController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsExpiryDate                  controllers.WhatIsExpiryDateController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsExpiryDate                        controllers.WhatIsExpiryDateController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsExpiryDate                        controllers.WhatIsExpiryDateController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsExpiryDate.title = WhatIsExpiryDate" >> ../conf/messages.en
echo "whatIsExpiryDate.heading = WhatIsExpiryDate" >> ../conf/messages.en
echo "whatIsExpiryDate.hint = For example, 12 11 2007" >> ../conf/messages.en
echo "whatIsExpiryDate.checkYourAnswersLabel = WhatIsExpiryDate" >> ../conf/messages.en
echo "whatIsExpiryDate.error.required.all = Enter the whatIsExpiryDate" >> ../conf/messages.en
echo "whatIsExpiryDate.error.required.two = The whatIsExpiryDate" must include {0} and {1} >> ../conf/messages.en
echo "whatIsExpiryDate.error.required = The whatIsExpiryDate must include {0}" >> ../conf/messages.en
echo "whatIsExpiryDate.error.invalid = Enter a real WhatIsExpiryDate" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsExpiryDateUserAnswersEntry: Arbitrary[(WhatIsExpiryDatePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsExpiryDatePage.type]";\
    print "        value <- arbitrary[Int].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsExpiryDatePage: Arbitrary[WhatIsExpiryDatePage.type] =";\
    print "    Arbitrary(WhatIsExpiryDatePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsExpiryDatePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsExpiryDate: Option[Row] = userAnswers.get(WhatIsExpiryDatePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsExpiryDate.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(Literal(answer.format(dateFormatter))),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsExpiryDateController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsExpiryDate.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsExpiryDate completed"
