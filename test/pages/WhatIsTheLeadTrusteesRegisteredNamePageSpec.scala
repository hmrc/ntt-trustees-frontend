package pages

import pages.behaviours.PageBehaviours


class WhatIsTheLeadTrusteesRegisteredNamePageSpec extends PageBehaviours {

  "WhatIsTheLeadTrusteesRegisteredNamePage" - {

    beRetrievable[String](WhatIsTheLeadTrusteesRegisteredNamePage)

    beSettable[String](WhatIsTheLeadTrusteesRegisteredNamePage)

    beRemovable[String](WhatIsTheLeadTrusteesRegisteredNamePage)
  }
}
