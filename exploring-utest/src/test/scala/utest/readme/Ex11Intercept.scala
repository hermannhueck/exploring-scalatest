/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex11Intercept extends TestSuite {

  val tests = Tests {

    test("test1") {
      val e = intercept[MatchError] {
        (0: Any) match { case _: String => }
      }
      println(e)

      // scala.MatchError: 0 (of class java.lang.Integer)
    }
  }
}
