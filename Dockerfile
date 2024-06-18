# Eclipse Temurin OpenJDK 17 이미지를 사용
FROM eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

# Redis 설치
RUN #apt-get update && \
#    apt-get install -y redis-server && \
#    rm -rf /var/lib/apt/lists/*

#EXPOSE 8080 6379
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
