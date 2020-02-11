/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex17LocalRetries extends TestSuite {

  class FlakyThing {

    def run(): Unit = {
      println("run ...")
      throw new java.lang.Exception("run failed")
    }
  }

  val flaky = new FlakyThing

  def tests = Tests {
    test("hello") - retry(3) {
      flaky.run()
    }
  }
}
