package utest.readme

import utest._

object BeforeAfterAllSimpleTests extends TestSuite {

  println("on object body, aka: before all")

  override def utestAfterAll(): Unit = {
    println("on after all")
  }

  val tests = Tests {
    test("outer1") {
      test("inner1") {
        1
      }
      test("inner2") {
        2
      }
    }
  }
}
