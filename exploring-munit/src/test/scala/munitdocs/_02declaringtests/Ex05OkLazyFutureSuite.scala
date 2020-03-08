package munitdocs
package _02declaringtests

import scala.concurrent._

class Ex05OkLazyFutureSuite extends munit.FunSuite {

  case class LazyFuture[+T](run: () => Future[T])

  object LazyFuture {

    def apply[T](thunk: => T)(implicit ec: ExecutionContext): LazyFuture[T] =
      LazyFuture(() => Future(thunk))
  }

  // TODO
  override def munitTestValue(testValue: => Any): Future[Any] =
    super.munitTestValue(testValue) flatMap {
      case LazyFuture(run) => run()
      case value           => Future.successful(value)
    }

  implicit val ec: ExecutionContext = ExecutionContext.global

  // await one second instead of default
  // override val munitTimeout = Duration(1, "s")

  test("ok-task") {
    LazyFuture {
      Thread.sleep(5000)
      // Test will fail because `Task.run()` is automatically called
      if (true) throw new RuntimeException("BOOM!") else 42
    }
  }
}
