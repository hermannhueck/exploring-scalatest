import munit._
import munit.Assertions._

val a = 1
val b = 2

// assert(a > b)

// assert(a > b, "a was smaller than b")

// assert(clue(a) > clue(b))

// assert(clue(List(a).head) > clue(b))

// assertEquals(a, b)

case class Library(name: String, awesome: Boolean, versions: Range = 0.to(1))
val munitLibrary = Library("MUnit", true)
val mdocLibrary  = Library("MDoc", true)

// assertEquals(munitLibrary, mdocLibrary)

// assertEquals(
//   Map(1 -> List(1.to(3))),
//   Map(1 -> List(1.to(4)))
// )
