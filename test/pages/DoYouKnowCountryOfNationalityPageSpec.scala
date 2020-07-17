package pages

import pages.behaviours.PageBehaviours

class DoYouKnowCountryOfNationalityPageSpec extends PageBehaviours {

  "DoYouKnowCountryOfNationalityPage" - {

    beRetrievable[Boolean](DoYouKnowCountryOfNationalityPage)

    beSettable[Boolean](DoYouKnowCountryOfNationalityPage)

    beRemovable[Boolean](DoYouKnowCountryOfNationalityPage)
  }
}
