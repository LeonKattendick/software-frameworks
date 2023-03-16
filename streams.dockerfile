FROM maven:3.6.0-jdk-11-slim AS build
COPY ./ /home/
RUN mvn -f /home/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/kafka-module/target/kafka-module-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/kafka.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/kafka.jar"]