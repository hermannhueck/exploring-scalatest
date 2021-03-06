/*
  See: https://github.com/monix/minitest
 */

package tutorial

import minitest._

object MySimpleSuite extends SimpleTestSuite {

  test("should be") {
    assertEquals(2, 1 + 1)
  }

  test("should not be") {
    assert(1 + 1 != 3)
  }

  test("should throw") {

    class DummyException extends RuntimeException("DUMMY")

    def test(): String = throw new DummyException

    intercept[DummyException] {
      val s = test()
      println(s)
    }
  }

  test("test result of") {
    assertResult("hello world") {
      "hello" + " " + "world"
    }
  }
}
