package pages

import pages.behaviours.PageBehaviours

class IsTrusteeAUkRegisteredBusinessPageSpec extends PageBehaviours {

  "IsTrusteeAUkRegisteredBusinessPage" - {

    beRetrievable[Boolean](IsTrusteeAUkRegisteredBusinessPage)

    beSettable[Boolean](IsTrusteeAUkRegisteredBusinessPage)

    beRemovable[Boolean](IsTrusteeAUkRegisteredBusinessPage)
  }
}
