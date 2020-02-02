import org.scalacheck._
import org.scalacheck.Prop._

val propConcatLists = forAll { (l1: List[Int], l2: List[Int]) =>
  l1.size + l2.size == (l1 ::: l2).size
}
propConcatLists.check

val propSqrt = forAll { (n: Int) =>
  scala.math.sqrt(n * n) == n
}
propSqrt.check

val propReverseList = forAll { l: List[String] =>
  l.reverse.reverse == l
}
propReverseList.check

val propConcatString = forAll { (s1: String, s2: String) =>
  (s1 + s2).endsWith(s2)
}
propConcatString.check

val smallInteger = Gen.choose(0, 100)

val propSmallInteger = forAll(smallInteger) { n =>
  n >= 0 && n <= 100
}
propSmallInteger.check
