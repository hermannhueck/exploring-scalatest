package scalactic.guide

import scala.util.chaining._

import org.scalactic._

object Ch05Normalization extends util.App {

  val truncated =
    new Normalization[Double] {
      def normalized(d: Double) = d.floor
    }

  // import org.scalatest._
  // import Matchers._
  // import TypeCheckedTripleEquals._
  // (2.1 should ===(2.0))(after being truncated)
  //.ensuring(_ == false) pipe println

  implicit val doubleNormalization = truncated
  import NormMethods._

  val d = 2.1 tap println
  d.norm ensuring (_ == 2.0) pipe println // returns 2.0

  // Unniformity

  import org.scalactic._

  val truncated2 =
    new Uniformity[Double] {
      def normalized(d: Double)       = d.floor
      def normalizedCanHandle(o: Any) = o.isInstanceOf[Double]

      def normalizedOrSame(o: Any): Any =
        o match {
          case d: Double => normalized(d)
          case _         => o
        }
    }

  import org.scalatest._
  import matchers.should.Matchers._

  2.1 should equal(2.0)(after being truncated2)

  implicit val doubleUniformity = truncated2
  import NormMethods._

  val d2 = 2.1 tap println
  d2.norm ensuring (_ == 2.0) pipe println // returns 2.0
}
