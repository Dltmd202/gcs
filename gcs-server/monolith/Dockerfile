FROM openjdk:19-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/monolith-0.0.1-SNAPSHOT.jar monolith.jar
ENTRYPOINT ["java", "-jar", "monolith.jar"]
