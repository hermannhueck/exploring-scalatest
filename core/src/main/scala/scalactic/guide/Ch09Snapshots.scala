package scalactic.guide

import scala.util.chaining._
import org.scalactic._
import Snapshots._

object Ch09Snapshots extends util.App {

  val a                 = 42
  val b                 = true
  val c                 = 42.0
  val d                 = "hello"
  val e: String         = null
  val f: java.util.Date = null

  snap(a, b, c, d, e, f) pipe println

  println

  snap(a, b, c, d, e, f).lines pipe println

  println

  snap(a, b, c, d, e, f).mkString("Wow! ", ", and ", ". That's so awesome!") pipe println
}
