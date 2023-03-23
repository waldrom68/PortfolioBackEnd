FROM amazoncorretto:19
MAINTAINER WDR
COPY target/wdr-1.0.0.jar wdr-1.0.0.jar
ENTRYPOINT ["java","-jar","/wdr-1.0.0.jar"]