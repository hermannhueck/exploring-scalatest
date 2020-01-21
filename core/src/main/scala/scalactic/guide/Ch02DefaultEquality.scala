package scalactic.guide

import scala.util.chaining._

import org.scalactic._
import TripleEquals._

object Ch02DefaultEquality extends util.App {

  (Array(1, 2, 3) == Array(1, 2, 3))
    .ensuring(_ == false) pipe println

  (Array(1, 2, 3) === Array(1, 2, 3))
    .ensuring(_ == true) pipe println

  (Array(1, 2, 3) == List(1, 2, 3))
    .ensuring(_ == false) pipe println

  (Array(1, 2, 3) === List(1, 2, 3))
    .ensuring(_ == true) pipe println

  (List(1, 2, 3) == Array(1, 2, 3))
    .ensuring(_ == false) pipe println

  (List(1, 2, 3) === Array(1, 2, 3))
    .ensuring(_ == true) pipe println
}
