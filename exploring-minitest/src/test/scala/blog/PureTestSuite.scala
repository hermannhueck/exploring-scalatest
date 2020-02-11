package blog

import minitest.SimpleTestSuite
import minitest.api.Asserts._
import minitest.laws.Checkers
import cats.effect.IO
import org.scalacheck.{Prop, Test}
import org.scalacheck.Prop._
import org.scalacheck.util.Pretty
import scala.concurrent.ExecutionContext.Implicits.global

trait PureTestSuite extends minitest.api.AbstractTestSuite {

  private[this] val ts = new SimpleTestSuite {}
  lazy val properties  = ts.properties
  val config           = Test.Parameters.default

  def test(name: String)(f: => Prop): Unit =
    ts.test(name) {
      val result = Test.check(config, f)
      if (!result.passed)
        fail(Pretty.pretty(result))
    }

  def testIO(name: String)(f: => IO[Prop]): Unit =
    ts.testAsync(name) {
      f.unsafeToFuture.map { res =>
        val result = Test.check(config, res)
        if (!result.passed)
          fail(Pretty.pretty(result))
      }
    }
}

object MyPureTestSuite extends PureTestSuite {

  val prop = forAll { xs: List[Int] =>
    xs.reverse.reverse == xs
  }

  test("test: xs.reverse.reverse == xs")(prop)

  testIO("testIO: xs.reverse.reverse == xs")(IO(prop))
}
