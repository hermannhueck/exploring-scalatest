/*
  See: https://github.com/monix/minitest
 */

package tutorial

import minitest._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object AsyncTestSuite extends SimpleTestSuite {

  testAsync("asynchronous execution") {

    val future = Future(100).map(_ + 1)

    for (result <- future) yield {
      assertEquals(result, 101)
    }
  }
}
