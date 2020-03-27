package munitblog

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

/*
  See:
  https://scalameta.org/munit/blog/2020/03/24/scalacheck.html
 */
class IntegerSuite extends ScalaCheckSuite {

  property("addition is commutative") {
    forAll { (n1: Int, n2: Int) =>
      n1 + n2 == n2 + n1
    }
  }

  property("0 is the identity of addition") {
    forAll { (n: Int) =>
      n + 0 == n
    }
  }

  // You can define properties using the familiar features of ScalaCheck,
  // including labels (:| and |:), conditional properties (==>) and custom generators.

  // The integration with MUnit also grants you the ability to use assertions.
  // This is especially useful when testing multiple conditions of a property.

  // For example, using the ScalaCheck API you may write:

  property("integer identities") {
    forAll { (n: Int) =>
      (n + 0 == n) :| "0 is the addition identity" &&
      (n * 1 == n) :| "1 is the multiplication identity"
    }
  }

  // For longer expressions, however, this may become inconvenient and may choose
  // to use a MUnit assertions instead:

  property("integer identities") {
    forAll { (n: Int) =>
      assertEquals(n + 0, n, "0 is the addition identity")
      assertEquals(n * 1, n, "1 is the multiplication identity")
    }
  }

}
