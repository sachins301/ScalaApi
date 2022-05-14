name := "ScalaApi"

version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.15"

val akkaVersion = "2.5.20"
val akkaHttpVersion = "10.1.7"
val scalaTestVersion = "3.2.11"
val sparkVersion = "2.4.5"

libraryDependencies ++= Seq(
  // akka streams
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  // akka http
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  // testing
  "org.scalatest" %% "scalatest" % scalaTestVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalamock" %% "scalamock" % "5.1.0",
  // JWT
  "com.pauldijou" %% "jwt-spray-json" % "2.1.0",
  //mysql
  "mysql" % "mysql-connector-java" % "8.0.29",
  "org.json4s" %% "json4s-native" % "4.0.5",
  "org.json4s" %% "json4s-jackson" % "4.0.5"


  //  "org.apache.spark" %% "spark-core" % sparkVersion,
//  "org.apache.spark" %% "spark-sql" % sparkVersion,
//
////  "org.scalatest" % "scalatest-core_2.12" % "3.2.11",
//  "org.json4s" %% "json4s-core" % "3.5.3",
//  "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"



)