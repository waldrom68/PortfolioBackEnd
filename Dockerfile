FROM amazoncorretto:19-alpine-jdk


MAINTAINER WDR

COPY target/wdr-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]