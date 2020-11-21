name := "example"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.0.1",
  "org.apache.spark" %% "spark-sql" % "3.0.1",
  "org.apache.spark" %% "spark-mllib" % "3.0.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "3.0.1" % "provided",
  "org.apache.spark" %% "spark-graphx" % "3.0.1",
  "org.apache.spark" %% "spark-yarn" % "3.0.1" % "provided"
)
