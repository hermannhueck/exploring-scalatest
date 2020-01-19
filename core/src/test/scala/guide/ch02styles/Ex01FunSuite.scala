package guide.ch02styles

// For teams coming from xUnit, FunSuite feels comfortable and familiar while still giving
// some of the benefits of BDD: FunSuite makes it easy to write descriptive test names,
// natural to write focused tests, and generates specification-like output that can facilitate
// communication among stakeholders.

import org.scalatest._

class Ex01SetSuite extends funsuite.AnyFunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
