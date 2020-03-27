package munitdocs._05scalacheck

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

/*
  See:
  https://scalameta.org/munit/docs/integrations/scalacheck.html
 */
class Ex03IntegerSuite extends ScalaCheckSuite {

  override def scalaCheckTestParameters =
    super
      .scalaCheckTestParameters
      .withMinSuccessfulTests(200)
      .withMaxDiscardRatio(10)

  property("addition is commutative") {
    forAll { (n1: Int, n2: Int) =>
      n1 + n2 == n2 + n1
    }
  }
}
