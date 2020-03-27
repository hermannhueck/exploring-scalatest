package munitdocs._05scalacheck

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

/*
  See:
  https://scalameta.org/munit/docs/integrations/scalacheck.html
 */
class Ex01IntegerSuite extends ScalaCheckSuite {

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

  // property is almost identical to FunSuite's test, but it additionally ensures
  // that the test value is a ScalaCheck Prop.

  // It supports all of test's features, such as tagging and marking the property as expected to fail:

  case object WindowsOnly extends munit.Tag("WindowsOnly")

  property("my property".tag(WindowsOnly)) {
    forAll { (n: Int) =>
      n * 0 == 0
    }
  }

  def buggyFunction(n: Int): Int = n

  property("issue-123".fail) {
    forAll { (n: Int) =>
      buggyFunction(n) == 42
    }
  }
}
