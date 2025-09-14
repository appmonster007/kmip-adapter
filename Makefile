## Directories for CI artifacts
ARTIFACTS_DIR := target/ci-artifacts
ART_UNIT_DIR := $(ARTIFACTS_DIR)/unit
ART_IT_DIR := $(ARTIFACTS_DIR)/integration
ART_PERF_DIR := $(ARTIFACTS_DIR)/performance
ART_COV_DIR := $(ARTIFACTS_DIR)/coverage
ART_LOGS_DIR := $(ARTIFACTS_DIR)/logs
JACOCO_SITE := target/site/jacoco
.PHONY: help install test tests all-tests test-unit test-integration benchmark format clean build run coverage \
	ci-clean ci-build ci-test-unit ci-test-integration ci-test-perf ci-test-perf-fast ci-coverage ci-report ci-report-json ci-docs ci-artifacts ci-all ci-all-fast

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
	mvn -DskipTests install

## Run complete pipeline (build → tests → coverage → benchmark)
all: build tests coverage benchmark ## Run complete pipeline without duplicate test runs

## Run all tests (alias)
all-tests: tests ## Run all tests (unit + integration)

## Convenience alias to run all tests
test: tests ## Run all tests (unit + integration)

## Run all tests (unit + integration) once
tests: ## Run all tests (unit + integration) once
	@echo "${GREEN}Running all tests (unit + integration) once...${RESET}"
	mvn -Pwith-integration verify

## Run unit tests
test-unit: ## Run unit tests
	@echo "${GREEN}Running unit tests...${RESET}"
	mvn test

## Run integration tests
test-integration: ## Run integration tests
	@echo "${GREEN}Running integration tests...${RESET}"
	mvn -Pwith-integration test

## Run a specific test class
test-class: ## Run a specific test class (e.g., make test-class TEST=com.example.TestClass)
	@if [ -z "$(TEST)" ]; then \
		echo "${YELLOW}Please specify a test class with TEST=ClassName${RESET}"; \
		exit 1; \
	fi
	mvn test -Dtest=$(TEST)

## Run performance benchmarks
benchmark: ## Run performance benchmarks (skip tests)
	@echo "${GREEN}Running performance benchmarks...${RESET}"
	mvn -Pperf -DskipTests verify

## Format code (uses Spotless if configured, else skips)
format: ## Format code (Spotless optional)
	@echo "${GREEN}Formatting code...${RESET}"
	@if grep -q "spotless-maven-plugin" pom.xml; then \
		echo "- Spotless detected. Running spotless:apply"; \
		mvn spotless:apply; \
	else \
		echo "- Spotless not configured in pom.xml. Skipping format."; \
	fi

## Clean build artifacts
clean: ## Clean build artifacts
	@echo "${GREEN}Cleaning...${RESET}"
	mvn clean

## Build the project (no implicit clean to avoid double-clean). Use `make clean` explicitly if needed.
build: install ## Build the project

## Run the application (optional; requires Spring Boot plugin)
run: ## Run the application (if spring-boot plugin is configured)
	@echo "${GREEN}Running application...${RESET}"
	@if grep -q "spring-boot-maven-plugin" pom.xml; then \
		mvn spring-boot:run; \
	else \
		echo "- Spring Boot plugin not configured in pom.xml. Nothing to run. Skipping."; \
	fi

## Generate code coverage report without rerunning tests
coverage: ## Generate code coverage report (reuses previous test run)
	@echo "${GREEN}Generating code coverage report from existing execution data...${RESET}"
	mvn -DskipTests jacoco:report
	@echo "${GREEN}Report generated at:${RESET} file://$(shell pwd)/target/site/jacoco/index.html"

########################################
# CI/CD targets (no redundant executions)
########################################

## Clean CI workspace
ci-clean:
	@echo "${GREEN}[CI] Cleaning workspace...${RESET}"
	rm -rf $(ARTIFACTS_DIR) target/ci-artifacts.zip || true
	mkdir -p $(ARTIFACTS_DIR) $(ART_UNIT_DIR) $(ART_IT_DIR) $(ART_PERF_DIR) $(ART_COV_DIR) $(ART_LOGS_DIR)

## Build only (no tests)
ci-build: ## Build project for CI (skip tests)
	@echo "${GREEN}[CI] Building project (skip tests)...${RESET}"
	mvn -DskipTests -DskipITs package

## Run unit tests only (exclude integration group) and collect reports
ci-test-unit: ## Run unit tests only and collect evidence
	@echo "${GREEN}[CI] Running unit tests...${RESET}"
	mvn -DexcludedGroups=integration test 2>&1 | tee $(ART_LOGS_DIR)/unit-tests.log
	@[ -d target/surefire-reports ] && cp -R target/surefire-reports/* $(ART_UNIT_DIR)/ || true

## Run integration tests only via Failsafe and collect reports
ci-test-integration: ## Run integration tests only and collect evidence
	@echo "${GREEN}[CI] Running integration tests...${RESET}"
	mvn -DskipTests -DskipITs=false failsafe:integration-test failsafe:verify 2>&1 | tee $(ART_LOGS_DIR)/integration-tests.log
	@[ -d target/failsafe-reports ] && cp -R target/failsafe-reports/* $(ART_IT_DIR)/ || true

## Run performance tests (JMH) and collect logs
ci-test-perf: ## Run performance tests and collect evidence
	@echo "${GREEN}[CI] Running performance benchmarks...${RESET}"
	mvn -Pperf -DskipTests -DskipITs verify 2>&1 | tee $(ART_PERF_DIR)/benchmarks.log
	@[ -d target/benchmarks ] && cp -R target/benchmarks $(ART_PERF_DIR)/ || true

## Run faster performance tests (reduced iterations) and collect logs
ci-test-perf-fast: ## Run fast performance benchmarks and collect evidence
	@echo "${GREEN}[CI] Running fast performance benchmarks...${RESET}"
	mvn -Pperf-fast -DskipTests -DskipITs verify 2>&1 | tee $(ART_PERF_DIR)/benchmarks.log
	@[ -d target/benchmarks ] && cp -R target/benchmarks $(ART_PERF_DIR)/ || true

## Generate code coverage (reuse previous test run) and collect site
ci-coverage: ## Generate coverage report and collect evidence
	@echo "${GREEN}[CI] Generating coverage site from existing data...${RESET}"
	mvn -DskipTests jacoco:report 2>&1 | tee $(ART_LOGS_DIR)/coverage.log
	@[ -d $(JACOCO_SITE) ] && cp -R $(JACOCO_SITE)/* $(ART_COV_DIR)/ || true

## Build consolidated CI report markdown
ci-report: ## Build consolidated CI evidence markdown
	@echo "${GREEN}[CI] Building consolidated CI report...${RESET}"
	@REPORT=$(ARTIFACTS_DIR)/summary.md; \
	{ \
		echo "# CI Evidence Summary"; \
		echo; \
		echo "Generated: $$(date)"; \
		echo; \
		echo "## Unit Tests"; \
		if ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1; then \
			RUN=$$(grep -ho 'tests="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
			FAIL=$$(grep -ho 'failures="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
			ERR=$$(grep -ho 'errors="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
			SKIP=$$(grep -ho 'skipped="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
			STATUS=$$( [ "$$FAIL" = "0" ] && [ "$$ERR" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" ); \
			echo "- Status: $$STATUS"; \
			echo "- Tests Run: $$RUN | Failures: $$FAIL | Errors: $$ERR | Skipped: $$SKIP"; \
			else echo "- Status: ⏳ Not run"; fi; \
		echo; \
		if ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1; then \
			RUN=$$(grep -ho 'tests="[0-9]\+' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
			if [ "$$RUN" -gt 0 ]; then \
				echo "## Integration Tests"; \
				FAIL=$$(grep -ho 'failures="[0-9]\+' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
				ERR=$$(grep -ho 'errors="[0-9]\+' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
				SKIP=$$(grep -ho 'skipped="[0-9]\+' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}'); \
				STATUS=$$( [ "$$FAIL" = "0" ] && [ "$$ERR" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" ); \
				echo "- Status: $$STATUS"; \
				echo "- Tests Run: $$RUN | Failures: $$FAIL | Errors: $$ERR | Skipped: $$SKIP"; \
			else \
				echo "<!-- ## Integration Tests (no tests found) -->"; \
			fi; \
		else \
			echo "<!-- ## Integration Tests (no test files found) -->"; \
		fi; \
		echo; \
		echo "## Performance Tests"; \
		if [ -s $(ART_PERF_DIR)/benchmarks.log ]; then \
			echo "- Status: ✅ Generated"; \
			echo "- Log: $(ART_PERF_DIR)/benchmarks.log"; \
			else echo "- Status: ⏳ Not run"; fi; \
		echo; \
		echo "## Code Coverage"; \
		if [ -f $(ART_COV_DIR)/jacoco.csv ]; then \
			COV=$$(tail -n +2 $(ART_COV_DIR)/jacoco.csv 2>/dev/null | head -1 | cut -d, -f4 | xargs printf "%.1f%%"); \
			echo "- Status: ✅ Generated"; \
			echo "- Overall Coverage: $$COV"; \
			echo "- Report: $(ART_COV_DIR)/index.html"; \
			else echo "- Status: ⏳ Not generated"; fi; \
		echo; \
		echo "## Artifacts"; \
		echo "- Unit: $(ART_UNIT_DIR)"; \
		echo "- Integration: $(ART_IT_DIR)"; \
		echo "- Performance: $(ART_PERF_DIR)"; \
		echo "- Coverage: $(ART_COV_DIR)"; \
	} > $$REPORT; \
	cat $$REPORT

## Build consolidated CI report as JSON (no external deps)
ci-report-json: ## Build consolidated CI evidence JSON
	@echo "${GREEN}[CI] Building JSON summary...${RESET}"
	@JSON=$(ARTIFACTS_DIR)/summary.json; \
	RUN_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'tests="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	FAIL_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'failures="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	ERR_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'errors="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	SKIP_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'skipped="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	RUN_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'tests="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	FAIL_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'failures="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	ERR_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'errors="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	SKIP_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'skipped="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	COV=$$( [ -f $(ART_COV_DIR)/jacoco.csv ] && tail -n +2 $(ART_COV_DIR)/jacoco.csv | head -1 | cut -d, -f4 | xargs printf "%.1f" || echo 0); \
	PERF=$$( [ -s $(ART_PERF_DIR)/benchmarks.log ] && echo true || echo false ); \
	{ \
		echo "{"; \
		echo "  \"generated\": \"$$(date -u +%Y-%m-%dT%H:%M:%SZ)\","; \
		echo "  \"unit\": { \"run\": $$RUN_U, \"failures\": $$FAIL_U, \"errors\": $$ERR_U, \"skipped\": $$SKIP_U },"; \
		echo "  \"integration\": { \"run\": $$RUN_I, \"failures\": $$FAIL_I, \"errors\": $$ERR_I, \"skipped\": $$SKIP_I },"; \
		echo "  \"performance\": { \"generated\": $$PERF, \"log\": \"$(ART_PERF_DIR)/benchmarks.log\" },"; \
		echo "  \"coverage\": { \"overall\": $$COV, \"report\": \"$(ART_COV_DIR)/index.html\" }"; \
		echo "}"; \
	} > $$JSON; \
	cat $$JSON

## Update project docs reports.md from CI artifacts (no test reruns)
ci-docs: ## Update docs/03-guides/testing/reports.md using CI artifacts
	@echo "${GREEN}[CI] Updating documentation from artifacts...${RESET}"
	@mkdir -p docs/03-guides/testing
	@OUT=docs/03-guides/testing/reports.md; \
	RUN_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'tests="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	FAIL_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'failures="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	ERR_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'errors="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	SKIP_U=$$(ls $(ART_UNIT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'skipped="[0-9]\+"' $(ART_UNIT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	RUN_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'tests="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	FAIL_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'failures="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	ERR_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'errors="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	SKIP_I=$$(ls $(ART_IT_DIR)/*.xml >/dev/null 2>&1 && grep -ho 'skipped="[0-9]\+"' $(ART_IT_DIR)/*.xml | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}' || echo 0); \
	COV=$$( [ -f $(ART_COV_DIR)/jacoco.csv ] && tail -n +2 $(ART_COV_DIR)/jacoco.csv | head -1 | cut -d, -f4 | xargs printf "%.1f%%" || echo "0%" ); \
	PERF=$$( [ -s $(ART_PERF_DIR)/benchmarks.log ] && echo "✅ Generated" || echo "⏳ Not run" ); \
	{ \
		echo "# Test Reports and Coverage"; \
		echo; \
		echo "## Latest Test Results"; \
		echo; \
		echo "Generated: $$(date)"; \
		echo; \
			echo "### Unit Tests"; \
		STATUS_U=$$( [ "$$FAIL_U" = "0" ] && [ "$$ERR_U" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" ); \
		echo "- **Status**: $$STATUS_U"; \
		echo "- **Tests Run**: $$RUN_U"; \
		echo "- **Failures**: $$FAIL_U"; \
		echo "- **Errors**: $$ERR_U"; \
		echo "- **Skipped**: $$SKIP_U"; \
		echo "- **Reports**: [Surefire XML](../../../target/ci-artifacts/unit/)"; \
		echo; \
		if [ "$$RUN_I" -gt 0 ]; then \
			echo "### Integration Tests"; \
			STATUS_I=$$( [ "$$FAIL_I" = "0" ] && [ "$$ERR_I" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" ); \
			echo "- **Status**: $$STATUS_I"; \
			echo "- **Tests Run**: $$RUN_I"; \
			echo "- **Failures**: $$FAIL_I"; \
			echo "- **Errors**: $$ERR_I"; \
			echo "- **Skipped**: $$SKIP_I"; \
			echo "- **Reports**: [Failsafe XML](../../../target/ci-artifacts/integration/)"; \
		else \
			echo "<!-- ### Integration Tests (no tests found) -->"; \
		fi; \
		echo; \
		echo "### Performance Tests"; \
		echo "- **Status**: $$PERF"; \
		echo "- **Log**: [benchmarks.log](../../../target/ci-artifacts/performance/benchmarks.log)"; \
		echo; \
		echo "### Code Coverage"; \
		echo "- **Status**: $$( [ -f $(ART_COV_DIR)/index.html ] && echo "✅ Generated" || echo "⏳ Not generated" )"; \
		echo "- **Overall Coverage**: $$COV"; \
		echo "- **Report**: [index.html](../../../target/ci-artifacts/coverage/index.html)"; \
		echo; \
		echo "## Report Locations"; \
		echo "- **Unit Reports**: [../../../target/ci-artifacts/unit/](../../../target/ci-artifacts/unit/)"; \
		echo "- **Integration Reports**: [../../../target/ci-artifacts/integration/](../../../target/ci-artifacts/integration/)"; \
		echo "- **Coverage Reports**: [../../../target/ci-artifacts/coverage/](../../../target/ci-artifacts/coverage/)"; \
		echo "- **Performance Logs**: [../../../target/ci-artifacts/performance/](../../../target/ci-artifacts/performance/)"; \
		} > $$OUT; \
		echo "Updated: $$OUT"

## Fast full CI pipeline (reduced performance duration)
ci-all-fast: ci-clean ci-build ci-test-unit ci-test-integration ci-test-perf-fast ci-coverage ci-report ci-report-json ci-docs ci-artifacts ## Run full CI pipeline (fast)

## Package all CI artifacts into a zip
	@echo "${GREEN}[CI] Packaging artifacts...${RESET}"
	cd target && zip -qr ci-artifacts.zip ci-artifacts || true
	@echo "${GREEN}[CI] Package:${RESET} file://$(shell pwd)/target/ci-artifacts.zip"

## Full CI pipeline (no redundant test runs)
ci-all: ci-clean ci-build ci-test-unit ci-test-integration ci-test-perf ci-coverage ci-report ci-report-json ci-docs ci-artifacts ## Run full CI pipeline

## Build all reports from existing artifacts (markdown + json + docs)
reports: ci-report ci-report-json ci-docs ## Generate reports and update docs (no test re-run)

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
