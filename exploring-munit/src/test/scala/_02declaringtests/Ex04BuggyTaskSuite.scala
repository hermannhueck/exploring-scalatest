package _02declaringtests

import scala.concurrent._

class Ex04BuggyTaskSuite extends munit.FunSuite {

  case class Task[+T](run: () => Future[T])

  object Task {

    def apply[T](thunk: => T)(implicit ec: ExecutionContext): Task[T] =
      Task(() => Future(thunk))
  }

  implicit val ec: ExecutionContext = ExecutionContext.global

  test("buggy-task") {
    Task {
      Thread.sleep(10)
      // WARNING: test will pass because `Task.run()` was never called
      if (true) throw new RuntimeException("BOOM!") else 42
    }
  }
}
