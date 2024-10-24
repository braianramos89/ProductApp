FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/*.jar productapp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "productapp.jar"]