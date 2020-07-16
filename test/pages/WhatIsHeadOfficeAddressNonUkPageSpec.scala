package pages

import pages.behaviours.PageBehaviours


class WhatIsHeadOfficeAddressNonUkPageSpec extends PageBehaviours {

  "WhatIsHeadOfficeAddressNonUkPage" - {

    beRetrievable[String](WhatIsHeadOfficeAddressNonUkPage)

    beSettable[String](WhatIsHeadOfficeAddressNonUkPage)

    beRemovable[String](WhatIsHeadOfficeAddressNonUkPage)
  }
}
