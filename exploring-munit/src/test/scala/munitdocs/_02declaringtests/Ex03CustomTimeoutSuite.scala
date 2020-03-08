package munitdocs
package _02declaringtests

import scala.concurrent._
import scala.concurrent.duration._

class Ex03CustomTimeoutSuite extends munit.FunSuite {

  implicit val ec: ExecutionContext = ExecutionContext.global

  // await one second instead of default
  override val munitTimeout = Duration(1, "s")

  test("slow-async") {
    Future {
      Thread.sleep(5000)
      // Test times out before `println()` is evaluated.
      println("pass")
    }
  }
}
