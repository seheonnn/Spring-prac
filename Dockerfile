FROM adoptopenjdk:11-jdk-hotspot

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
