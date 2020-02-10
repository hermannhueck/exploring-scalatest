package scalactic.guide

import scala.util.chaining._

import org.scalactic._

object Ch07dOrAndEvery extends util.App {

  case class Person(name: String, age: Int)

  def parseName(input: String): String Or One[ErrorMessage] = {
    val trimmed = input.trim
    if (!trimmed.isEmpty) Good(trimmed) else Bad(One(s""""${input}" is not a valid name"""))
  }

  def parseAge(input: String): Int Or One[ErrorMessage] = {
    try {
      val age = input.trim.toInt
      if (age >= 0) Good(age) else Bad(One(s""""${age}" is not a valid age"""))
    } catch {
      case _: NumberFormatException => Bad(One(s"""${input}" is not a valid integer"""))
    }
  }

  import Accumulation._

  def parsePerson(inputName: String, inputAge: String): Person Or Every[ErrorMessage] = {
    val name = parseName(inputName)
    val age  = parseAge(inputAge)
    withGood(name, age) { Person(_, _) }
  }

  parsePerson("Bridget Jones", "29") pipe println
  // Result: Some(Person(Bridget Jones,29))

  parsePerson("Bridget Jones", "") pipe println
  // Result: None

  parsePerson("Bridget Jones", "-29") pipe println
  // Result: None

  parsePerson("", "") pipe println
  // Result: None
}
