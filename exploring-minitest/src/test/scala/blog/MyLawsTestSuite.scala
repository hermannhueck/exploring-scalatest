/*
  See: https://alexn.org/blog/2017/11/10/minitest-no-crap-scala-library.html
 */

package blog

import minitest._
import minitest.laws.Checkers

object MyLawsTestSuite extends SimpleTestSuite with Checkers {

  test("addition of integers is commutative") {
    check2((x: Int, y: Int) => x + y == y + x)
  }

  test("addition of integers is associative") {
    check3((x: Int, y: Int, z: Int) => (x + y) + z == x + (y + z))
  }
}
