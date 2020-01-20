import org.scalactic._
import TypeCheckedTripleEquals._
Some(1) === 2
Some(1) === Some(2)
1 === 1L
import ConversionCheckedTripleEquals._
1 === 1L
List(1, 2, 3) === Vector(1, 2, 3)
import TraversableEqualityConstraints._
List(1, 2, 3) === Vector(1, 2, 3)
List(1, 2, 3) === Set(1, 2, 3)
