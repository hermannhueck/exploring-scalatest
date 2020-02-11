/*
  See: https://alexn.org/blog/2017/11/10/minitest-no-crap-scala-library.html
 */

package blog

import minitest.SimpleTestSuite
import scala.concurrent._
import scala.concurrent.duration._

object MySimpleSuite extends SimpleTestSuite {

  test("should be") {
    assertEquals(2, 1 + 1)
  }

  test("should not be") {
    assert(1 + 1 != 3)
  }

  test("should throw") {

    class DummyException extends RuntimeException("DUMMY")

    def test(): String =
      throw new DummyException

    intercept[DummyException] {
      test()
    }
  }

  test("test result of") {
    assertResult("hello world") {
      "hello" + " " + "world"
    }
  }

  test("should be ignored") {

    implicit val ec: ExecutionContext = ExecutionContext.global

    if (Platform.isJS) ignore("Blocking not supported on top of JS")
    val r = Await.result(Future(1), Duration.Inf)
    assertEquals(r, 1)
  }
}
