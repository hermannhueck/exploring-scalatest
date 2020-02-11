/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex07FansiTests extends TestSuite {

  val tests = Tests {

    test("parsing") {

      def check(frag: fansi.Str) = {
        val parsed = fansi.Str(frag.render)
        assert(parsed == frag)
        parsed
      }

      def square(all: Seq[fansi.Attr]) = {
        //println(
        all
          .map(attr => attr.escapeOpt.getOrElse("") + "#")
          .grouped(32)
          .map(_.mkString)
          .mkString("\n")
        //)
      }

      test { check(fansi.Color.True(255, 0, 0)("lol")) }
      test { check(fansi.Color.True(1, 234, 56)("lol")) }
      test { check(fansi.Color.True(255, 255, 255)("lol")) }
      test {
        (for (i <- 0 to 255) yield check(fansi.Color.True(i, i, i)("x"))).mkString
      }
      test {
        check(
          "#" + fansi.Color.True(127, 126, 0)("lol") + "omg" + fansi.Color.True(127, 126, 0)("wtf")
        )
      }

      test {
        val colors: Seq[fansi.Attr] =
          for (i <- 0 to 255)
            yield fansi.Color.True(i, i, i)
        check(
          square(colors)
        )
      }
    }
  }
}
