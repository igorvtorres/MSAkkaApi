ThisBuild / version := "0.1.3-SNAPSHOT"
ThisBuild / organization := "com.it"
ThisBuild / scalaVersion := "2.13.6"

lazy val app = (project in file("MSAkkaApi"))
  .settings(
    assembly / mainClass := Some("com.it.app.Msakkaapi")
  )

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case PathList("reference.conf") => MergeStrategy.concat
  case x => MergeStrategy.first
}

//Compile / mainClass := Some("com.it.app.Msakkaapi")

val akkaVersion = "2.6.8"
val akkaHttpVersion = "10.2.4"
val akkaHttpJsonSerializersVersion = "1.36.0"
val mongoDbVersion = "4.2.3"
val guiceVersion = "5.0.1"
val scalaLibVersion = "2.13.6"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "de.heikoseeberger" %% "akka-http-jackson" % akkaHttpJsonSerializersVersion,
  "org.mongodb.scala" %% "mongo-scala-driver" % mongoDbVersion,
  "net.codingwell" %% "scala-guice" % guiceVersion,
  "org.scala-lang" % "scala-library" % scalaLibVersion
)
