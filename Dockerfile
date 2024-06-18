# Eclipse Temurin OpenJDK 17 이미지를 사용
FROM eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

# Redis 설치
RUN sudo yum update && \
    sudo yum install -y redis-server && \
    sudo rm -rf /var/lib/apt/lists/*

EXPOSE 8080 6379

ENTRYPOINT ["java", "-jar", "/app.jar"]
