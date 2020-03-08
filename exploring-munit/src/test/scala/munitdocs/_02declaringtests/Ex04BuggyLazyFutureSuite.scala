package munitdocs
package _02declaringtests

import scala.concurrent._

class Ex04BuggyLazyFutureSuite extends munit.FunSuite {

  case class LazyFuture[+T](run: () => Future[T])

  object LazyFuture {

    def apply[T](thunk: => T)(implicit ec: ExecutionContext): LazyFuture[T] =
      LazyFuture(() => Future(thunk))
  }

  implicit val ec: ExecutionContext = ExecutionContext.global

  test("buggy-task") {
    LazyFuture {
      Thread.sleep(10)
      // WARNING: test will pass because `Task.run()` was never called
      if (true) throw new RuntimeException("BOOM!") else 42
    }
  }
}
