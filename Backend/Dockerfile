# Use Maven to build the project
FROM maven:3.8.5-openjdk-17 as build

# Set working directory
WORKDIR /app

# Copy the pom.xml and dependencies first (for better caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src/ ./src/

# Package the application
RUN mvn clean package

# Use a minimal Java runtime for the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the previous build stage
COPY --from=build /app/target/BetterBuy-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
