package munitdocs
package _02declaringtests

import scala.concurrent._

class Ex08MyWindowsSuite extends munit.FunSuite {

  case class Rerun(count: Int) extends munit.Tag("Rerun")

  implicit val ec: ExecutionContext = ExecutionContext.global

  override def munitTestTransforms = super.munitTestTransforms ++ List(
    new TestTransform("Rerun", { test =>
      val rerunCount = test
        .tags
        .collectFirst { case Rerun(n) => n }
        .getOrElse(1)
      if (rerunCount == 1) test
      else {
        test.withBody(() => {
          Future.sequence(1.to(rerunCount).map(_ => test.body()).toList)
        })
      }
    })
  )

  test("files x 10".tag(Rerun(10))) {
    // will run 10 times
    println("Hello 10 times")
  }

  test("files x 1") {
    // will run once, like normal
    println("Hello only once")
  }
}
