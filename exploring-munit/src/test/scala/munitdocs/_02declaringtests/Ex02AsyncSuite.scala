package munitdocs
package _02declaringtests

import scala.concurrent._

class Ex02AsyncSuite extends munit.FunSuite {

  implicit val ec: ExecutionContext = ExecutionContext.global

  test("async") {
    Future {
      println("Hello Internet!")
    }
  }
}
