FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar .
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]