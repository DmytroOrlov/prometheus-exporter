import Dependencies._
import sbt.Resolver

val prometheusExporter = project.in(file("."))
  .settings(
    inThisBuild(List(
      organization := "com.evolutiongaming",
      scalaVersion := "2.13.1",
      developers := List(Developer("stas", "Stas", "stass.ua@gmail.com", url("https://www.linkedin.com/in/stas-shevchenko-916b7a16"))),
      scmInfo := Some(ScmInfo(url("https://github.com/evolution-gaming/prometheus-exporter"), "scm:git:git@github.com:evolution-gaming/prometheus-exporter.git")),
    )),
    name := "prometheus-exporter",
    licenses := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.html")),
    scalacOptions in(Compile, doc) ++= Seq("-no-link-warnings"),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Xfatal-warnings",
      "-Xlint",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen"
    ),
    resolvers += Resolver.bintrayRepo("evolutiongaming", "maven"),
    homepage := Some(new URL("https://github.com/evolution-gaming/prometheus-exporter")),
    startYear := Some(2017),
    organizationName := "Evolution Gaming",
    organizationHomepage := Some(url("http://evolutiongaming.com")),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.5.26",
      "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.26" % Test,
      prometheusCommonClient, prometheusHotspotClient, simpleClientLogback, logging,
      Akka.Http, Akka.HttpTestKit % Test, scalatest % Test, pegdown % Test) map Dependencies.excludeLog4j,
    testOptions in Test ++= Seq(
      Tests.Argument(
        TestFrameworks.ScalaTest,
        "-oDS",
        "-h",
        "target/test-reports"
      )
    ),
    publishTo := {
      val nexus = "https://nexus.com/nexus/content/repositories/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "snapshots")
      else
        Some("releases" at nexus + "releases")
    }
  )
