FROM openjdk:19-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/mavlink-monolith-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
