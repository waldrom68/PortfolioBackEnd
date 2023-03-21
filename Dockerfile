FROM amazoncorretto:19-alpine-jdk


MAINTAINER WDR

COPY target/wdr-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]