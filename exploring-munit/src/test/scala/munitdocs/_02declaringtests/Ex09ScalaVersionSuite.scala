package munitdocs
package _02declaringtests

class Ex09ScalaVersionSuite extends munit.FunSuite {

  val scalaVersion = scala.util.Properties.versionNumberString

  override def munitTestTransforms = super.munitTestTransforms ++ List(
    new TestTransform("append Scala version", { test =>
      test.withName(test.name + "-" + scalaVersion)
    })
  )
  test("foo") {
    assert(!scalaVersion.startsWith("2.11"))
  }
}
