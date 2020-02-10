package utest.readme

import utest._

object ArrowAsserts extends TestSuite {

  val tests = Tests {

    test("test1") {
      1 ==> 1                           // passes
      Array(1, 2, 3) ==> Array(1, 2, 3) // passes
      try {
        1 ==> 2 // throws
      } catch {
        case e: java.lang.AssertionError =>
          e
      }
    }
  }
}
