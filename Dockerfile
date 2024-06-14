# 베이스 이미지 설정
FROM openjdk:17-jdk-slim

# 스프링 부트 애플리케이션 빌드
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

# 런타임 이미지 설정
FROM openjdk:17-jdk-slim

# 빌드된 JAR 파일 복사
COPY --from=0 /app/build/libs/*.jar /app/app.jar

# 스프링 부트 애플리케이션 실행
CMD ["java", "-jar", "/app/app.jar"]
