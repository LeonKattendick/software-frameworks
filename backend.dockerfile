FROM maven:3.6.0-jdk-11-slim AS build
COPY ./ /home/
RUN mvn -f /home/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/backend-module/target/backend-module-1.0-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/backend.jar"]