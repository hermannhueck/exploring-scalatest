package scalatest.guide.ch13async

import org.scalatest._
import scala.concurrent.Future

class AddSpec extends flatspec.AsyncFlatSpec {

  def addSoon(addends: Int*): Future[Int] = Future { addends.sum }

  behavior of "addSoon"

  it should "eventually compute a sum of passed Ints" in {
    val futureSum: Future[Int] = addSoon(1, 2)
    // You can map assertions onto a Future, then return
    // the resulting Future[Assertion] to ScalaTest:
    futureSum map { sum =>
      assert(sum == 3)
    }
  }

  def addNow(addends: Int*): Int = addends.sum

  behavior of "addSoon"

  it should "immediately compute a sum of passed Ints" in {
    val sum: Int = addNow(1, 2)
    // You can also write synchronous tests. The body
    // must have result type Assertion:
    assert(sum == 3)
  }

  it should "immediately compute a sum of passed Ints and return an Assertion" in {
    val sum: Int = addNow(1, 2)
    assert(sum == 3)
    println("hi") // println has result type Unit
    succeed       // succeed has result type Assertion
  }
}
