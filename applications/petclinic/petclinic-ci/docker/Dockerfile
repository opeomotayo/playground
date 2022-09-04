FROM openjdk:11.0.5-jdk
COPY *.jar app.jar
ENTRYPOINT java --Dspring.profiles.active=test -jar app.jar
