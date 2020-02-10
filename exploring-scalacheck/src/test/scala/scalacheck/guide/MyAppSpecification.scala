package scalacheck.guide

import org.scalacheck.Properties

object MyAppSpecification extends Properties("MyApp") {
  include(StringSpecification)
  include(ScalaCheckGuide)
}
