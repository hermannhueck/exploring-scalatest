package munitdocs
package _02declaringtests

import scala.concurrent._

class Ex05OkLazyFutureSuite extends munit.FunSuite {

  case class LazyFuture[+T](run: () => Future[T])

  object LazyFuture {

    def apply[T](thunk: => T)(implicit ec: ExecutionContext): LazyFuture[T] =
      LazyFuture(() => Future(thunk))
  }

  override def munitValueTransforms = super.munitValueTransforms ++ List(
    new ValueTransform("LazyFuture", {
      case LazyFuture(run) => run()
    })
  )

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
