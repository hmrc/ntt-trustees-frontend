#!/bin/bash

echo ""
echo "Applying migration IsTheirResidenceInTheUk"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /isTheirResidenceInTheUk                        controllers.IsTheirResidenceInTheUkController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /isTheirResidenceInTheUk                        controllers.IsTheirResidenceInTheUkController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeIsTheirResidenceInTheUk                  controllers.IsTheirResidenceInTheUkController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeIsTheirResidenceInTheUk                  controllers.IsTheirResidenceInTheUkController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "isTheirResidenceInTheUk.title = isTheirResidenceInTheUk" >> ../conf/messages.en
echo "isTheirResidenceInTheUk.heading = isTheirResidenceInTheUk" >> ../conf/messages.en
echo "isTheirResidenceInTheUk.checkYourAnswersLabel = isTheirResidenceInTheUk" >> ../conf/messages.en
echo "isTheirResidenceInTheUk.error.required = Select yes if isTheirResidenceInTheUk" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsTheirResidenceInTheUkUserAnswersEntry: Arbitrary[(IsTheirResidenceInTheUkPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[IsTheirResidenceInTheUkPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsTheirResidenceInTheUkPage: Arbitrary[IsTheirResidenceInTheUkPage.type] =";\
    print "    Arbitrary(IsTheirResidenceInTheUkPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(IsTheirResidenceInTheUkPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def isTheirResidenceInTheUk: Option[Row] = userAnswers.get(IsTheirResidenceInTheUkPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"isTheirResidenceInTheUk.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.IsTheirResidenceInTheUkController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"isTheirResidenceInTheUk.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration IsTheirResidenceInTheUk completed"
