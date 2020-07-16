package pages

import pages.behaviours.PageBehaviours


class WhatIsHeadOfficeAddressUkPageSpec extends PageBehaviours {

  "WhatIsHeadOfficeAddressUkPage" - {

    beRetrievable[String](WhatIsHeadOfficeAddressUkPage)

    beSettable[String](WhatIsHeadOfficeAddressUkPage)

    beRemovable[String](WhatIsHeadOfficeAddressUkPage)
  }
}
