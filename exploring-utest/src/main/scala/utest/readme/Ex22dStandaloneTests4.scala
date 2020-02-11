/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex22dStandaloneTests4 extends App {

// Run `TestSuite` object directly without using SBT, and use
// its configuration for execution and output formatting
  object MyTestSuite extends TestSuite {

    val tests = Tests {
      test("test1") {
        // throw new Exception("test1")
        "test1"
      }
      test("test2") {
        test("inner") {
          1
        }
      }
      test("test3") {
        val a = List[Byte](1, 2)
        // a(10)
        a(1)
      }
    }
  }

  val results4 = TestRunner.runAndPrint(
    MyTestSuite.tests,
    "MyTestSuiteB",
    executor = MyTestSuite,
    formatter = new utest.framework.Formatter {}
  )
}
