/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex03CommonCode extends TestSuite {

  /* Doesn't work!
  val tests1 = Tests {
    for (fileName <- Seq("hello", "world", "i", "am", "cow")) {
      fileName - {
        // lots of code using fileName
      }
    }
  }
   */

  val tests = Tests {

    def runTestChecks(fileName: String) = {
      // lots of code using fileName
      fileName
    }

    test("hello") { runTestChecks("hello") }
    test("world") { runTestChecks("world") }
    test("i") { runTestChecks("i") }
    test("am") { runTestChecks("am") }
    test("cow") { runTestChecks("cow") }
  }
}
