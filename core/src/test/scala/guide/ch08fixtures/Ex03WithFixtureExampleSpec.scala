package guide.ch08fixtures

import org.scalatest._
import java.io.File

class Ex03WithFixtureExampleSpec extends flatspec.AnyFlatSpec {

  override def withFixture(test: NoArgTest) = {

    super.withFixture(test) match {
      case failed: Failed =>
        val currDir   = new File(".")
        val fileNames = currDir.list()
        info("Dir snapshot: " + fileNames.mkString(", "))
        failed
      case other => other
    }
  }

  "This test" should "succeed" in {
    assert(1 + 1 === 2)
  }

  it should "fail" in {
    assert(1 + 1 === 3)
  }
}
