FROM openjdk:17-jdk
ARG JAR_FILE=./api/build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 80

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]
