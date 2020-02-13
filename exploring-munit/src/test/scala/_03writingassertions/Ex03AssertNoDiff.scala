package _03writingassertions

class Ex03AssertNoDiff extends munit.FunSuite {

  val obtainedString = "val x = 41\nval y = 43\nval z = 43"
  val expectedString = "val x = 41\nval y = 42\nval z = 43"

  test("assertNoDiff") {
    assertNoDiff(obtainedString, expectedString)
    // munit.FailException: diff assertion failed
    // => Obtained
    //     """|val x = 41
    //        |val y = 43
    //        |val z = 43
    //        |""".stripMargin
    // => Diff (- obtained, + expected)
    //  val x = 41
    // -val y = 43
    // +val y = 42
    //  val z = 43
    //  at munit.Assertions.fail(Assertions.scala:135)
  }
}
