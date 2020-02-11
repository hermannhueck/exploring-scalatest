package _02declaringtests

class Ex06aHelperFunctionSuite extends munit.FunSuite {

  def check[T](
      name: String,
      original: List[T],
      expected: Option[T]
  )(implicit loc: munit.Location): Unit = {
    test(name) {
      val obtained = original.headOption
      assertEquals(obtained, expected)
    }
  }

  check("basic", List(1, 2), Some(1))
  check("empty", List.empty[Int], Some(1))
  check("null", List(null, 2), Some(null))
}
