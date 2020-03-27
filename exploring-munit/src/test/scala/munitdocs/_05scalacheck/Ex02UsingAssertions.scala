package munitdocs._05scalacheck

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

/*
  See:
  https://scalameta.org/munit/docs/integrations/scalacheck.html
 */
class Ex02IntegerSuite extends ScalaCheckSuite {

  property("integer identities #1 (with assertEquals)") {
    forAll { (n: Int) =>
      assertEquals(n + 0, n)
      assertEquals(n * 1, n)
    }
  }

  // The property above is equivalent to

  property("integer identities #2") {
    forAll { (n: Int) =>
      (n + 0 == n) && (n * 1 == n)
    }
  }
}
