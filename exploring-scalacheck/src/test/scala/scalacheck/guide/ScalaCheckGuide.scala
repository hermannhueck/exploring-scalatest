package scalacheck.guide

import org.scalacheck._
import org.scalacheck.Prop._

@com.github.ghik.silencer.silent("implicit numeric widening")
object ScalaCheckGuide extends Properties("Guide") {

  property("ConcatLists") = forAll { (l1: List[Int], l2: List[Int]) =>
    l1.size + l2.size == (l1 ::: l2).size
  }

  property("Sqrt") = forAll { (n: Int) =>
    scala.math.sqrt(n * n) == n
  }

  property("ReverseList") = forAll { l: List[String] =>
    l.reverse.reverse == l
  }

  property("ConcatString") = forAll { (s1: String, s2: String) =>
    (s1 + s2).endsWith(s2)
  }

  val smallInteger = Gen.choose(0, 100)

  property("SmallInteger") = forAll(smallInteger) { n =>
    n >= 0 && n <= 100
  }

  property("MakeList") = forAll { n: Int =>
    (n >= 0 && n < 10000) ==>
      (List.fill(n)("").length == n)
  }

  property("Trivial") = forAll { n: Int =>
    (n == 0) ==> (n == 0)
  }
}
