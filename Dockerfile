# Step 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml first to fetch dependencies (this speeds up future builds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copy the jar from the build stage - notice the artifactId from your pom 'tournamnet'
COPY --from=build /app/target/tournamnet-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Use a shell form to ensure environment variables are picked up correctly
ENTRYPOINT ["java", "-Xmx512m", "-jar", "app.jar"]