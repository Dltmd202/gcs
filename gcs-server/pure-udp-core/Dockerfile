FROM openjdk:18-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/pure-udp-core-0.0.1-SNAPSHOT.jar udp.jar
ENV file.dir /tmp/files
ENTRYPOINT ["java", "-jar", "udp.jar"]
