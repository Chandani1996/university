FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/university-*.jar university-app.jar
ENTRYPOINT ["java","-jar","/university-app.jar"]

