package scalatest.guide.ch19partialfunctionvalues

import org.scalatest._

class PartialFunctionValuesSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers with PartialFunctionValues {

  "PartialFunctionValues" should "makes the values inside a PartialFunction directly accessible" in {

    // Note: a Map[K, V] is a PartialFunction[K, V]
    val pf: PartialFunction[String, Int] = Map("I" -> 1, "II" -> 2, "III" -> 3, "IV" -> 4)

    pf.valueAt("IV") should equal(4)
    assert(pf.valueAt("IV") === 4)

    // pf("V") should equal(5) // pf("V") throws NoSuchElementException
    a[NoSuchElementException] should be thrownBy pf("V")

    val thrown = the[NoSuchElementException] thrownBy pf("V")
    thrown.getMessage should startWith("key not found:")
  }
}
