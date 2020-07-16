package pages

import pages.behaviours.PageBehaviours

class IsHeadOfficeInUkPageSpec extends PageBehaviours {

  "IsHeadOfficeInUkPage" - {

    beRetrievable[Boolean](IsHeadOfficeInUkPage)

    beSettable[Boolean](IsHeadOfficeInUkPage)

    beRemovable[Boolean](IsHeadOfficeInUkPage)
  }
}
