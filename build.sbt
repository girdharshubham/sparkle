name := "sparkle"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" % "spark-sql-kafka-0-10_2.11" % "2.4.0" % "provided",
  "org.apache.kafka" % "kafka-clients" % "0.11.0.1"
)
