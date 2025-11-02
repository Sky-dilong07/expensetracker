# ===== Stage 1: Build the app =====
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy all source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2: Run the app =====
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will automatically detect this)
EXPOSE 8083

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
