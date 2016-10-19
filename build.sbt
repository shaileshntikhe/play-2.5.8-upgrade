name := """play-service"""

version := "7.5.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
	ws,
	"com.datastax.cassandra" % "cassandra-driver-core" % "2.0.12",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.apache.shiro" % "shiro-core" % "1.2.2",
  "org.apache.shiro" % "shiro-ehcache" % "1.2.2" exclude("net.sf.ehcache", "ehcache-core"),
  "com.typesafe.akka"  %% "akka-actor" %   "2.4.4",
  "com.typesafe.akka"  %%   "akka-slf4j"  %   "2.4.4",
  "com.typesafe.akka"  %%   "akka-contrib"  %   "2.4.4",
  "com.typesafe.akka"  %%   "akka-cluster"  %   "2.4.4",
  "com.tuplejump.utils" %% "xformer" % "0.0.1"
)

resolvers ++= Seq(
  "sgodbillon" at "https://bitbucket.org/sgodbillon/repository/raw/master/snapshots/",
  "terracotta" at "http://www.terracotta.org/download/reflector/releases"
)

/*ADDED TO KEEP OBJECTS AS CONTROLLERS
REFER DOCS:
1. https://www.playframework.com/documentation/2.5.x/Migration25#routes-generated-with-injectedroutesgenerator*/
routesGenerator := StaticRoutesGenerator
