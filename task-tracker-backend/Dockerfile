# =========================
# 1. Build stage
# =========================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom.xml first to leverage Docker layer caching
COPY pom.xml ./

# Download dependencies (cached unless pom.xml changes)
RUN mvn -q dependency:go-offline

# Now copy source code
COPY src ./src

# Build the JAR
RUN mvn -q clean package -DskipTests


# =========================
# 2. Runtime stage
# =========================
FROM eclipse-temurin:21-jre

# Create non-root user for security
RUN useradd -m spring

WORKDIR /app

# Copy JAR from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot's port
EXPOSE 8080

# Switch to non-root user
USER spring

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
