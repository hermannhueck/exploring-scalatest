package utest.readme

import utest._

object SuiteRetries extends TestSuite with TestSuite.Retries {

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
