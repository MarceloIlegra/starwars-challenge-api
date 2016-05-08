logLevel := Level.Warn

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.7.0-SNAPSHOT")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.0-RC1")