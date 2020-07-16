package pages

import pages.behaviours.PageBehaviours


class WhatIsTheUtrPageSpec extends PageBehaviours {

  "WhatIsTheUtrPage" - {

    beRetrievable[String](WhatIsTheUtrPage)

    beSettable[String](WhatIsTheUtrPage)

    beRemovable[String](WhatIsTheUtrPage)
  }
}
