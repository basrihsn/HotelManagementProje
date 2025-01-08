#!/usr/bin/env bash
# Exit on error
set -e

# Build the project
./mvnw clean package -DskipTests

# Copy the built JAR to the target directory
cp target/*.jar target/app.jar 