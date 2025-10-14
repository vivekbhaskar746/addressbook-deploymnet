# Use a lightweight Java runtime
FROM eclipse-temurin:17-jre-jammy

# Create app directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/addressbook-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]