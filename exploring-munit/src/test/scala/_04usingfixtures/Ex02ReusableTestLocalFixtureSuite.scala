package _04usingfixtures

import java.nio.file._
import munit._

class Ex02ReusableTestLocalFixtureSuite extends munit.FunSuite {

  val file = new Fixture[Path]("files") {
    var file: Path = null
    def apply()    = file

    override def beforeEach(context: BeforeEach): Unit = {
      file = Files.createTempFile("files", context.test.name)
    }

    override def afterEach(context: AfterEach): Unit = {
      // Always gets called, even if test failed.
      Files.deleteIfExists(file)
      ()
    }
  }

  override def munitFixtures = List(file)

  test("tmp file exists") {
    // `file` is the temporary file that was created for this test case.
    assert(Files.exists(file()))
  }
}
