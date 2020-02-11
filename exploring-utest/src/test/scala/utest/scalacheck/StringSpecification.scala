package utest.scalacheck

import org.scalacheck.{Prop, Test}
import org.scalacheck.util.Pretty
import org.scalacheck.Prop.forAll

import utest._

trait UTestScalaCheck {

  protected[this] object UTestReporter extends Test.TestCallback {
    private val prettyParams = Pretty.defaultParams

    override def onTestResult(name: String, res: org.scalacheck.Test.Result) = {
      val scalaCheckResult = if (res.passed) "" else Pretty.pretty(res, prettyParams)
      assert(scalaCheckResult.isEmpty)
    }
  }

  implicit protected[this] class PropWrapper(prop: Prop) {

    def checkUTest(): Unit = {
      prop.check(Test.Parameters.default.withTestCallback(UTestReporter))
    }
  }
}

object StringSpecification extends TestSuite with UTestScalaCheck {

  val tests = TestSuite {
    "String" - {

      "startsWith" - forAll { (a: String, b: String) =>
        (a + b).startsWith(a)
      }.checkUTest()

      "concatenate" - forAll { (a: String, b: String) =>
        (a + b).length > a.length && (a + b).length > b.length
      }.checkUTest()

      "substring" - forAll { (a: String, b: String, c: String) =>
        (a + b + c).substring(a.length, a.length + b.length) == b
      }.checkUTest()
    }
  }
}
