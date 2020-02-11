/*
  See: http://www.lihaoyi.com/post/uTesttheEssentialTestFrameworkforScala.html
 */

package utest.blog

import utest._

object PPrintTests extends TestSuite {

  def check(c: Char, expected: String) = {
    val escaped = pprint.Util.escapeChar(c, new StringBuilder).toString
    assert(escaped == expected)
  }

  val tests = Tests {
    "escapeChar" - {
      check('a', "a")
      check('-', "-")
      check('\n', "\\n")
      check('\\', "\\\\")
      check('\t', "\\t")
    }
  }
}
