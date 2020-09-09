FROM maven:3.6.3-jdk-8 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app dependency:go-offline -B
RUN mvn -f /home/app/pom.xml clean package
FROM openjdk:8u171-jre-alpine
COPY --from=build /home/app/target/messageHashStore-0.0.1-SNAPSHOT.jar /usr/local/lib/messageHashStore-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/messageHashStore-0.0.1-SNAPSHOT.jar"]