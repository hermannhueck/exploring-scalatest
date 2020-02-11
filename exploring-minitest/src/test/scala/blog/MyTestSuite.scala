/*
  See: https://alexn.org/blog/2017/11/10/minitest-no-crap-scala-library.html
 */

package blog

import monix.execution.schedulers.TestScheduler
import minitest.TestSuite
import scala.concurrent.Future
import scala.util.Success

object MyTestSuite extends TestSuite[TestScheduler] {

  def setup() = TestScheduler()

  def tearDown(env: TestScheduler): Unit =
    assert(env.state.tasks.isEmpty, "Scheduler should not have tasks left")

  test("simulated async") { implicit ec =>
    val f = Future(1).map(_ + 1)
    ec.tick()

    assertEquals(f.value, Some(Success(2)))
  }
}
