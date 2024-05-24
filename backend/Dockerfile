FROM openjdk:18
COPY target/backend-0.0.1-SNAPSHOT.jar /expense/backend/
WORKDIR /expense/backend

ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]
