package utest.readme

import utest._

object BeforeAfterEachTest extends TestSuite {

  var x = 0

  // Both utestBeforeEach and utestAfterEach runs inside utestWrap's body callback.

  override def utestBeforeEach(path: Seq[String]): Unit = {
    println(s"on before each x: $x")
    x = 0
  }

  override def utestAfterEach(path: Seq[String]): Unit =
    println(s"on after each x: $x")

  val tests = Tests {
    test("outer1") {
      x += 1
      test("inner1") {
        x += 2
        assert(x == 3) // += 1, += 2
        x
      }
      test("inner2") {
        x += 3
        assert(x == 4) // += 1, += 3
        x
      }
    }
    test("outer2") {
      x += 4
      test("inner3") {
        x += 5
        assert(x == 9) // += 4, += 5
        x
      }
    }
  }
}
