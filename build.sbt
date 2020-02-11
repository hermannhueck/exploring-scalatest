import Dependencies._
import ScalacOptions._

val projectName        = "exploring-scalatest"
val projectDescription = "Exploring scalatest"
val projectVersion     = "0.1.0"

val scala212               = "2.12.10"
val scala213               = "2.13.1"
val supportedScalaVersions = List(scala212, scala213)

inThisBuild(
  Seq(
    version := projectVersion,
    scalaVersion := scala213,
    crossScalaVersions := supportedScalaVersions,
    publish / skip := true,
    libraryDependencies ++= Seq(
      collectionCompat,
      silencerLib,
      silencerPlugin,
      kindProjectorPlugin,
      betterMonadicForPlugin
    ),
    Test / parallelExecution := false,
    // S = Small Stack Traces, D = print Duration
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oSD"),
    // run 100 tests for each property // -s = -minSuccessfulTests
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "100"),
    testFrameworks += new TestFramework("munit.Framework"),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    initialCommands :=
      s"""|
          |import scala.util.chaining._
          |println
          |""".stripMargin // initialize REPL
  )
)

lazy val root = (project in file("."))
  .aggregate(
    `exploring-scalatest`,
    `exploring-scalacheck`,
    `integration-scalatest-scalacheck`,
    `exploring-utest`,
    `exploring-minitest`
  )
  .settings(
    name := projectName,
    description := projectDescription,
    crossScalaVersions := Seq.empty
  )

lazy val `exploring-scalatest` = (project in file("exploring-scalatest"))
  .dependsOn(compat213, util)
  .settings(
    name := "exploring-scalatest",
    description := "Exploring ScalaTest",
    libraryDependencies ++= Seq(
      scalaTest,
      scalactic,
      scalaTestApp,
      scalaMock,
      scalaCheck,
      scalaTestPlusCheck,
      scalaCheckShapeless,
      seleniumJava,
      seleniumFirefox
    ),
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value)
  )

lazy val `exploring-scalacheck` = (project in file("exploring-scalacheck"))
  .dependsOn(compat213, util)
  .settings(
    name := "exploring-scalacheck",
    description := "Exploring ScalaCheck",
    libraryDependencies ++= Seq(
      scalaCheck,
      scalaCheckDatetime
    ),
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value)
  )

lazy val `integration-scalatest-scalacheck` = (project in file("integration-scalatest-scalacheck"))
  .dependsOn(compat213, util)
  .settings(
    name := "integration-scalatest-scalacheck",
    description := "Integration of ScalaTest and ScalaCheck",
    libraryDependencies ++= Seq(
      scalaTest,
      scalaCheck,
      scalaTestPlusCheck
    ),
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value)
  )

lazy val `exploring-utest` = (project in file("exploring-utest"))
  .dependsOn(compat213, util)
  .settings(
    name := "exploring-utest",
    description := "Exploring utest",
    libraryDependencies ++= Seq(
      utest,
      fansi,
      pprint
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value)
  )

lazy val `exploring-minitest` = (project in file("exploring-minitest"))
  .dependsOn(compat213, util)
  .settings(
    name := "exploring-minitest",
    description := "Exploring minitest",
    libraryDependencies ++= Seq(
      minitest,
      minitestLaws,
      monixExecution,
      catsEffect
    ),
    testFrameworks += new TestFramework("minitest.runner.Framework"),
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value)
  )

lazy val compat213 = (project in file("compat213"))
  .settings(
    name := "compat213",
    description := "compat library providing features of Scala 2.13 backported to 2.12",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value)
  )

lazy val util = (project in file("util"))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "util",
    description := "Utilities",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "build",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value)
  )
