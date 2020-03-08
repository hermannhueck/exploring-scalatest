package munitdocs
package _04usingfixtures

import java.nio.file._
import munit._

class Ex01FunctionalTestLocalFixtureSuite extends munit.FunSuite {

  val files = new FunFixture[Path](
    setup = { test =>
      val path: Path = Files.createTempFile("tmp", test.name)
      path
    }: TestOptions => Path,
    teardown = { file: Path =>
      // Always gets called, even if test failed.
      Files.deleteIfExists(file)
      ()
    }: Path => Unit
  )

  files.test("basic") { file =>
    assert(Files.isRegularFile(file), s"Files.isRegularFile($file)")
  }

  // Fixture with access to two temporary files.
  val files2: FunFixture[(Path, Path)] = FunFixture.map2(files, files)

  files2.test("two") {
    case (file1, file2) =>
      assertNotEquals(file1, file2)
      assert(Files.isRegularFile(file1), s"Files.isRegularFile($file1)")
      assert(Files.isRegularFile(file2), s"Files.isRegularFile($file2)")
  }
}
