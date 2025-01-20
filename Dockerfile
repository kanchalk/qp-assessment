FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/grocery-booking-api.jar /app/grocery-booking-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/grocery-booking-api.jar"]
