package scalatest.guide.ch18eithervalues

import org.scalatest._

class EitherValuesSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers with EitherValues {

  "EitherValues" should "makes the values inside an Either directly accessible" in {

    val either1 = Right(10)
    val either2 = Left("Muchas problemas")

    either1.right.value should be > 9
    either2.left.value should be("Muchas problemas")

    assert(either1.right.value > 9)
    assert(either2.left.value === "Muchas problemas")
  }
}
