package scalatest.guide.ch02styles

// A good first step for teams wishing to move from xUnit to BDD, FlatSpec's structure is flat
// like xUnit, so simple and familiar, but the test names must be written in a specification style:
// "X should Y," "A must B," etc.

import org.scalatest._

class Ex02SetSpec extends flatspec.AnyFlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
