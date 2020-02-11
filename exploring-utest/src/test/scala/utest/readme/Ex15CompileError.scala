/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex15CompileError extends TestSuite {

  val tests = Tests {

    test("test1") {
      compileError("true * false")
      // CompileError.Type("value * is not a member of Boolean")
    }

    test("test2") {
      compileError("(}")
      // CompileError.Parse("')' expected but '}' found.")
    }

    test("test3") {
      val x = 0
      // compileError("x + x") // does not compile
      // [error] compileError check failed to have a compilation error
    }

    test("test4") {
      compileError("true * false").check(
        """
      compileError("true * false").check(
                         ^
        """,
        "value * is not a member of Boolean"
      )
    }
  }
}
