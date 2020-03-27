package munitdocs
package _04usingfixtures

import java.sql.Connection
import java.sql.DriverManager

@com.github.ghik.silencer.silent("pure expression does nothing in statement position")
class Ex05AdhocSuiteLocalFixtureSuite extends munit.FunSuite {

  private var db: Connection = null
  def apply()                = db

  override def beforeAll(): Unit = {
    db = DriverManager.getConnection("jdbc:h2:mem:", "sa", null)
    println(">>> database connection established")
  }

  override def afterAll(): Unit = {
    db.close()
    println("<<< database connection closed")
  }

  test("test1") {
    // @com.github.ghik.silencer.silent("pure expression does nothing in statement position")
    db // database connection has been initialized
    println("--- using database connection in test1")
  }

  test("test2") {
    // @com.github.ghik.silencer.silent("pure expression does nothing in statement position")
    db // the same `db` instance as in "test1"
    println("--- using database connection in test2")
  }
}
