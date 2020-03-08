package munitdocs
package _02declaringtests

class Ex08MyWindowsSuite extends munit.FunSuite {

  case class Rerun(count: Int) extends munit.Tag("Rerun")

  override def munitRunTest(options: munit.TestOptions, body: => Any): Any = {
    val rerunCount = options
      .tags
      .collectFirst {
        case Rerun(n) => n
      }
      .getOrElse(1)
    1.to(rerunCount).map(_ => super.munitRunTest(options, body))
  }

  test("files x 10".tag(Rerun(10))) {
    // will run 10 times
    println("Hello 10 times")
  }

  test("files x 1") {
    // will run once, like normal
    println("Hello only once")
  }
}
