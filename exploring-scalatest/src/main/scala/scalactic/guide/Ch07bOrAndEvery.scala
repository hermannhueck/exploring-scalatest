package scalactic.guide

import scala.util.chaining._

object Ch07bOrAndEvery extends util.App {

  case class Person(name: String, age: Int)

  def parseName(input: String): Either[String, String] = {
    val trimmed = input.trim
    if (!trimmed.isEmpty) Right(trimmed) else Left(s""""${input}" is not a valid name""")
  }

  def parseAge(input: String): Either[String, Int] = {
    try {
      val age = input.trim.toInt
      if (age >= 0) Right(age) else Left(s""""${age}" is not a valid age""")
    } catch {
      case _: NumberFormatException => Left(s""""${input}" is not a valid integer""")
    }
  }

  def parsePerson(inputName: String, inputAge: String): Either[String, Person] =
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
