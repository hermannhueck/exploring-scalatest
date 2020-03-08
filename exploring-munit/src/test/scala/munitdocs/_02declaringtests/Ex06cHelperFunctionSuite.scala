package munitdocs
package _02declaringtests

class Ex06cHelperFunctionSuite extends munit.FunSuite {

  // OK: `bytes` parameter is by-name so `readAllBytes` is evaluated in test body.
  def checkByName(name: String, bytes: => Array[Byte])(implicit loc: munit.Location): Unit =
    test(name) {
      /* use bytes */
      assert(bytes.length > 1000)
    }

  // Not OK: `bytes` parameter is eager so `readAllBytes` is evaluated in class constructor.
  def checkEager(name: String, bytes: Array[Byte])(implicit loc: munit.Location): Unit =
    test(name) {
      /* use bytes */
      assert(bytes.length > 1000)
    }

  import java.nio.file.{Files, Paths}
  checkByName("file length lazy", Files.readAllBytes(Paths.get("build.sbt")))
  checkEager("file length eager", Files.readAllBytes(Paths.get("build.sbt")))
}
