package pages

import pages.behaviours.PageBehaviours

class DoYouKnowCountryOfResidencyPageSpec extends PageBehaviours {

  "DoYouKnowCountryOfResidencyPage" - {

    beRetrievable[Boolean](DoYouKnowCountryOfResidencyPage)

    beSettable[Boolean](DoYouKnowCountryOfResidencyPage)

    beRemovable[Boolean](DoYouKnowCountryOfResidencyPage)
  }
}
