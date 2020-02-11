/*
  See: http://www.lihaoyi.com/post/uTesttheEssentialTestFrameworkforScala.html
 */

package utest.blog

import utest._

object FansiTests extends TestSuite {

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

  val tests = Tests {
    "parsing" - {
      * - check(fansi.Color.True(255, 0, 0)("lol"))
      * - check(fansi.Color.True(1, 234, 56)("lol"))
      * - check(fansi.Color.True(255, 255, 255)("lol"))
      * - check(fansi.Color.True(10000)("lol"))
      * - check(square(for (i <- 0 to 255) yield fansi.Color.True(i, i, i)))
    }
  }
}
