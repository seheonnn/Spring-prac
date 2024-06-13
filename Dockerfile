# 베이스 이미지 설정
FROM openjdk:17-jdk-slim as builder

# 스프링 부트 애플리케이션 빌드
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM nginx:alpine

# Nginx 설정 파일 복사
COPY .platform/nginx.conf /etc/nginx/nginx.conf

# 스프링 부트 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar /usr/share/nginx/html/app.jar

# Nginx 및 스프링 부트 애플리케이션 실행
CMD ["sh", "-c", "java -jar /usr/share/nginx/html/app.jar & nginx -g 'daemon off;'"]

#FROM adoptopenjdk:11-jdk-hotspot
#
#ARG JAR_FILE=build/libs/*.jar
#
#COPY ${JAR_FILE} app.jar
#
#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev","/app.jar"]
