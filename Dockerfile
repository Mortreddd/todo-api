# Stage 1: Build the application using Maven
FROM eclipse-temurin:22-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Set execute permissions on the Maven wrapper
RUN chmod +x ./mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application using a JDK
FROM eclipse-temurin:22-jre
LABEL authors="Emmanuel"

# Copy the jar file from the build stage
COPY --from=build /app/target/todo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
