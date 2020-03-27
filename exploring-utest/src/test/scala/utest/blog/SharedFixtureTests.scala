package utest.blog

/*
  See: http://www.lihaoyi.com/post/uTesttheEssentialTestFrameworkforScala.html
 */

import utest._

@com.github.ghik.silencer.silent("dead code")
object SharedFixtureTests extends TestSuite {

  val tests = Tests {
    "outer1" - {
      throw new Exception("Outer One")
      "inner31" - {
        throw new Exception("Inner One")
      }
      "inner2" - {
        throw new Exception("Inner One")
      }
    }
    "outer2" - {
      throw new Exception("Outer Two")
      "inner3" - {
        throw new Exception("Inner Two")
      }
    }
  }
}
