package scalatest.guide.ch02styles

// Because it gives absolute freedom (and no guidance) on how specification text
// should be written, FreeSpec is a good choice for teams experienced with BDD
// and able to agree on how to structure the specification text.

import org.scalatest._

class Ex05SetSpec extends freespec.AnyFreeSpec {

  "A Set" - {
    "when empty" - {
      "should have size 0" in {
        assert(Set.empty.size == 0)
      }

      "should produce NoSuchElementException when head is invoked" in {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}
