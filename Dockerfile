FROM amazoncorretto:19
MAINTAINER WDR
COPY target/wdr-2.0.0.jar wdr-2.0.0.jar
ENTRYPOINT ["java","-jar","/wdr-2.0.0.jar"]