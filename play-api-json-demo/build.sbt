name := """play api json demo"""
organization := "septeni-technology"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.3"
libraryDependencies += "com.github.tototoshi" %% "play-json4s-native" % "0.8.0"
libraryDependencies += "com.github.tototoshi" %% "play-json4s-jackson" % "0.8.0"
libraryDependencies +=  "org.json4s" %% "json4s-native" % " 3.5.2"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "septeni-technology.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "septeni-technology.binders._"
