package munitdocs
package _02declaringtests

class Ex09ScalaVersionSuite extends munit.FunSuite {

  val scalaVersion = scala.util.Properties.versionNumberString

  override def munitNewTest(test: Test): Test =
    test.withName(test.name + "-" + scalaVersion)

  test("foo") {
    assert(!scalaVersion.startsWith("2.11"))
  }
}
