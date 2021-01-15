name := "PlayTest"

version := "1.0"

lazy val `playtest` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(jdbc, ehcache, ws, specs2 % Test, guice)

unmanagedResourceDirectories in Test += baseDirectory(_ / "target/web/public/test").value

libraryDependencies ++= Seq(
  "mysql"                 % "mysql-connector-java"          % "8.0.22",
  "com.typesafe.play"     %% "play-json"                    % "2.9.1",
  "org.scalikejdbc"       %% "scalikejdbc"                  % "3.5.0",
  "org.scalikejdbc"       %% "scalikejdbc-config"           % "3.5.0",
  "org.scalikejdbc"       %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5",
  "org.skinny-framework" %% "skinny-orm" % "3.0.3",
  "org.skinny-framework" %% "skinny-task" % "3.0.3",
  "com.h2database" % "h2" % "1.4.+",
  "ch.qos.logback" % "logback-classic" % "1.1.+"
)