package scalacheck.guide

import org.scalacheck.Gen
import org.scalacheck.Prop._

object CollectTestdataCheck extends util.App {

  val dummyProp = forAll(Gen.choose(1, 10)) { n =>
    collect(n) {
      n == n
    }
  }

  dummyProp.check
}
