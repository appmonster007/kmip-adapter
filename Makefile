.PHONY: help install test test-unit test-integration benchmark format clean build run

# Colors
GREEN  := $(shell tput -Txterm setaf 2)
YELLOW := $(shell tput -Txterm setaf 3)
WHITE  := $(shell tput -Txterm setaf 7)
RESET  := $(shell tput -Txterm sgr0)

# Help documentation
TARGET_MAX_CHAR_NUM=20

## Show help
help:
	@echo ''
	@echo 'Usage:'
	@echo '  ${YELLOW}make${RESET} ${GREEN}<target>${RESET}'
	@echo ''
	@echo 'Targets:'
	@awk '/^[a-zA-Z\_-]+:.*?## .*$$/ { \
		helpMessage = match(lastLine, /^## (.*)/); \
		if (helpMessage) { \
			helpCommand = substr($$1, 0, index($$1, ":")-1); \
			helpMessage = substr(lastLine, RSTART + 3, RLENGTH); \
			printf "  ${YELLOW}%-$(TARGET_MAX_CHAR_NUM)s${GREEN}%s${RESET}\n", helpCommand, helpMessage; \
		} \
	} \
	{ lastLine = $$0 }' $(MAKEFILE_LIST) | sort -u

## Install project dependencies
install: ## Install project dependencies
	@echo "${GREEN}Installing dependencies...${RESET}"
	mvn clean install -DskipTests

## Run complete pipeline (clean → build → test → benchmark)
all: clean build test-unit test-integration benchmark ## Run complete pipeline

## Run all tests
all-tests: test-unit test-integration ## Run all tests (unit + integration)

## Run unit tests
test-unit: ## Run unit tests
	@echo "${GREEN}Running unit tests...${RESET}"
	mvn test

## Run integration tests
test-integration: ## Run integration tests
	@echo "${GREEN}Running integration tests...${RESET}"
	mvn verify -DskipUnitTests

## Run a specific test class
test-class: ## Run a specific test class (e.g., make test-class TEST=com.example.TestClass)
	@if [ -z "$(TEST)" ]; then \
		echo "${YELLOW}Please specify a test class with TEST=ClassName${RESET}"; \
		exit 1; \
	fi
	mvn test -Dtest=$(TEST)

## Run performance benchmarks
benchmark: ## Run performance benchmarks
	@echo "${GREEN}Running performance benchmarks...${RESET}"
	mvn clean test-compile exec:exec -Dexec.executable="java" \
		-Dexec.args="-cp target/test-classes:target/classes:$$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q) \
		org.openjdk.jmh.Main -f 1 -wi 3 -i 5"

## Format code
format: ## Format code using spotless
	@echo "${GREEN}Formatting code...${RESET}"
	mvn spotless:apply

## Clean build artifacts
clean: ## Clean build artifacts
	@echo "${GREEN}Cleaning...${RESET}"
	mvn clean

## Build the project
build: clean install ## Clean and build the project

## Run the application
run: ## Run the application
	@echo "${GREEN}Running application...${RESET}"
	mvn spring-boot:run

## Generate code coverage report
coverage: ## Generate code coverage report
	@echo "${GREEN}Generating code coverage report...${RESET}"
	mvn clean test jacoco:report
	@echo "${GREEN}Report generated at:${RESET} file://$(shell pwd)/target/site/jacoco/index.html"

## Check for dependency updates
dependency-updates: ## Check for dependency updates
	@echo "${GREEN}Checking for dependency updates...${RESET}"
	mvn versions:display-dependency-updates

## Show project information
info: ## Show project information
	@echo "${GREEN}Project Information:${RESET}"
	@echo "  Name: ${YELLOW}$(shell mvn help:evaluate -Dexpression=project.name -q -DforceStdout)${RESET}"
	@echo "  Version: ${YELLOW}$(shell mvn help:evaluate -Dexpression=project.version -q -DforceStdout)${RESET}"
	@echo "  Java Version: ${YELLOW}$(shell java -version 2>&1 | head -n 1)${RESET}"
	@echo "  Maven Version: ${YELLOW}$(shell mvn -v | head -n 1)${RESET}"
