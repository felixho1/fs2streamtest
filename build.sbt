name := "fs2streamtest"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.typelevel" %% "cats-effect" % "1.2.0" withSources () withJavadoc ()
libraryDependencies += "co.fs2" %% "fs2-core" % "1.0.4" // For cats 1.5.0 and cats-effect 1.2.0
libraryDependencies += "co.fs2" %% "fs2-io" % "1.0.4"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Ypartial-unification"
)
