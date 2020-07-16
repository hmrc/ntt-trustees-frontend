package pages

import pages.behaviours.PageBehaviours


class WhatIsIdCardCountryOfIssuePageSpec extends PageBehaviours {

  "WhatIsIdCardCountryOfIssuePage" - {

    beRetrievable[String](WhatIsIdCardCountryOfIssuePage)

    beSettable[String](WhatIsIdCardCountryOfIssuePage)

    beRemovable[String](WhatIsIdCardCountryOfIssuePage)
  }
}
