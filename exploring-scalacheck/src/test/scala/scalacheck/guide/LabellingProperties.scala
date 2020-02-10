package scalacheck.guide

import org.scalacheck.Properties
import org.scalacheck.Prop.{all, forAll, propBoolean}

object LabellingProperties extends Properties("Labelling") {

  def myMagicFunction(n: Int, m: Int) = n + m

  val complexProp1 = forAll { (m: Int, n: Int) =>
    val res = myMagicFunction(n, m)
    (res >= m) :| "result > #1" &&
    (res >= n) :| "result > #2" &&
    (res < m + n) :| "result not sum"
  }

  property("complex1") = complexProp1

  val complexProp2 = forAll { (m: Int, n: Int) =>
    val res = myMagicFunction(n, m)
    ("result > #1" |: res >= m) &&
    ("result > #2" |: res >= n) &&
    ("result not sum" |: res < m + n)
  }

  property("complex2") = complexProp2

  val propMul = forAll { (n: Int, m: Int) =>
    val res = n * m
    ("evidence = " + res) |: all(
      "div1" |: m != 0 ==> (res / m == n),
      "div2" |: n != 0 ==> (res / n == m),
      "lt1" |: res > m,
      "lt2" |: res > n
    )
  }
  property("mul") = propMul
}
