/*
  See: https://alexn.org/blog/2017/11/10/minitest-no-crap-scala-library.html
 */

package blog

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
