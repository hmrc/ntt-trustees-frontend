package pages

import pages.behaviours.PageBehaviours


class WhatIsPassportCountryOfIssuePageSpec extends PageBehaviours {

  "WhatIsPassportCountryOfIssuePage" - {

    beRetrievable[String](WhatIsPassportCountryOfIssuePage)

    beSettable[String](WhatIsPassportCountryOfIssuePage)

    beRemovable[String](WhatIsPassportCountryOfIssuePage)
  }
}
