# 베이스 이미지 설정
FROM openjdk:17-jdk-slim as builder

# 스프링 부트 애플리케이션 빌드
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

# Nginx 및 JAR 파일을 위한 별도의 컨테이너 사용
FROM nginx:alpine

# Nginx 설정 파일 복사
COPY .platform/nginx.conf /etc/nginx/nginx.conf

# Nginx를 위한 경로 설정
RUN mkdir -p /usr/share/nginx/html/app

# 스프링 부트 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar /usr/share/nginx/html/app/app.jar

# Nginx 및 스프링 부트 애플리케이션 실행
CMD ["sh", "-c", "java -jar /usr/share/nginx/html/app/app.jar & nginx -g 'daemon off;'"]
