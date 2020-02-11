/*
  See: https://github.com/monix/minitest
 */

package tutorial

import minitest.TestSuite
import scala.util.Random

object MyTestSuite extends TestSuite[Int] {

  def setup(): Int = {
    Random.nextInt(100) + 1
  }

  def tearDown(env: Int): Unit = {
    assert(env > 0, "should still be positive")
  }

  test("should be positive") { env =>
    assert(env > 0, "positive test")
  }

  test("should be lower or equal to 100") { env =>
    assert(env <= 100, s"$env > 100")
  }
}
