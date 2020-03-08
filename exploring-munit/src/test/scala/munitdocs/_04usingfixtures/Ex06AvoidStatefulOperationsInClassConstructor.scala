package munitdocs
package _04usingfixtures

import java.sql.Connection
import java.sql.DriverManager
import munit._

class Ex06AvoidStatefulOperationsInClassConstructor extends munit.FunSuite {

  // Don't do this, because the class may get initialized even if no tests run.
  private val db: Connection = DriverManager.getConnection("jdbc:h2:mem:", "sa", null)
  println(">>> database connection established")

  override def beforeAll(): Unit = {}

  override def afterAll(): Unit = {
    db.close()
    println("<<< database connection closed")
  }

  test("test1") {
    db // database connection has been initialized
    println("--- using database connection in test1")
  }

  test("test2") {
    // ...
    db // the same `db` instance as in "test1"
    println("--- using database connection in test2")
  }
}
