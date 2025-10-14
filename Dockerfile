# Use a lightweight Java runtime
FROM eclipse-temurin:17-jre-jammy

# Create app directory
WORKDIR /app

# Copy the JAR file into the container
COPY addressbook-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]