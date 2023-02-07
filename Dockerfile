FROM amazoncorretto:19

COPY target/wdr-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]