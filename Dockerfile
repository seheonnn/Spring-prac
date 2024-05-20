#FROM openjdk:17-jdk
#ARG JAR_FILE=./build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#
#EXPOSE 80
#
#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]

# =======================

#FROM ubuntu:22.04
#
#RUN apt-get update
#RUN apt-get install -y nginx zip curl
#
#RUN echo "daemon off;" >> /etc/nginx/nginx.conf
#RUN curl -o /usr/share/nginx/www/master.zip -L https://codeload.github.com/gabrielecirulli/2048/zip/master
#RUN cd /usr/share/nginx/www/ && unzip master.zip && mv 2048-master/* . && rm -rf 2048-master master.zip
#
#EXPOSE 80
#
#CMD ["/usr/sbin/nginx", "-c", "/etc/nginx/nginx.conf"]

# =======================

FROM ubuntu:22.04

RUN apt-get update \
    && apt-get install -y nginx zip curl

RUN echo "daemon off;" >> /etc/nginx/nginx.conf

# /usr/share/nginx/www 디렉토리 생성
RUN mkdir -p /usr/share/nginx/www

# 파일 다운로드
RUN curl -o /usr/share/nginx/www/master.zip -L https://codeload.github.com/gabrielecirulli/2048/zip/master

# 파일 압축 해제 및 이동
RUN cd /usr/share/nginx/www/ && unzip master.zip && mv 2048-master/* . && rm -rf 2048-master master.zip

EXPOSE 80

CMD ["/usr/sbin/nginx", "-c", "/etc/nginx/nginx.conf"]
