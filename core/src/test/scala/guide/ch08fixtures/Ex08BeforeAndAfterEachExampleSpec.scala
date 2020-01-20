package guide.ch08fixtures

import org.scalatest._
import collection.mutable.ListBuffer

trait Builder2 extends BeforeAndAfterEach with BeforeAndAfterAll { this: Suite =>

  val builder = new StringBuilder

  override def beforeEach() = {
    println("=====> Builder2.beforeEach")
    builder.append("ScalaTest is ")
    super.beforeEach() // To be stackable, must call super.beforeEach
  }

  override def afterEach() = {
    try super.afterEach() // To be stackable, must call super.afterEach
    finally {
      builder.clear()
      println("=====> Builder2.afterEach")
    }
  }

  override def beforeAll() = {
    println("=====> Builder2.beforeAll")
    super.beforeAll() // To be stackable, must call super.beforeEach
  }

  override def afterAll() = {
    try super.afterAll() // To be stackable, must call super.afterEach
    finally println("=====> Builder2.afterAll")
  }
}

trait Buffer2 extends BeforeAndAfterEach with BeforeAndAfterAll { this: Suite =>

  val buffer = new ListBuffer[String]

  override def beforeEach() = {
    println("=====> Buffer2.beforeEach")
    super.beforeEach() // To be stackable, must call super.beforeEach
  }

  override def afterEach() = {
    try super.afterEach() // To be stackable, must call super.afterEach
    finally {
      buffer.clear()
      println("=====> Buffer2.afterEach")
    }
  }

  override def beforeAll() = {
    println("=====> Buffer2.beforeAll")
    super.beforeAll() // To be stackable, must call super.beforeEach
  }

  override def afterAll() = {
    try super.afterAll() // To be stackable, must call super.afterEach
    finally println("=====> Buffer2.afterAll")
  }
}

class Ex08BeforeAndAfterEachExampleSpec extends flatspec.AnyFlatSpec with Builder2 with Buffer2 {

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
    buffer += "clear"
  }
}
