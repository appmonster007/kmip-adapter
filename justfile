# Just command runner for KMIP Adapter
# Run `just --list` to see all available commands

# Set default shell to bash for better script handling
set shell := ["bash", "-c"]

# Default target when running just without arguments
default: help

# Run complete pipeline (clean → build → test → benchmark)
all: clean build test benchmark

# Show this help
help:
    #!/usr/bin/env bash
    echo "KMIP Adapter Development Commands:"
    echo "  just install      - Install project dependencies"
    echo "  just test         - Run all tests (unit + integration)"
    echo "  just test-unit    - Run unit tests"
    echo "  just test-integration - Run integration tests"
    echo "  just test-class TEST=... - Run specific test class"
    echo "  just benchmark    - Run performance benchmarks"
    echo "  just format       - Format code using spotless"
    echo "  just clean        - Clean build artifacts"
    echo "  just build        - Clean and build the project"
    echo "  just run          - Run the application"
    echo "  just coverage     - Generate code coverage report"
    echo "  just info         - Show project information"

# Install project dependencies
install:
    @echo "📦 Installing dependencies..."
    mvn clean install -DskipTests

# Run all tests (unit + integration)
test:
    @echo "🧪 Running all tests (unit + integration)..."
    mvn -Pwith-integration test

# Run unit tests
test-unit:
    @echo "🧪 Running unit tests..."
    mvn test

# Run integration tests
test-integration:
    @echo "🔍 Running integration tests..."
    mvn -Pwith-integration test

# Run specific test class
test-class TEST:
    #!/usr/bin/env bash
    if [ -z "{{TEST}}" ]; then
        echo "❌ Please specify a test class with TEST=ClassName"
        exit 1
    fi
    echo "🧪 Running test class: {{TEST}}"
    mvn test -Dtest={{TEST}}

# Run performance benchmarks
benchmark:
    @echo "⚡ Running performance benchmarks..."
    mvn -Pperf verify

# Format code
format:
    @echo "🎨 Formatting code..."
    mvn spotless:apply

# Clean build artifacts
clean:
    @echo "🧹 Cleaning..."
    mvn clean

# Build the project
build: clean install

# Run the application
run:
    @echo "🚀 Running application..."
    mvn spring-boot:run

# Generate code coverage report
coverage:
    @echo "📊 Generating code coverage report..."
    mvn clean test jacoco:report
    @echo "📄 Report generated at: file://$(pwd)/target/site/jacoco/index.html"

# Show project information
info:
    @echo "📋 Project Information:"
    @echo "  Name: $(mvn help:evaluate -Dexpression=project.name -q -DforceStdout)"
    @echo "  Version: $(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)"
    @echo "  Java Version: $(java -version 2>&1 | head -n 1)"
    @echo "  Maven Version: $(mvn -v | head -n 1)"
