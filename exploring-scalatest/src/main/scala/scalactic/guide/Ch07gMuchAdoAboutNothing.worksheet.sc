import org.scalactic._

Good(3)
Bad("oops")

Good(3).orBad[String]
Good[Int].orBad("oops")

// Good[AnyVal, String](3)
// Bad[Int, ErrorMessage]("oops")

Good(3): AnyVal Or String
Bad("oops"): Int Or ErrorMessage
