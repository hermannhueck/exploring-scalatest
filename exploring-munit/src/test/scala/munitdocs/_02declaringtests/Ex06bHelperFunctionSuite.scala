package munitdocs
package _02declaringtests

@com.github.ghik.silencer.silent("type was inferred to be `Any`")
class Ex06bHelperFunctionSuite extends munit.FunSuite {

  def check[T](
      name: String,
      original: List[T],
      expected: Option[T]
  ): Unit = { // no implicit munit.Location passed
    test(name) {
      val obtained = original.headOption
      assertEquals(obtained, expected)
    }
  }

  check("basic", List(1, 2), Some(1))
  check("empty", List.empty[Int], Some(1))
  // @com.github.ghik.silencer.silent("type was inferred to be `Any`")
  check("null", List(null, 2), Some(null))
}
