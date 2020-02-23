./gradlew clean && ./gradlew :common:quarkusBuild &&
  ./gradlew :application:quarkusBuild && java -jar application/build/*.jar
