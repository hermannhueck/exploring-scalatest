package utest.readme

import utest._

object AssertMatch extends TestSuite {

  val tests = Tests {

    test("test1") {
      assertMatch(Seq(1, 2, 3)) { case Seq(1, 2) => }
      // AssertionError: Matching failed Seq(1, 2, 3)
    }
  }
}
