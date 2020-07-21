#!/bin/bash

echo ""
echo "Applying migration WhatIsIdCardExpiryDate"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsIdCardExpiryDate                  controllers.individual.lead.WhatIsIdCardExpiryDateController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsIdCardExpiryDate                  controllers.individual.lead.WhatIsIdCardExpiryDateController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsIdCardExpiryDate                        controllers.individual.lead.WhatIsIdCardExpiryDateController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsIdCardExpiryDate                        controllers.individual.lead.WhatIsIdCardExpiryDateController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.title = WhatIsIdCardExpiryDate" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.heading = WhatIsIdCardExpiryDate" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.hint = For example, 12 11 2007" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.checkYourAnswersLabel = WhatIsIdCardExpiryDate" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.error.required.all = Enter the whatIsIdCardExpiryDate" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.error.required.two = The whatIsIdCardExpiryDate" must include {0} and {1} >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.error.required = The whatIsIdCardExpiryDate must include {0}" >> ../conf/messages.en
echo "whatIsIdCardExpiryDate.error.invalid = Enter a real WhatIsIdCardExpiryDate" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsIdCardExpiryDateUserAnswersEntry: Arbitrary[(WhatIsIdCardExpiryDatePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsIdCardExpiryDatePage.type]";\
    print "        value <- arbitrary[Int].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsIdCardExpiryDatePage: Arbitrary[WhatIsIdCardExpiryDatePage.type] =";\
    print "    Arbitrary(WhatIsIdCardExpiryDatePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsIdCardExpiryDatePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsIdCardExpiryDate: Option[Row] = userAnswers.get(WhatIsIdCardExpiryDatePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsIdCardExpiryDate.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(Literal(answer.format(dateFormatter))),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsIdCardExpiryDateController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsIdCardExpiryDate.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsIdCardExpiryDate completed"
