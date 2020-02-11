/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

object Ex08AsyncTests extends TestSuite {

  implicit val ec: ExecutionContext = ExecutionContext.global

  val tests = Tests {
    test("testSuccess") {
      Future {
        assert(true)
      }
    }
    test("testFail") {
      Future {
        assert(false)
      }
    }
    test("normalSuccess") {
      assert(true)
    }
    test("normalFail") {
      assert(false)
    }
  }

  TestRunner.runAsync(tests).map { results =>
    val leafResults = results.leaves.toSeq
    assert(leafResults(0).value.isSuccess) // root
    assert(leafResults(1).value.isSuccess) // testSuccess
    assert(leafResults(2).value.isFailure) // testFail
    assert(leafResults(3).value.isSuccess) // normalSuccess
  }
}
