// See README.md for license details.

ThisBuild / scalaVersion     := "2.12.13"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.starryleo"

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "edu.berkeley.cs" %% "chisel3" % "3.4.3",
    "edu.berkeley.cs" %% "chiseltest" % "0.3.3" % "test"
  ),
  scalacOptions ++= Seq(
    "-Xsource:2.11",
    "-language:reflectiveCalls",
    "-deprecation",
    "-feature",
    "-Xcheckinit",
    // Enables autoclonetype2 in 3.4.x (on by default in 3.5)
    "-P:chiselplugin:useBundlePlugin"
  ),
  addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % "3.4.3" cross CrossVersion.full),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
)

lazy val root = (project in file("."))
  .settings(
    name := "chisel-bootcamp-learning",
    commonSettings
  )

lazy val demo = (project in file("0_demo"))
  .settings(
    name := "demo",
    commonSettings
  )

lazy val first_module = (project in file("2.1_first_module"))
  .settings(
    name := "first_module",
    commonSettings,
    libraryDependencies ++= Seq(
      "edu.berkeley.cs" %% "chisel-iotesters" % "1.5.3",
    ),
  )

