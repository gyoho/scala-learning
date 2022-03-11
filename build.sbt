name := "scala-learning"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.softwaremill.quicklens" % "quicklens_2.12" % "1.4.8",
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "org.typelevel" %% s"cats-core" % "2.5.0"
)
