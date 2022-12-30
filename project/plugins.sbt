resolvers ++= Seq(
  "central" at "https://repo1.maven.org/maven2"
)

addSbtPlugin("com.codecommit" % "sbt-spiewak-sonatype" % "0.23.0")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"         % "2.4.4")
