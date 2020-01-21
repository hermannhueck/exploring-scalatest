package scalactic.guide

import scala.util.chaining._

import org.scalactic._

object Ch07fOrAndEvery extends util.App {

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

  "Using Accumulation.combined ..." pipe println

  List(parseAge("29"), parseAge("30"), parseAge("31")).combined pipe println
  // Result: Good(List(29, 30, 31))

  List(parseAge("29"), parseAge("-30"), parseAge("31")).combined pipe println
  // Result: Bad(One("-30" is not a valid age))

  List(parseAge("29"), parseAge("-30"), parseAge("-31")).combined pipe println
  // Result: Bad(Many("-30" is not a valid age, "-31" is not a valid age))

  "\nUsing Accumulation.validatedBy ..." pipe println

  List("29", "30", "31").validatedBy(parseAge) pipe println
  // Result: Good(List(29, 30, 31))

  List("29", "-30", "31").validatedBy(parseAge) pipe println
  // Result: Bad(One("-30" is not a valid age))

  List("29", "-30", "-31").validatedBy(parseAge) pipe println
  // Result: Bad(Many("-30" is not a valid age, "-31" is not a valid age))

  "\nUsing Accumulation.zip ..." pipe println

  parseName("Dude") zip parseAge("21") pipe println
  // Result: Good((Dude,21))

  parseName("Dude") zip parseAge("-21") pipe println
  // Result: Bad(One("-21" is not a valid age))

  parseName("") zip parseAge("-21") pipe println
  // Result: Bad(Many("" is not a valid name, "-21" is not a valid age))

  "\nUsing Accumulation.when ..." pipe println

  def isRound(i: Int): Validation[ErrorMessage] =
    if (i % 10 == 0) Pass else Fail(s"$i was not a round number")

  def isDivBy3(i: Int): Validation[ErrorMessage] =
    if (i % 3 == 0) Pass else Fail(s"$i was not divisible by 3")

  parseAge("-30").when(isRound, isDivBy3) pipe println
  // Result: Bad(One("-30" is not a valid age))

  parseAge("30").when(isRound, isDivBy3) pipe println
  // Result: Good(30)

  parseAge("33").when(isRound, isDivBy3) pipe println
  // Result: Bad(One(33 was not a round number))

  parseAge("20").when(isRound, isDivBy3) pipe println
  // Result: Bad(One(20 was not divisible by 3))

  parseAge("31").when(isRound, isDivBy3) pipe println
  // Result: Bad(Many(31 was not a round number, 31 was not divisible by 3))

  for (age <- parseAge("-30") when (isRound, isDivBy3)) yield age pipe println
  // Result: Bad(One("-30" is not a valid age))

  for (age <- parseAge("30") when (isRound, isDivBy3)) yield age pipe println
  // Result: Good(30)

  for (age <- parseAge("33") when (isRound, isDivBy3)) yield age pipe println
  // Result: Bad(One(33 was not a round number))

  for (age <- parseAge("20") when (isRound, isDivBy3)) yield age pipe println
  // Result: Bad(One(20 was not divisible by 3))

  for (age <- parseAge("31") when (isRound, isDivBy3)) yield age pipe println
  // Result: Bad(Many(31 was not a round number, 31 was not divisible by 3))
}
