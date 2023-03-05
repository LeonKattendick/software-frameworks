FROM maven:3.6.0-jdk-11-slim AS build
COPY ./ /home/
RUN mvn -f /home/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/crawler-module/target/crawler-module-1.0-SNAPSHOT.jar /usr/local/lib/crawler.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/crawler.jar"]