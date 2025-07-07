FROM maven:3.9.10-eclipse-temurin-24 AS build 
COPY . . 
RUN mvn clean package -DskipTests

FROM openjdk:24-jdk-slim
COPY --from=build /target/demoapi-0.0.1-SNAPSHOT.jar demoapi.jar 
EXPOSE 8080 
ENTRYPOINT [ "java","-jar","demoapi.jar" ]