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
      scalactic,
      scalaTest,
      scalaTestApp,
      scalaMock,
      scalaCheck,
      scalaCheckDatetime,
      shapeless,
      catsEffect,
      silencerLib,
      silencerPlugin,
      kindProjectorPlugin,
      betterMonadicForPlugin
    ) ++ Seq(
      scalaTestPlusCheck,
      scalaCheckShapeless,
      seleniumJava,
      seleniumFirefox,
      munit,
      utest
    ).map(_ % Test),
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
          |import org.scalatest._
          |import org.scalacheck._
          |println
          |""".stripMargin // initialize REPL
  )
)

lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    name := projectName,
    description := projectDescription,
    crossScalaVersions := Seq.empty
  )

lazy val core = (project in file("core"))
  .dependsOn(compat213, util)
  .settings(
    name := "core",
    description := "My gorgeous core App",
    libraryDependencies ++= Seq(
      shapeless,
      fs2Core,
      fs2Io
    ) ++ {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, minor)) if minor >= 13 => Seq.empty
        // Macro paradise not needed in 2.13. Just use scalacOption -Ymacro-annotations. See project/ScalacOptions.scala
        case _ =>
          Seq(compilerPlugin("org.scalamacros" %% "paradise" % "2.1.1" cross CrossVersion.full))
      }
    },
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    // suppress unused import warnings in the scala repl
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
