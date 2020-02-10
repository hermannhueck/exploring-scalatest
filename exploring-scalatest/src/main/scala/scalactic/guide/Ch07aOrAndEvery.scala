package scalactic.guide

import scala.util.chaining._

object Ch07aOrAndEvery extends util.App {

  case class Person(name: String, age: Int)

  def parseName(input: String): Option[String] = {
    val trimmed = input.trim
    if (!trimmed.isEmpty) Some(trimmed) else None
  }

  def parseAge(input: String): Option[Int] = {
    try {
      val age = input.trim.toInt
      if (age >= 0) Some(age) else None
    } catch {
      case _: NumberFormatException => None
    }
  }

  def parsePerson(inputName: String, inputAge: String): Option[Person] =
    for {
      name <- parseName(inputName)
      age  <- parseAge(inputAge)
    } yield Person(name, age)

  parsePerson("Bridget Jones", "29") pipe println
  // Result: Some(Person(Bridget Jones,29))

  parsePerson("Bridget Jones", "") pipe println
  // Result: None

  parsePerson("Bridget Jones", "-29") pipe println
  // Result: None

  parsePerson("", "") pipe println
  // Result: None
}
