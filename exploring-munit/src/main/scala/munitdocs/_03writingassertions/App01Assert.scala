package munitdocs
package _03writingassertions

import munit.Assertions._

object App01Assert extends util.App {

  val a = 1
  val b = 2

  // assert(a > b)
  // munit.FailException: assertion failed
  // at munit.Assertions.fail(Assertions.scala:135)

  // assert(a > b, "a was smaller than b")
  // munit.FailException: a was smaller than b
  // at munit.Assertions.fail(Assertions.scala:135)

  // assert(clue(a) > clue(b))
  // munit.FailException: assertion failed
  // Clues {
  //   a: Int = 1
  //   b: Int = 2
  // }
  //  at munit.Assertions.fail(Assertions.scala:135)

  assert(clue(List(a).head) > clue(b))
  // munit.FailException: assertion failed
  // Clues {
  //   List(a).head: Int = 1
  //   b: Int = 2
  // }
  //  at munit.Assertions.fail(Assertions.scala:135)
}
