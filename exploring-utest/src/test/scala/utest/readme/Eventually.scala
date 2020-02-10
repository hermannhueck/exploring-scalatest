package utest.readme

import utest._
import utest.asserts._
import scala.concurrent.duration._

object Eventually extends TestSuite {

  val tests = Tests {

    test("test1") {

      implicit val retryMax      = RetryMax(300.millis)
      implicit val retryInterval = RetryInterval(50.millis)

      val x = Seq(12)
      eventually(x == Nil)

      // utest.AssertionError: eventually(x == Nil)
      // x: Seq[Int] = List(12)
    }
  }
}
