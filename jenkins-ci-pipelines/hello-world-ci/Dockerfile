<<<<<<< HEAD
FROM node:18 AS build-container
ADD . /app
=======
FROM openjdk:11-jdk-slim-bullseye AS build-env
COPY . /app/examples
>>>>>>> Testing hello java
WORKDIR /app
RUN javac examples/*.java
RUN jar cfe main.jar examples.HelloJava examples/*.class 

<<<<<<< HEAD
FROM gcr.io/distroless/nodejs18-debian11 AS deploy-container
COPY --from=build-container /app /app
=======
FROM gcr.io/distroless/java11-debian11
COPY --from=build-env /app /app
>>>>>>> Testing hello java
WORKDIR /app
CMD ["main.jar"]
