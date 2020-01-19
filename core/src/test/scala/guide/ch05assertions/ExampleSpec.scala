package guide.ch05assertions

import org.scalatest._
import org.scalatest.exceptions.TestFailedException

class ExampleSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers {

  "Assertions" should "be examined: assert" in {
    a[TestFailedException] should be thrownBy {
      val left  = 2
      val right = 1
      assert(left === right)
    }
  }

  "Assertions" should "be examined: fail" in {
    val s = "hi"
    try {
      s.charAt(-1)
      fail()
    } catch {
      case _: IndexOutOfBoundsException => // Expected, so continue
    }
  }

  "Assertions" should "be examined: assertThrows" in {
    val s = "hi"
    assertThrows[IndexOutOfBoundsException] { // Result type: Assertion
      s.charAt(-1)
    }
  }

  "Assertions" should "be examined: intercept" in {
    val s = "hi"
    val caught =
      intercept[IndexOutOfBoundsException] { // Result type: IndexOutOfBoundsException
        s.charAt(-1)
      }
    assert(caught.getMessage.indexOf("-1") != -1)
  }

  "Compilation" should "be asserted" in {
    assertCompiles("val a: Int = 1")
    assertDoesNotCompile("val a: String = ...")
    assertTypeError("val a: String = 1")
  }

  "Failed assumptions" should "throw a TestCanceledException" in {
    final case class Database() {
      def isAvailable: Boolean = false
    }
    val database = Database()
    // assume(database.isAvailable)
    assume(database.isAvailable, "The database was down again")
  }

  "cancel" should "cancel the test" in {
    cancel("Can't run the test because no internet connection was found")
  }

  "A clue in assert" should "enhance the test failed message" in {
    assert(1 + 1 === 3, "--- this is a clue")
  }

  "A clue in assertResult" should "enhance the test failed message" in {
    assertResult(3, "--- this is a clue") { 1 + 1 }
  }

  "A clue in assertThrows" should "enhance the test failed message" in {
    withClue("this is a clue:") {
      assertThrows[IndexOutOfBoundsException] {
        "hi".charAt(-1)
      }
    }

  }
}
