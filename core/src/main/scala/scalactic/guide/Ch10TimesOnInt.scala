package scalactic.guide

import org.scalactic._
import TimesOnInt._

object Ch10TimesOnInt extends util.App {

  3 times println("Hello again, world!")

  println

  2 times {
    print("Hello ")
    print("again, ")
    println("world!")
  }
}
