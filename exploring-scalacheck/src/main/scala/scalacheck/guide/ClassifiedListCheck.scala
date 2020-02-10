package scalacheck.guide

import org.scalacheck.Prop._

object ClassifiedListCheck extends util.App {

  def ordered(l: List[Int]) = l == l.sorted

  val myProp = forAll { l: List[Int] =>
    classify(ordered(l), "ordered") {
      classify(l.length > 5, "large", "small") {
        l.reverse.reverse == l
      }
    }
  }

  myProp.check
}
