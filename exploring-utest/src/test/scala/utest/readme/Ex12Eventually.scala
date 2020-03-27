/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._
import utest.asserts._
import scala.concurrent.duration._

object Ex12Eventually extends TestSuite {

  val tests = Tests {

    test("test1") {

      @com.github.ghik.silencer.silent("never used")
      implicit val retryMax = RetryMax(300.millis)
      @com.github.ghik.silencer.silent("never used")
      implicit val retryInterval = RetryInterval(50.millis)

      val x = Seq(12)
      eventually(x == Nil)

      // utest.AssertionError: eventually(x == Nil)
      // x: Seq[Int] = List(12)
    }
  }
}
