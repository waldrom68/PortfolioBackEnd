FROM amazoncorretto:19

MAINTAINER wdr

COPY target/wdr-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]