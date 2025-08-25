# Use a multi-stage build to keep the final image small

# First stage: Build the Spring Boot application
# Uses a Maven image with OpenJDK 17
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build the Spring Boot JAR, skipping tests for faster build
RUN mvn clean package -DskipTests

# Second stage: Run the Spring Boot application
# Uses a JRE-only slim image for OpenJDK 17 from Eclipse Temurin (on Ubuntu Focal)
# This is lighter than a full JDK image
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
# Copy the built JAR from the 'build' stage into the /app directory of this new image
COPY --from=build /app/target/*.jar app.jar
# Expose the port that your Spring Boot application will listen on
EXPOSE 8080
# Define the command to run your Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
