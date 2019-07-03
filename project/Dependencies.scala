import sbt._

object Dependencies {
  private val prometheusVersion = "0.0.26"

  private val scalaLoggingVersion = "3.9.2"

  lazy val prometheusCommonClient = "io.prometheus" % "simpleclient_common" % prometheusVersion
  lazy val prometheusHotspotClient = "io.prometheus" % "simpleclient_hotspot" % prometheusVersion
  lazy val simpleClientLogback = "io.prometheus" % "simpleclient_logback" % prometheusVersion
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val pegdown = "org.pegdown" % "pegdown" % "1.6.0"

  lazy val logging = "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion

  object Akka {
    private val akkaHttpVersion = "10.1.8"

    val Http = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    val HttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  }


  def excludeLog4j(moduleID: ModuleID) = moduleID.excludeAll(
    ExclusionRule("log4j", "log4j"),
    ExclusionRule("org.slf4j", "slf4j-log4j12"),
    ExclusionRule("commons-logging", "commons-logging"))
}
