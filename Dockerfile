# 1. 베이스 이미지 설정
FROM openjdk:17-jdk-slim AS base

# 2. 의존성 설치 단계
FROM base AS deps
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon

# 3. 빌드 단계
FROM base AS builder
WORKDIR /app
COPY --from=deps /app ./
COPY src ./src
RUN ./gradlew clean build -x test --no-daemon

# 4. 런타임 이미지 설정
FROM openjdk:17-jdk-slim AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# 애플리케이션 실행 환경 설정
ENV SPRING_PROFILES_ACTIVE=dev
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/app/logs/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/app/logs/heapdump.hprof"

EXPOSE 8080

# 애플리케이션 실행
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
