name := "Gym"

version := "1.0"

scalaVersion := "2.12.1"

// https://mvnrepository.com/artifact/org.deeplearning4j/gym-java-client
libraryDependencies += "org.deeplearning4j" % "gym-java-client" % "0.8.1-SNAPSHOT"

resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
