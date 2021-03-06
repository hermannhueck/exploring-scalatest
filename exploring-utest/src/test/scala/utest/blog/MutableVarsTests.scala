/*
  See: http://www.lihaoyi.com/post/uTesttheEssentialTestFrameworkforScala.html
 */

package utest.blog

import utest._

object MutableVarsTests extends TestSuite {

  val tests = Tests {
    var x = 0
    "outer1" - {
      x += 1
      "inner1" - {
        x += 2
        assert(x == 3) // 0 + 1 + 2
        x
      }
      "inner2" - {
        x += 3
        assert(x == 4) // 0 + 1 + 3
        x
      }
    }
    "outer2" - {
      x += 4
      "inner3" - {
        x += 5
        assert(x == 9) // 0 + 4 + 5
        x
      }
    }
  }
}
