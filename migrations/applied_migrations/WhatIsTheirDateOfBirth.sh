#!/bin/bash

echo ""
echo "Applying migration WhatIsTheirDateOfBirth"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheirDateOfBirth                  controllers.WhatIsTheirDateOfBirthController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheirDateOfBirth                  controllers.WhatIsTheirDateOfBirthController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheirDateOfBirth                        controllers.WhatIsTheirDateOfBirthController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheirDateOfBirth                        controllers.WhatIsTheirDateOfBirthController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.title = WhatIsTheirDateOfBirth" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.heading = WhatIsTheirDateOfBirth" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.hint = For example, 12 11 2007" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.checkYourAnswersLabel = WhatIsTheirDateOfBirth" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.error.required.all = Enter the whatIsTheirDateOfBirth" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.error.required.two = The whatIsTheirDateOfBirth" must include {0} and {1} >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.error.required = The whatIsTheirDateOfBirth must include {0}" >> ../conf/messages.en
echo "whatIsTheirDateOfBirth.error.invalid = Enter a real WhatIsTheirDateOfBirth" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirDateOfBirthUserAnswersEntry: Arbitrary[(WhatIsTheirDateOfBirthPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheirDateOfBirthPage.type]";\
    print "        value <- arbitrary[Int].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheirDateOfBirthPage: Arbitrary[WhatIsTheirDateOfBirthPage.type] =";\
    print "    Arbitrary(WhatIsTheirDateOfBirthPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheirDateOfBirthPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheirDateOfBirth: Option[Row] = userAnswers.get(WhatIsTheirDateOfBirthPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheirDateOfBirth.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(Literal(answer.format(dateFormatter))),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheirDateOfBirthController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheirDateOfBirth.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheirDateOfBirth completed"
