package guide.ch06tagging

import org.scalatest._
import org.scalatest.Tag
import org.scalatest.tagobjects.Slow

object DbTest extends Tag("com.mycompany.tags.DbTest")

class ExampleSpec extends flatspec.AnyFlatSpec {

  "The Scala language" must "add correctly" taggedAs (Slow) in {
    val sum = 1 + 1
    assert(sum === 2)
  }

  it must "subtract correctly" taggedAs (Slow, DbTest) in {
    val diff = 4 - 1
    assert(diff === 3)
  }
}
