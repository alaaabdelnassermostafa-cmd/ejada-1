# ─── Stage 1: Build (optional — skip if you pre-build with Maven) ─────────────
# If you prefer to build inside Docker, uncomment the stage below:
# FROM maven:3.9-eclipse-temurin-17 AS builder
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn package -DskipTests
# Then change the COPY below to: COPY --from=builder /app/target/*.jar app.jar

# ─── Stage: Runtime ───────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine

# Non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

# Copy the fat JAR built by Maven (mvn package)
COPY target/library-catalog-*.jar app.jar

# Switch to non-root user
USER spring:spring

# Expose the Spring Boot port
EXPOSE 8080

# Start the application with the docker Spring profile (uses PostgreSQL)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
