ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.2",
  "org.apache.spark" %% "spark-sql" % "3.2.2",
  "org.apache.spark" %% "spark-mllib" % "3.2.2",
  "org.apache.spark" %% "spark-streaming" % "3.2.2"
)


lazy val root = (project in file("."))
  .settings(
    name := "spark-samples"
  )
