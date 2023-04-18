FROM openjdk:17
# Set the working directory
WORKDIR /app

# Copy the jar file to the working directory
COPY target/*.jar mock.jar




# Run the application
CMD ["java", "-jar", "mock.jar"]