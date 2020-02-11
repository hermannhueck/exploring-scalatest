/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex18SuiteRetries extends TestSuite with TestSuite.Retries {

  class FlakyThing {

    def run(): Unit = {
      println("run ...")
      throw new java.lang.Exception("run failed")
    }
  }

  val flaky = new FlakyThing

  override val utestRetryCount = 3

  def tests = Tests {
    test("hello") - {
      flaky.run()
    }
  }
}
