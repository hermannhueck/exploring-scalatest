package scalacheck.guide

import org.scalacheck.Arbitrary

import scala.util.chaining._

object GeneratorArbitrary extends util.App {

  val evenInteger = Arbitrary.arbitrary[Int] suchThat (_ % 2 == 0)
  s"generated an even Int (using arbitrary[Int]) ---> " pipe print
  evenInteger.sample pipe println

  val squares = for {
    xs <- Arbitrary.arbitrary[List[Int]]
  } yield xs.map(x => x * x)
  s"generated an Int Lists (using arbitrary[List[Int]]) --->\n" pipe print
  squares.sample pipe println
}
