# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "backend.jar"]