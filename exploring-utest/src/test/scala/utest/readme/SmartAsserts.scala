package utest.readme

import utest._

object SmartAsserts extends TestSuite {

  val tests = Tests {

    test("test1") {
      val x = 1
      val y = "2"
      assert(
        x > 0,
        x == y
      )
      // utest.AssertionError: x == y
      // x: Int = 1
      // y: String = 2
    }
    test("test2") {
      val x = 1L
      val y = 0L
      assert(x / y == 10)
      // utest.AssertionError: assert(x / y == 10)
      // caused by: java.lang.ArithmeticException: / by zero
      // x: Long = 1
      // y: Long = 0
    }
  }
}
