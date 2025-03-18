# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Package the application
RUN ./mvnw package

# Expose the application port and the debug port
EXPOSE 8080

# Run the application with remote debugging enabled
CMD ["java", "-jar", "target/hubspotintegration-0.0.1-SNAPSHOT.jar"]