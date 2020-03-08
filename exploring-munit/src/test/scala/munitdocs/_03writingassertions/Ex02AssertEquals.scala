package munitdocs
package _03writingassertions

class Ex02AssertEquals extends munit.FunSuite {

  val a = 1
  val b = 2

  test("assertEquals_01") {
    assertEquals(a, b)
    // munit.FailException: values are not the same
    // => Obtained
    // 1
    // => Diff (- obtained, + expected)
    // -1
    // +2
    //  at munit.Assertions.fail(Assertions.scala:135)
  }

  case class Library(name: String, awesome: Boolean, versions: Range = 0.to(1))
  val munitLibrary = Library("MUnit", true)
  // munitLibrary: Library = Library("MUnit", true, Range(0, 1))
  val mdocLibrary = Library("MDoc", true)
  // mdocLibrary: Library = Library("MDoc", true, Range(0, 1))

  test("assertEquals_02") {
    assertEquals(munitLibrary, mdocLibrary)
    // munit.FailException: values are not the same
    // => Obtained
    // Library(
    //   name = "MUnit",
    //   awesome = true,
    //   versions = Range(
    //     0,
    //     1
    //   )
    // )
    // => Diff (- obtained, + expected)
    //  Library(
    // -  name = "MUnit",
    // +  name = "MDoc",
    //    awesome = true,
    //  at munit.Assertions.fail(Assertions.scala:135)
  }

  test("assertEquals_03") {
    assertEquals(
      Map(1 -> List(1.to(3))),
      Map(1 -> List(1.to(4)))
    )
    // munit.FailException: values are not the same
    // => Obtained
    // Map(
    //   1 -> List(
    //     Range(
    //       1,
    //       2,
    //       3
    //     )
    //   )
    // )
    // => Diff (- obtained, + expected)
    //        2,
    // -      3
    // +      3,
    // +      4
    //      )
    //  at munit.Assertions.fail(Assertions.scala:135)
  }

  test("assertEquals_04") {
    // assertEquals(1, "") // compile error
    // error: Cannot prove that Int =:= String.
    // Error occurred in an application involving default arguments.
    // assertEquals(1, "")
    // ^^^^^^^^^^^^^^^^^^^
  }

  test("assertEquals_05") {
    // assertEquals(Some(1), Option(1)) // compile error
    // error: Cannot prove that Some[Int] =:= Option[Int].
    // Error occurred in an application involving default arguments.
    // assertEquals(Some(1): Option[Int], Option(1))
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  }

  test("assertEquals_06") {
    // compiles with type ascription
    assertEquals(Some(1): Option[Int], Option(1))
  }

  test("assertNotEquals_01") {
    assertNotEquals(a, a)
    // munit.FailException: values are the same
    //  at munit.Assertions.fail(Assertions.scala:135)
  }

  test("assertNotEquals_02") {
    assertNotEquals(a, b)
  }
}
