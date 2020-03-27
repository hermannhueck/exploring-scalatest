/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex16TestPathTests extends TestSuite {

  @com.github.ghik.silencer.silent("deprecated")
  val tests = Tests {

    "testPath" - {
      "foo" - {
        assert(implicitly[utest.framework.TestPath].value == Seq("testPath", "foo"))
      }
    }
  }
}
