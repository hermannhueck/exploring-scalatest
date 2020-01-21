package scalactic.guide

import scala.util.chaining._

import org.scalactic._

object Ch01CustomEquality extends util.App {

  case class Person(name: String, age: Double)

  import TripleEquals._

  Person("Joe", 29.0001) === Person("Joe", 29.0) pipe println

  import Tolerance._

  implicit val personEq =
     new Equality[Person] {
          def areEqual(a: Person, b: Any): Boolean =
            b match {
              case p: Person => a.name == p.name && a.age === p.age +- 0.0002
              case _ => false
            }
        }

  Person("Joe", 29.0001) === Person("Joe", 29.0) pipe println
}
