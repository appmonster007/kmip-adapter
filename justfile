# Just command runner for KMIP Adapter
# Run `just --list` to see all available commands

# Set default shell to bash for better script handling
set shell := ["bash", "-c"]

# Default target when running just without arguments
default: help

# Run complete pipeline (build → tests → coverage → benchmark)
all: build tests coverage benchmark

# Show this help
help:
    #!/usr/bin/env bash
    echo "KMIP Adapter Development Commands:"
    echo "  just install      - Install project dependencies"
    echo "  just tests        - Run all tests (unit + integration) once"
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

# Run all tests (unit + integration) once
tests:
    @echo "🧪 Running all tests (unit + integration) once..."
    mvn -Pwith-integration verify

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
    mvn -Pperf -DskipTests verify

# Format code
format:
    #!/usr/bin/env bash
    echo "🎨 Formatting code..."
    if grep -q "spotless-maven-plugin" pom.xml; then
        echo "- Spotless detected. Running spotless:apply"
        mvn spotless:apply
    else
        echo "- Spotless not configured in pom.xml. Skipping format."
    fi

# Clean build artifacts
clean:
    @echo "🧹 Cleaning..."
    mvn clean

# Build the project
build: install

# Run the application
run:
    #!/usr/bin/env bash
    echo "🚀 Running application..."
    if grep -q "spring-boot-maven-plugin" pom.xml; then
        mvn spring-boot:run
    else
        echo "- Spring Boot plugin not configured in pom.xml. Nothing to run. Skipping."
    fi

# Generate code coverage report
coverage:
    @echo "📊 Generating code coverage report (reusing exec data)..."
    mvn -DskipTests jacoco:report
    @echo "📄 Report generated at: target/site/jacoco/index.html"

# ==========================
# CI/CD tasks
# ==========================

ci-clean:
    #!/usr/bin/env bash
    echo "🧹 [CI] Cleaning workspace..."
    rm -rf target/ci-artifacts target/ci-artifacts.zip || true
    mkdir -p target/ci-artifacts/{unit,integration,performance,coverage,logs}

ci-build:
    @echo "🏗️ [CI] Building (skip tests)..."
    mvn -DskipTests -DskipITs package

ci-test-unit:
    #!/usr/bin/env bash
    echo "🧪 [CI] Unit tests..."
    mvn -DexcludedGroups=integration test 2>&1 | tee target/ci-artifacts/logs/unit-tests.log
    [ -d target/surefire-reports ] && cp -R target/surefire-reports/* target/ci-artifacts/unit/ || true

ci-test-integration:
    #!/usr/bin/env bash
    echo "🔍 [CI] Integration tests..."
    mvn -DskipTests -DskipITs=false failsafe:integration-test failsafe:verify 2>&1 | tee target/ci-artifacts/logs/integration-tests.log
    [ -d target/failsafe-reports ] && cp -R target/failsafe-reports/* target/ci-artifacts/integration/ || true

ci-test-perf:
    #!/usr/bin/env bash
    echo "⚡ [CI] Performance benchmarks..."
    mvn -Pperf -DskipTests -DskipITs verify 2>&1 | tee target/ci-artifacts/performance/benchmarks.log
    [ -d target/benchmarks ] && cp -R target/benchmarks target/ci-artifacts/performance/ || true

ci-test-perf-fast:
    #!/usr/bin/env bash
    echo "⚡ [CI] Fast performance benchmarks..."
    mvn -Pperf-fast -DskipTests -DskipITs verify 2>&1 | tee target/ci-artifacts/performance/benchmarks.log
    [ -d target/benchmarks ] && cp -R target/benchmarks target/ci-artifacts/performance/ || true

ci-coverage:
    #!/usr/bin/env bash
    echo "📊 [CI] Coverage site..."
    mvn -DskipTests jacoco:report 2>&1 | tee target/ci-artifacts/logs/coverage.log
    [ -d target/site/jacoco ] && cp -R target/site/jacoco/* target/ci-artifacts/coverage/ || true

ci-report:
    #!/usr/bin/env bash
    REPORT=target/ci-artifacts/summary.md
    ART_UNIT_DIR=target/ci-artifacts/unit
    ART_IT_DIR=target/ci-artifacts/integration
    ART_PERF_DIR=target/ci-artifacts/performance
    ART_COV_DIR=target/ci-artifacts/coverage
    RUN_U=$(grep -ho 'tests="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    FAIL_U=$(grep -ho 'failures="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    ERR_U=$(grep -ho 'errors="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    SKIP_U=$(grep -ho 'skipped="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    RUN_I=$(grep -ho 'tests="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    FAIL_I=$(grep -ho 'failures="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    ERR_I=$(grep -ho 'errors="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    SKIP_I=$(grep -ho 'skipped="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    COV=$( [ -f "$ART_COV_DIR"/jacoco.csv ] && tail -n +2 "$ART_COV_DIR"/jacoco.csv | head -1 | cut -d, -f4 | xargs printf "%.1f%%" || echo "0%" )
    PERF=$( [ -s "$ART_PERF_DIR"/benchmarks.log ] && echo "✅ Generated" || echo "⏳ Not run" )
    {
      echo "# CI Evidence Summary"; echo; echo "Generated: $(date)"; echo;
      echo "## Unit Tests"; STATUS_U=$( [ "$FAIL_U" = "0" ] && [ "$ERR_U" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" );
      echo "- Status: $STATUS_U"; echo "- Tests Run: $RUN_U | Failures: $FAIL_U | Errors: $ERR_U | Skipped: $SKIP_U"; echo;
      if [ "$RUN_I" -gt 0 ]; then
        echo "## Integration Tests"; 
        STATUS_I=$( [ "$FAIL_I" = "0" ] && [ "$ERR_I" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" );
        echo "- Status: $STATUS_I"; 
        echo "- Tests Run: $RUN_I | Failures: $FAIL_I | Errors: $ERR_I | Skipped: $SKIP_I"; 
      else
        echo "<!-- ## Integration Tests (no tests found) -->";
      fi; 
      echo;
      echo "## Performance Tests"; echo "- Status: $PERF"; echo "- Log: target/ci-artifacts/performance/benchmarks.log"; echo;
      echo "## Code Coverage"; echo "- Status: $( [ -f "$ART_COV_DIR"/index.html ] && echo "✅ Generated" || echo "⏳ Not generated" )";
      echo "- Overall Coverage: $COV"; echo "- Report: target/ci-artifacts/coverage/index.html"; echo;
      echo "## Artifacts"; echo "- Unit: target/ci-artifacts/unit"; echo "- Integration: target/ci-artifacts/integration";
      echo "- Performance: target/ci-artifacts/performance"; echo "- Coverage: target/ci-artifacts/coverage";
    } > "$REPORT"
    cat "$REPORT"

ci-report-json:
    #!/usr/bin/env bash
    JSON=target/ci-artifacts/summary.json
    ART_UNIT_DIR=target/ci-artifacts/unit
    ART_IT_DIR=target/ci-artifacts/integration
    ART_PERF_DIR=target/ci-artifacts/performance
    ART_COV_DIR=target/ci-artifacts/coverage
    RUN_U=$(grep -ho 'tests="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    FAIL_U=$(grep -ho 'failures="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    ERR_U=$(grep -ho 'errors="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    SKIP_U=$(grep -ho 'skipped="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    RUN_I=$(grep -ho 'tests="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    FAIL_I=$(grep -ho 'failures="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    ERR_I=$(grep -ho 'errors="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    SKIP_I=$(grep -ho 'skipped="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    COV=$( [ -f "$ART_COV_DIR"/jacoco.csv ] && tail -n +2 "$ART_COV_DIR"/jacoco.csv | head -1 | cut -d, -f4 | xargs printf "%.1f" || echo 0 )
    PERF=$( [ -s "$ART_PERF_DIR"/benchmarks.log ] && echo true || echo false )
    {
      echo "{";
      echo "  \"generated\": \"$(date -u +%Y-%m-%dT%H:%M:%SZ)\",";
      echo "  \"unit\": { \"run\": $RUN_U, \"failures\": $FAIL_U, \"errors\": $ERR_U, \"skipped\": $SKIP_U },";
      echo "  \"integration\": { \"run\": $RUN_I, \"failures\": $FAIL_I, \"errors\": $ERR_I, \"skipped\": $SKIP_I },";
      echo "  \"performance\": { \"generated\": $PERF, \"log\": \"target/ci-artifacts/performance/benchmarks.log\" },";
      echo "  \"coverage\": { \"overall\": $COV, \"report\": \"target/ci-artifacts/coverage/index.html\" }";
      echo "}";
    } > "$JSON"
    cat "$JSON"

ci-docs:
    #!/usr/bin/env bash
    OUT=docs/03-guides/testing/reports.md
    ART_UNIT_DIR=target/ci-artifacts/unit
    ART_IT_DIR=target/ci-artifacts/integration
    ART_PERF_DIR=target/ci-artifacts/performance
    ART_COV_DIR=target/ci-artifacts/coverage
    RUN_U=$(grep -ho 'tests="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    FAIL_U=$(grep -ho 'failures="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    ERR_U=$(grep -ho 'errors="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    SKIP_U=$(grep -ho 'skipped="[0-9]\+"' "$ART_UNIT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    RUN_I=$(grep -ho 'tests="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    FAIL_I=$(grep -ho 'failures="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    ERR_I=$(grep -ho 'errors="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    SKIP_I=$(grep -ho 'skipped="[0-9]\+"' "$ART_IT_DIR"/*.xml 2>/dev/null | grep -o '[0-9]\+' | awk '{s+=$$1} END {print s+0}')
    COV=$( [ -f "$ART_COV_DIR"/jacoco.csv ] && tail -n +2 "$ART_COV_DIR"/jacoco.csv | head -1 | cut -d, -f4 | xargs printf "%.1f%%" || echo "0%" )
    PERF=$( [ -s "$ART_PERF_DIR"/benchmarks.log ] && echo "✅ Generated" || echo "⏳ Not run" )
    {
      echo "# Test Reports and Coverage"; echo; echo "## Latest Test Results"; echo; echo "Generated: $(date)"; echo;
      echo "### Unit Tests"; STATUS_U=$( [ "$FAIL_U" = "0" ] && [ "$ERR_U" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" );
      echo "- **Status**: $STATUS_U"; echo "- **Tests Run**: $RUN_U"; echo "- **Failures**: $FAIL_U"; echo "- **Errors**: $ERR_U"; echo "- **Skipped**: $SKIP_U";
      echo "- **Reports**: [Surefire XML](../../../target/ci-artifacts/unit/)"; echo;
      if [ "$RUN_I" -gt 0 ]; then
        echo "### Integration Tests"; 
        STATUS_I=$( [ "$FAIL_I" = "0" ] && [ "$ERR_I" = "0" ] && echo "✅ PASSED" || echo "❌ FAILED" );
        echo "- **Status**: $STATUS_I"; 
        echo "- **Tests Run**: $RUN_I"; 
        echo "- **Failures**: $FAIL_I"; 
        echo "- **Errors**: $ERR_I"; 
        echo "- **Skipped**: $SKIP_I";
        echo "- **Reports**: [Failsafe XML](../../../target/ci-artifacts/integration/)"; 
      else
        echo "<!-- ### Integration Tests (no tests found) -->";
      fi;
      echo;
      echo "### Performance Tests"; echo "- **Status**: $PERF"; echo "- **Log**: [benchmarks.log](../../../target/ci-artifacts/performance/benchmarks.log)"; echo;
      echo "### Code Coverage"; echo "- **Status**: $( [ -f "$ART_COV_DIR"/index.html ] && echo "✅ Generated" || echo "⏳ Not generated" )";
      echo "- **Overall Coverage**: $COV"; echo "- **Report**: [index.html](../../../target/ci-artifacts/coverage/index.html)"; echo;
      echo "## Report Locations"; \
      echo "- **Unit Reports**: [../../../target/ci-artifacts/unit/](../../../target/ci-artifacts/unit/)"; \
      echo "- **Integration Reports**: [../../../target/ci-artifacts/integration/](../../../target/ci-artifacts/integration/)"; \
      echo "- **Coverage Reports**: [../../../target/ci-artifacts/coverage/](../../../target/ci-artifacts/coverage/)"; \
      echo "- **Performance Logs**: [../../../target/ci-artifacts/performance/](../../../target/ci-artifacts/performance/)"; \
    } > "$OUT"
    echo "Updated: $OUT"

ci-artifacts:
    #!/usr/bin/env bash
    cd target && zip -qr ci-artifacts.zip ci-artifacts || true
    echo "Package: target/ci-artifacts.zip"

ci-all:
    @just ci-clean ci-build ci-test-unit ci-test-integration ci-test-perf ci-coverage ci-report ci-report-json ci-docs ci-artifacts

ci-all-fast:
    @just ci-clean ci-build ci-test-unit ci-test-integration ci-test-perf-fast ci-coverage ci-report ci-report-json ci-docs ci-artifacts

# Show project information
info:
    @echo "📋 Project Information:"
    @echo "  Name: $(mvn help:evaluate -Dexpression=project.name -q -DforceStdout)"
    @echo "  Version: $(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)"
    @echo "  Java Version: $(java -version 2>&1 | head -n 1)"
    @echo "  Maven Version: $(mvn -v | head -n 1)"
