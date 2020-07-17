package pages

import pages.behaviours.PageBehaviours

class DoYouKnowHeadOfficeCountryPageSpec extends PageBehaviours {

  "DoYouKnowHeadOfficeCountryPage" - {

    beRetrievable[Boolean](DoYouKnowHeadOfficeCountryPage)

    beSettable[Boolean](DoYouKnowHeadOfficeCountryPage)

    beRemovable[Boolean](DoYouKnowHeadOfficeCountryPage)
  }
}
