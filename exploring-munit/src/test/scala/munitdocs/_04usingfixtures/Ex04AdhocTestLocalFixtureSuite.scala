package munitdocs
package _04usingfixtures

import java.nio.file._
import munit._

class Ex04AdhocTestLocalFixtureSuite extends munit.FunSuite {

  var path: Path = null

  // Runs before each individual test.
  override def beforeEach(context: BeforeEach): Unit = {
    path = Files.createTempFile("Ex04AdhocTestLocalFixtureSuite", context.test.name)
  }

  // Runs after each individual test.
  override def afterEach(context: AfterEach): Unit = {
    Files.deleteIfExists(path)
    ()
  }

  test("test1") {
    assert(Files.exists(path))
    path // will be deleted after this test case finishes
  }
  test("test2") {
    assert(Files.isRegularFile(path))
    path // not the same `path` as in "test1"
  }
}
