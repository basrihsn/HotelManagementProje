#!/usr/bin/env bash
# Exit on error
set -e

# Make mvnw executable
chmod +x mvnw

# Build the project and create JAR
./mvnw clean package -DskipTests 