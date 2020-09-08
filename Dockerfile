# Build
FROM maven:3.6.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
# Package
FROM openjdk:8
COPY --from=build /home/app/target/messageHashStore-0.0.1-SNAPSHOT.jar /usr/local/lib/messageHashStore-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/messageHashStore-0.0.1-SNAPSHOT.jar"]