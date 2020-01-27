package scalatest.guide.ch16inside

import org.scalatest._

case class Address(street: String, city: String, state: String, zip: String)
case class Name(first: String, middle: String, last: String)
case class Record(name: Name, address: Address, age: Int)

class InsideSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers with Inside {

  "inside" should "make values in nested structures accessible" in {

    val rec = Record(
      Name("Sally", "Anna", "Jones"),
      Address("25 Main St", "Los Angeles", "CA", "12345"),
      38
    )

    inside(rec) {
      case Record(name, address, age) =>
        inside(name) {
          case Name(first, middle, last) =>
            first should be("Sally")
            middle should be("Ann")
            last should be("Jones")
        }
        inside(address) {
          case Address(street, city, state, zip) =>
            street should startWith("25")
            city should endWith("Angeles")
            state should equal("CA")
            zip should be("12345")
        }
        age should be < 99
    }
  }
}
