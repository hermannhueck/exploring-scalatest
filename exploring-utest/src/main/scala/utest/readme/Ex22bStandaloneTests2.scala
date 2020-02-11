/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex22bStandaloneTests2 extends App {

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

  // Run, return results, and print streaming output with the default formatter
  val results2 = TestRunner.runAndPrint(
    tests,
    "MyTestSuiteA"
  )
}
