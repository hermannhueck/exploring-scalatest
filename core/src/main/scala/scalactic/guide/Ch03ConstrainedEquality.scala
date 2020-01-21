package scalactic.guide

import scala.util.chaining._

import org.scalactic._
import TripleEquals._

object Ch03ConstrainedEquality extends util.App {

  (Some(1) === 2)
    .ensuring(_ == false) pipe println

  (1 === 1L)
    .ensuring(_ == true) pipe println

  import TypeCheckedTripleEquals._

  {
    import org.scalatest.Assertions.assertDoesNotCompile

    // (Some(1) === 2) // does not typecheck
    """assertDoesNotCompile("(Some(1) === 2)")""" pipe println
    assertDoesNotCompile("(Some(1) === 2)")

    // (1 === 1L) // does not typecheck
    """assertDoesNotCompile("(1 === 1L)")""" pipe println
    assertDoesNotCompile("(1 === 1L)")
  }

  // compiles with type ascription
  (1 === (1L: AnyVal))
    .ensuring(_ == true) pipe println

  (1L === (1: AnyVal))
    .ensuring(_ == true) pipe println

  // ConversionCheckedTripleEquals no longer available in scalactix 3.1.0
  /*
  import ConversionCheckedTripleEquals._

  (1 === 1L)
    .ensuring(_ == true) pipe println

  (1L === 1)
    .ensuring(_ == true) pipe println
   */

  // List(1, 2, 3) === Vector(1, 2, 3) // does not compile

  import TraversableEqualityConstraints._

  // compiles with TraversableEqualityConstraints
  (List(1, 2, 3) === Vector(1, 2, 3))
    .ensuring(_ == true) pipe println
}
