package pages

import pages.behaviours.PageBehaviours


class WhatIsTheBusinessNamePageSpec extends PageBehaviours {

  "WhatIsTheBusinessNamePage" - {

    beRetrievable[String](WhatIsTheBusinessNamePage)

    beSettable[String](WhatIsTheBusinessNamePage)

    beRemovable[String](WhatIsTheBusinessNamePage)
  }
}
