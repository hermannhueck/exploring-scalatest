/*
  See: https://github.com/lihaoyi/utest
 */

package utest.readme

import utest._

object Ex20TestWithFixture extends TestSuite {

  def myTest[T](func: Int => T) = {
    val fixture = 1337          // initialize some value
    val res     = func(fixture) // make the value available in the test case
    assert(fixture == 1337) // properly teardown the value later
    res
  }

  val tests = Tests {

    test("test1") - myTest { fixture =>
      // do stuff with fixture
      println(fixture)
      assert(fixture + 1 == 1338)
      fixture
    }
  }
}
