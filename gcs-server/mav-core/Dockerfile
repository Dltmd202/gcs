FROM openjdk:19-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/mav-core-0.0.1-SNAPSHOT.jar mav-core.jar
ENTRYPOINT ["java", "-jar", "mav-core.jar"]
