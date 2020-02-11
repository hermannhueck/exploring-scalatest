package _02declaringtests

import munit._
import scala.concurrent._
import munit.Tag

class Ex11MyCustomMinimalSuite extends munit.Suite {

  override type TestValue = Future[String]

  override def munitTests() = List(
    new Test(
      "name",
      // compile error if it's not a Future[String]
      body = { () =>
        Future.successful("Hello world!")
      },
      tags = Set.empty[Tag],
      location = implicitly[Location]
    )
  )
}
