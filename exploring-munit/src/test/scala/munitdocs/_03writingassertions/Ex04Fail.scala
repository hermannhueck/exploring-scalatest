package munitdocs
package _03writingassertions

class Ex04Fail extends munit.FunSuite {

  val a = 1
  val b = 2

  test("fail_01") {
    fail("test failed")
    // munit.FailException: test failed
    //  at munit.Assertions.fail(Assertions.scala:135)
  }

  test("fail_02") {
    fail("test failed", clues(a + b))
    // munit.FailException: test failed
    // Clues {
    //   a + b: Int = 3
    // }
    //  at munit.Assertions.fail(Assertions.scala:135)
  }
}
