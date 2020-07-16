package pages

import pages.behaviours.PageBehaviours


class WhatIsPassportNumberPageSpec extends PageBehaviours {

  "WhatIsPassportNumberPage" - {

    beRetrievable[String](WhatIsPassportNumberPage)

    beSettable[String](WhatIsPassportNumberPage)

    beRemovable[String](WhatIsPassportNumberPage)
  }
}
