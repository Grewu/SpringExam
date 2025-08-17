# syntax=docker/dockerfile:1

# --- Build Stage ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy only files needed for dependency resolution first (for better caching)
COPY --link build.gradle settings.gradle gradlew ./
COPY --link gradle ./gradle

# Download dependencies (leverages Docker cache)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# Copy the rest of the source code
COPY --link src ./src
COPY --link .gitattributes ./

# Build the application (skip tests for faster build)
RUN ./gradlew build --no-daemon -x test

# --- Runtime Stage ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Create a non-root user and group
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

# Copy the built jar from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Set permissions
RUN chown -R appuser:appgroup /app
USER appuser

# JVM options: container-aware memory, GC tuning
ENV JAVA_OPTS="-XX:MaxRAMPercentage=80.0 -XX:+UseContainerSupport"

EXPOSE 9011

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
