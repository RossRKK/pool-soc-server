./gradlew clean build bootRepackage
##these paths are stupid, for some reason classpath didn't work
cd build/resources/main
java -jar ../../libs/pool-soc-server-0.1.0.jar

