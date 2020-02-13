package _04usingfixtures

import java.sql.Connection
import java.sql.DriverManager
import munit._

class Ex03ReusableSuiteLocalFixtureSuite extends munit.FunSuite {

  val db = new Fixture[Connection]("database") {

    private var connection: Connection = null
    def apply()                        = connection

    override def beforeAll(): Unit = {
      connection = DriverManager.getConnection("jdbc:h2:mem:", "sa", null)
      println(">>> database connection established")
    }

    override def afterAll(): Unit = {
      connection.close()
      println("<<< database connection closed")
    }
  }

  override def munitFixtures = List(db)

  test("test1") {
    db() // database connection has been initialized
    println("--- using database connection in test1")
  }

  test("test2") {
    // ...
    db() // the same `db` instance as in "test1"
    println("--- using database connection in test2")
  }
}
