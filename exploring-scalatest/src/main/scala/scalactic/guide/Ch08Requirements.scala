package scalactic.guide

import scala.util.chaining._
import scala.util.Try

object Ch08Requirements extends util.App {

  val length = 5
  val idx    = 6

  Try {
    require(idx >= 0 && idx <= length, s"index, $idx, was less than zero or greater than or equal to length, $length")
  }.fold(_.toString, _.toString) pipe println
  // java.lang.IllegalArgumentException: requirement failed: index, 6, was less than zero or greater than or equal to length, 5

  Try {
    require(idx >= 0 && idx <= length) // message is missing !!!
  }.fold(_.toString, _.toString) pipe println
  // java.lang.IllegalArgumentException: requirement failed

  import org.scalactic._
  import Requirements._

  Try {
    require(idx >= 0 && idx <= length) // message is missing again
  }.fold(_.toString, _.toString) pipe println
  // java.lang.IllegalArgumentException: 6 was greater than or equal to 0, but 6 was not less than or equal to 5

  Try {
    require(idx >= 0 && idx <= length, "(hopefully that helps)")
  }.fold(_.toString, _.toString) pipe println
  // java.lang.IllegalArgumentException: 6 was greater than or equal to 0, but 6 was not less than or equal to 5 (hopefully that helps)

  val connectionOpen = false

  Try {
    requireState(connectionOpen)
  }.fold(_.toString, _.toString) pipe println
  // java.lang.IllegalStateException: connectionOpen was false

  val a                 = 42
  val b                 = true
  val c                 = 42.0
  val d                 = "hello"
  val e: String         = null
  val f: java.util.Date = null

  Try {
    requireNonNull(a, b, c, d, e, f)
  }.fold(_.toString, _.toString) pipe println
  // org.scalactic.exceptions.NullArgumentException: Ch08Requirements.this.e and Ch08Requirements.this.f were null
}
