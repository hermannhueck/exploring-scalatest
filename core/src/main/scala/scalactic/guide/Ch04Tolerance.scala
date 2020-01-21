package scalactic.guide

import scala.util.chaining._

import org.scalactic._
import TripleEquals._
import Tolerance._

object Ch04Tolerance extends util.App {

  val result = 2.000001 tap println

  (result === 2.0 +- .001)
    .ensuring(_ == true) pipe println

  (result === 2.0 +- .000000001)
    .ensuring(_ == false) pipe println
}
