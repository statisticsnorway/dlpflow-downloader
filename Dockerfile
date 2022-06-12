FROM azul/zulu-openjdk-alpine:17
RUN apk --no-cache add curl
COPY target/dlpflow-downloader-*.jar dlpflow-downloader.jar
COPY target/classes/logback*.xml /conf/
EXPOSE 10310
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-Dcom.sun.management.jmxremote", "-Dmicronaut.bootstrap.context=true", "-Xmx512m", "-jar", "dlpflow-downloader.jar"]
