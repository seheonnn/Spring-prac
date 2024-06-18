FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

# Redis 설치
RUN apt-get update && \
    apt-get install -y redis-server && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 8080 6379

ENTRYPOINT ["java", "-jar", "/app.jar"]
