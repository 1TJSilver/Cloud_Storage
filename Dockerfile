FROM openjdk:8-jdk-alpine

EXPOSE 8080

COPY target/HW_9_0_0_1_Cloud_Storage-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]