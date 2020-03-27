/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex04CommonCodeWithTestPath extends TestSuite {

  @com.github.ghik.silencer.silent("deprecated")
  val tests = Tests {

    def runTestChecks()(implicit path: utest.framework.TestPath) = {
      val fileName = path.value.last
      // lots of code using fileName
      fileName
    }

    test("hello") { runTestChecks() }
    test("world") { runTestChecks() }
    test("i") { runTestChecks() }
    test("am") { runTestChecks() }
    test("cow") { runTestChecks() }
  }
}
