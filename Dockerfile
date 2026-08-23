FROM openjdk:21-ea-21-jdk-slim-buster
ARG JAR_FILE=target/turnos-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_turnos.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app_turnos.jar"]
