#FROM amazoncorretto:19-alpine-jdk
FROM amazoncorretto:19
MAINTAINER WDR
COPY target/wdr-1.0.0.jar target/wdr-1.0.0.jar
ENTRYPOINT ["java","-jar","/wdr-1.0.0.jar"]
#EXPOSE 8080