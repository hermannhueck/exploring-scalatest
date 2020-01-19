import org.scalatest.Assertions._

val left  = 2
val right = 1
assert(left == right)

val a   = 1
val b   = 2
val c   = 3
val d   = 4
val xs  = List(a, b, c)
val num = 1.0

assert(a == b || c >= d)
// Error message: 1 did not equal 2, and 3 was not greater than or equal to 4

assert(xs.exists(_ == 4))
// Error message: List(1, 2, 3) did not contain 4

assert("hello".startsWith("h") && "goodbye".endsWith("y"))
// Error message: "hello" started with "h", but "goodbye" did not end with "y"

assert(num.isInstanceOf[Int])
// Error message: 1.0 was not instance of scala.Int

assert(Some(2).isEmpty)
// Error message: Some(2) was not empty
