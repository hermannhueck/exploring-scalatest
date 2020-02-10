package scalacheck.guide

import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.{forAll, forAllNoShrink}

object TestCaseShrinking extends util.App {

  val p1 = forAllNoShrink(arbitrary[List[Int]])(l => l == l.distinct)
  p1.check
  println

  val p2 = forAll(arbitrary[List[Int]])(l => l == l.distinct)
  p2.check
  println

  val p3 = forAll((l: List[Int]) => l == l.distinct)
  p3.check
}
