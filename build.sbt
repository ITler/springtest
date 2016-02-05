name := """springtest"""

EclipseKeys.skipParents in ThisBuild := false

//onLoad in Global := { Command.process("project core", _: State) } compose (onLoad in Global).value


lazy val scala_version = "2.11.6"
lazy val spring_boot_release = "1.3.0.RELEASE"

scalaVersion := scala_version

val scalaBuildOptions = Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls",
    "-language:implicitConversions", "-language:postfixOps", "-language:dynamics","-language:higherKinds",
    "-language:existentials", "-language:experimental.macros", "-Xmax-classfile-name", "140")

lazy val commonSettings = Seq(
  organization := "com.itl",
  resolvers += "Spring releases" at "https://repo.spring.io/libs-release",
  version := "0.9-SNAPSHOT",
  routesGenerator := InjectedRoutesGenerator,
  scalaVersion := scala_version
)

lazy val common = (project in file("common")).enablePlugins(PlayJava).
  settings(commonSettings: _*).
  settings(
      libraryDependencies ++= Seq(
          "org.springframework.boot" % "spring-boot-starter-data-jpa" % spring_boot_release,
          "org.springframework.boot" % "spring-boot-starter-parent" % spring_boot_release,
          "com.h2database" % "h2" % "1.4.190",
          "joda-time" % "joda-time-hibernate" % "1.4",
          "javax.inject" % "javax.inject" % "1",
          "junit" % "junit" % "4.12"  % "test",
          "com.novocode" % "junit-interface" % "0.11"  % "test",
          "org.mockito" % "mockito-core" % "1.10.5" % "test"
      )
  )

lazy val frontend = (project in file("frontend")).enablePlugins(PlayJava).
  settings(commonSettings: _*).
  settings(
      scalacOptions ++= scalaBuildOptions,
      javaOptions += s"-Dconfig.resource=frontend-application.conf",
      libraryDependencies ++= Seq(
          "org.springframework.boot" % "spring-boot-starter-data-jpa" % spring_boot_release,
          "org.springframework.boot" % "spring-boot-starter-parent" % spring_boot_release,
          "com.h2database" % "h2" % "1.4.190",
          "joda-time" % "joda-time-hibernate" % "1.4",
          "javax.inject" % "javax.inject" % "1",
          "junit" % "junit" % "4.12"  % "test",
          "com.novocode" % "junit-interface" % "0.11"  % "test",
          "org.mockito" % "mockito-core" % "1.10.5" % "test"
      )
  ).dependsOn(common % "test->test;compile->compile")

lazy val springtest = (project in file(".")).enablePlugins(PlayJava).
  settings(commonSettings: _*).
  settings(

  ).
  dependsOn(common % "test->test;compile->compile").
  dependsOn(frontend % "test->test;compile->compile").
  aggregate(frontend, common)

