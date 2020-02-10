package utest.readme

import utest._

object CommonCode extends TestSuite {

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
