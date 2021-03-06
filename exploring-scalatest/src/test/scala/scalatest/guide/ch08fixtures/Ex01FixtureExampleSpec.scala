package scalatest.guide.ch08fixtures

import org.scalatest._
import collection.mutable.ListBuffer

class Ex01FixtureExampleSpec extends flatspec.AnyFlatSpec {

  import scala.language.reflectiveCalls

  def fixture =
    new {
      val builder = new StringBuilder("ScalaTest is ")
      val buffer  = new ListBuffer[String]
    }

  "Testing" should "be easy" in {
    val f = fixture
    f.builder.append("easy!")
    assert(f.builder.toString === "ScalaTest is easy!")
    assert(f.buffer.isEmpty)
    f.buffer += "sweet"
  }

  it should "be fun" in {
    val f = fixture
    f.builder.append("fun!")
    assert(f.builder.toString === "ScalaTest is fun!")
    assert(f.buffer.isEmpty)
  }
}
