# Quick Start for New Types

A concise checklist to add a new KMIP type and per-class tests end-to-end, with links to minimal boilerplates and CI snippets.

## Links

- **Boilerplates**:
  - Structure: `docs/03-guides/development/boilerplate-structure.md`
  - Data Type: `docs/03-guides/development/boilerplate-attribute.md`
  - Enumeration: `docs/03-guides/development/boilerplate-enum.md`
- **Testing**:
  - Tests Index: `docs/03-guides/tests-index.md`
  - Testing Guide: `docs/03-guides/testing.md`
  - Benchmarking: `docs/04-performance/performance-testing-guide.md`
- **API Reference**: `docs/04-api/`

## 6-Step Checklist

1. **Define the Type**
   - **Structure**: See `FooStructure` in `development/boilerplate-structure.md`
   - **Data Type**: See `FooDataType` in `development/boilerplate-attribute.md`
   - **Enumeration**: See `FooEnum` in `development/boilerplate-enum.md`
   - **Tag Registration**: Add to `KmipTag.Standard` if needed
   - **Version Support**: Specify supported KMIP versions in `supportedVersions`
   - **Validation**: Implement `validate(KmipSpec)` for spec-specific rules).

2. **Implement Factory Methods**
   - Add static factory methods in your type class:
     ```java
     public static FooDataType of(String stringValue, Integer intValue) {
         return builder()
             .stringValue(stringValue)
             .intValue(intValue)
             .build();
     }
     ```
   - For enums, add `fromValue` and `fromName` methods
   - For structures, implement a builder with validation

3. **Register Codecs**
   Add your serializers/deserializers to the appropriate service files:
   
   - **JSON**:
     - `META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer`
     - `META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer`
   
   - **XML**:
     - `META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer`
     - `META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer`
   
   - **TTLV**:
     - `META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer`
     - `META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer`

4. **Add Unit Tests**
   - **Core Tests**: Test validation, equals/hashCode, and builder
     ```java
     @Test
     void testValidation() {
         FooDataType data = FooDataType.builder()
             .stringValue("test")
             .intValue(42)
             .build();
         assertDoesNotThrow(() -> data.validate(KmipSpec.V1_4));
     }
     ```
   
   - **Serialization Tests**: Test JSON/XML/TTLV round-trip
     ```java
     @Test
     void testJsonRoundTrip() throws Exception {
         FooDataType original = FooDataType.of("test", 42);
         String json = KmipContext.getJsonMapper().toJson(original);
         FooDataType deserialized = KmipContext.getJsonMapper().fromJson(json, FooDataType.class);
         assertEquals(original, deserialized);
     }
     ```

5. **Add Integration Tests**
   - Test with different KMIP versions
   - Test edge cases and invalid inputs
   - Verify error messages and validation rules
   - Test with real KMIP server if applicable

6. **Performance Testing**
   - Add JMH benchmarks for critical paths
   - Test serialization/deserialization performance
   - Include memory usage metrics
   - Run with: `mvn -Pperf test`

## Running Tests

### Local Development
```bash
# Run unit tests
mvn test

# Run with integration tests
mvn -Pwith-integration test

# Generate coverage report (HTML)
mvn clean test
# View at: target/site/jacoco/index.html

# Strict coverage check
mvn -Pcoverage-strict verify
```

### Performance Testing
```bash
# Standard benchmark
mvn -Pperf verify

# Quick benchmark (fewer iterations)
mvn -Pperf-fast verify

# Custom benchmark
mvn -Pperf -Dbench.args="-wi 3 -i 5 -f 1 -rf json -rff target/jmh.json" verify
```

## CI/CD Integration

### GitHub Actions Workflow Example

```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
  workflow_dispatch:

jobs:
  build-and-test:
    name: Build and Test
    runs-on: ubuntu-latest
    
    steps:
    - name: Checkout code
      uses: actions/checkout@v4
      
    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with:
        distribution: 'temurin'
        java-version: '21'
        
    - name: Cache Maven packages
      uses: actions/cache@v3
      with:
        path: ~/.m2/repository
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
        restore-keys: ${{ runner.os }}-m2-
    
    - name: Build with Maven
      run: mvn -B clean verify
      
    - name: Upload test results
      if: always()
      uses: actions/upload-artifact@v4
      with:
        name: test-results
        path: '**/target/surefire-reports/*.xml'
        if-no-files-found: ignore
        
    - name: Upload coverage report
      if: success()
      uses: actions/upload-artifact@v4
      with:
        name: coverage-report
        path: '**/target/site/jacoco/**'
        if-no-files-found: ignore

  performance:
    name: Performance Tests
    needs: build-and-test
    if: github.event_name == 'workflow_dispatch' || github.ref == 'refs/heads/main'
    runs-on: ubuntu-latest
    
    steps:
    - name: Checkout code
      uses: actions/checkout@v4
      
    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with:
        distribution: 'temurin'
        java-version: '21'
        
    - name: Run performance tests
      run: mvn -B -Pperf verify
      
    - name: Upload benchmark results
      if: always()
      uses: actions/upload-artifact@v4
      with:
        name: benchmark-results
        path: '**/target/jmh-results/*.json'
        if-no-files-found: ignore
```

### Key Features

1. **Build and Test**
   - Runs on all pushes and pull requests
   - Caches Maven dependencies for faster builds
   - Uploads test results and coverage reports
   
2. **Performance Testing**
   - Runs on demand or main branch updates
   - Generates benchmark results
   - Stores results as build artifacts

3. **Best Practices**
   - Uses latest stable versions of actions
   - Includes proper Java version management
   - Handles artifacts and test results
   - Includes caching for faster builds
