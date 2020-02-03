FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8080

#ARG JAR_FILE=target/apifaceframe-*.jar

COPY target/*.jar api.jar

#COPY ${JAR_FILE} api.jar

ENTRYPOINT ["java","-jar","/api.jar"]