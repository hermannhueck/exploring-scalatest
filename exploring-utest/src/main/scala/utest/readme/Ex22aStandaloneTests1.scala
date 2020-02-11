/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._
import utest.framework._

object Ex22aStandaloneTests1 extends App {

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

  // Run and return results
  val results1: HTree[String, Result] = TestRunner.run(tests)

  println(results1)
}
