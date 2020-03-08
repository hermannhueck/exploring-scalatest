package munitdocs
package _02declaringtests

import scala.concurrent._

class Ex05OkTaskSuite extends munit.FunSuite {

  case class Task[+T](run: () => Future[T])

  object Task {

    def apply[T](thunk: => T)(implicit ec: ExecutionContext): Task[T] =
      Task(() => Future(thunk))
  }

  override def munitTestValue(testValue: Any): Any =
    testValue match {
      case Task(run) => Await.result(run(), munitTimeout)
      case _         => super.munitTestValue(testValue)
    }

  implicit val ec: ExecutionContext = ExecutionContext.global

  // await one second instead of default
  // override val munitTimeout = Duration(1, "s")

  test("ok-task") {
    Task {
      Thread.sleep(5000)
      // Test will fail because `Task.run()` is automatically called
      if (true) throw new RuntimeException("BOOM!") else 42
    }
  }
}
