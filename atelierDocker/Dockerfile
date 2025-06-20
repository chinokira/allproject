FROM openjdk:11-jdk

WORKDIR /app

COPY microservices-docker/message/target/message-0.0.1-SNAPSHOT.jar /app/message-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/message-0.0.1-SNAPSHOT.jar"]