FROM openjdk:11-slim as build
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
