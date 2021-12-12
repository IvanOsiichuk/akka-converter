ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "akka-converter",
    idePackagePrefix := Some("org.akkaconverter")
  )
  .enablePlugins(SbtTwirl)
  .enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true
Compile / mainClass := Some("org.akkaconverter.client.Client")

val akkaVersion       = "2.6.17"
val akkaHttpVersion   = "10.2.7"
val slickVersion      = "3.3.3"
val slf4jVersion      = "1.7.32"
val postgresqlVersion = "42.3.1"
val configVersion     = "1.4.1"
val guice             = "5.0.2"
val playTwirlVersion  = "1.5.1"
val webjarsVersion    = "5.1.1"
val scalaJsVersion    = "2.0.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"      % akkaVersion,
  "com.typesafe.akka" %% "akka-http"        % akkaHttpVersion
)

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"          % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "org.slf4j"          % "slf4j-nop"       % slf4jVersion,
  "com.typesafe.play"  %% "twirl-api"      % playTwirlVersion,
  "org.postgresql"     % "postgresql"      % postgresqlVersion,
  "com.typesafe"       % "config"          % configVersion,
  "net.codingwell"     %% "scala-guice"    % guice,
  "org.webjars"        % "bootstrap"       % webjarsVersion,
  "org.scala-js"       %%% "scalajs-dom"   % scalaJsVersion
)
