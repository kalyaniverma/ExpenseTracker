FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar .
# Create log directory
RUN mkdir -p /var/log/spring-boot-app
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]