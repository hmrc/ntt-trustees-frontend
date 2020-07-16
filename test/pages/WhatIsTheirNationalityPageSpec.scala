package pages

import pages.behaviours.PageBehaviours


class WhatIsTheirNationalityPageSpec extends PageBehaviours {

  "WhatIsTheirNationalityPage" - {

    beRetrievable[String](WhatIsTheirNationalityPage)

    beSettable[String](WhatIsTheirNationalityPage)

    beRemovable[String](WhatIsTheirNationalityPage)
  }
}
