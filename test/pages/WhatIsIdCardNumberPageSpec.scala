package pages

import pages.behaviours.PageBehaviours


class WhatIsIdCardNumberPageSpec extends PageBehaviours {

  "WhatIsIdCardNumberPage" - {

    beRetrievable[String](WhatIsIdCardNumberPage)

    beSettable[String](WhatIsIdCardNumberPage)

    beRemovable[String](WhatIsIdCardNumberPage)
  }
}
