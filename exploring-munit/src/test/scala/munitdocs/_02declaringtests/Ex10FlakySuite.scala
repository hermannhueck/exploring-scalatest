package munitdocs
package _02declaringtests

class Ex10FlakySuite extends munit.FunSuite {

  test("requests".flaky) {
    // I/O heavy tests that sometimes fail
    assertEquals(scala.util.Random.nextBoolean(), true)
  }
}
