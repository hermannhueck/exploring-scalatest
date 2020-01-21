package scalactic.guide

import scala.util.chaining._

import org.scalactic._
import TripleEquals._
import Explicitly._

object Ch06ExplicitlyDSL extends util.App {

  val result = "hello"

  "Using Explicitly DSL ..." pipe println

  // In production code:
  if ((result === "hello")(decided by defaultEquality)) true else false

  import org.scalatest._
  import matchers.should.Matchers._
  // In tests:
  result should equal("hello")(decided by defaultEquality)

  val result2 = "HELLO"

  "together with StringNormalizations ..." pipe println
  import StringNormalizations._

  // In production code:
  if ((result2 === "hello")(after being lowerCased)) true else false

  // In tests:
  result2 should equal("hello")(after being lowerCased and trimmed)

}
