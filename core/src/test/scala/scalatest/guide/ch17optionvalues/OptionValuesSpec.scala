package scalatest.guide.ch17optionvalues

import org.scalatest._
import exceptions.TestFailedException

class OptionValuesSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers with OptionValues {

  "OptionValues" should "makes the value inside an Option directly accessible" in {

    val opt = Some(10)

    opt.value should be > 9
    assert(opt.value > 9)

    val opt2: Option[Int] = None

    // requires 2 statements without OptionValues
    // opt2 should be('defined) // throws TestFailedException
    // opt2.get should be > 9

    // opt2.value should be > 9
    val e = intercept[TestFailedException] { opt2.value should be > 9 }
    e.getMessage should ===("The Option on which value was invoked was not defined.")
    e.getCause shouldBe a[NoSuchElementException]
    e.getCause.getMessage should ===("None.get")
  }
}
