/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex01HelloTests extends TestSuite {

  val tests = Tests {
    test("test1") {
      throw new Exception("test1")
    }
    test("test2") {
      1
    }
    test("test3") {
      val a = List[Byte](1, 2)
      a(10)
    }
  }
}
