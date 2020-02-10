package utest.readme

import utest._
import utest.framework._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

object StandaloneTests3 extends App {

  val tests = Tests {
    test("test1") {
      // throw new Exception("test1")
      "test1"
    }
    test("test2") {
      test("inner") {
        1
      }
    }
    test("test3") {
      val a = List[Byte](1, 2)
      // a(10)
      a(1)
    }
  }

  val x = 42

  implicit val ec: ExecutionContext = ExecutionContext.global

  // Run, return results, and print output with custom formatter and executor
  val results3 = TestRunner.runAndPrint(
    tests,
    "MyTestSuiteA",
    executor = new utest.framework.Executor {

      override def utestWrap(path: Seq[String], runBody: => Future[Any])(implicit ec: ExecutionContext): Future[Any] = {
        println("Getting ready to run " + path.mkString("."))
        utestBeforeEach(path)
        runBody.andThen {
          case _ => utestAfterEach(path)
        }
      }
    },
    formatter = new utest.framework.Formatter {
      override def formatColor = false
    }
  )
}
