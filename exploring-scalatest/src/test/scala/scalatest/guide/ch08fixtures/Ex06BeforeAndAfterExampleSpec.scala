package scalatest.guide.ch08fixtures

import org.scalatest._
import collection.mutable.ListBuffer

class Ex06BeforeAndAfterExampleSpec extends flatspec.AnyFlatSpec with BeforeAndAfter {

  val builder = new StringBuilder
  val buffer  = new ListBuffer[String]

  before {
    println("=====> before")
    builder.append("ScalaTest is ")
  }

  after {
    builder.clear()
    buffer.clear()
    println("=====> after")
  }

  "Testing" should "be easy" in {
    builder.append("easy!")
    assert(builder.toString === "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString === "ScalaTest is fun!")
    assert(buffer.isEmpty)
  }
}
