FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN mkdir .image && cd .image && mkdir storage

EXPOSE 8080

COPY target/*.jar api.jar

ENTRYPOINT ["java","-jar","/api.jar"]