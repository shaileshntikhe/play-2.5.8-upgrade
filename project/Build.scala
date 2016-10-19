//import sbt._
//import sbt.Keys._
//
//object ApplicationBuild extends Build {
//
//  val appName = "thatCamThing"
//  val appVersion = "3.0"
//
//  val scalaVersion = "2.10.4"
//
//  val appDependencies = Seq(
//    // Add your project dependencies here
//    "com.twitter" % "util-core" % "6.1.0",
//
//    "net.databinder.dispatch" %% "dispatch-core" % "0.10.0",
//
////    "play.modules.reactivemongo" %% "play2-reactivemongo" % "0.9",
////    "org.reactivemongo" % "play2-reactivemongo" % "0.8" cross CrossVersion.binary,
//    "org.apache.shiro" % "shiro-core" % "1.2.2",
//    "org.apache.shiro" % "shiro-ehcache" % "1.2.2" exclude("net.sf.ehcache", "ehcache-core"),
//    "net.sf.ehcache" % "ehcache-core" % "2.6.6",
//    "net.sf.ehcache" % "ehcache-terracotta" % "2.6.6",
//    "com.typesafe.akka"  %% "akka-actor" %   "2.3.4",
//    "com.typesafe.akka"  %%   "akka-slf4j"  %   "2.3.4",
//    "com.typesafe.akka"  %%   "akka-contrib"  %   "2.3.4",
//    "com.typesafe.akka"  %%   "akka-cluster"  %   "2.3.4"
//  )
//
//  libraryDependencies ++= Seq(
//    "com.twitter" % "util-core" % "6.1.0"
//  )
//
//  val main = play.Project(appName, appVersion, appDependencies).settings(
//
//    resolvers ++= Seq(
//      "sgodbillon" at "https://bitbucket.org/sgodbillon/repository/raw/master/snapshots/",
//      "terracotta" at "http://www.terracotta.org/download/reflector/releases",
//      "Twitter's Repository" at "http://maven.twttr.com/"
//    ),
//    checksums in update := Nil
//  )
//}
