# Development Workflow Guide

This guide provides a step-by-step process for building, testing, and documenting changes to the KMIP Adapter project.

## Prerequisites

- Java 21 or later
- Maven 3.6 or later
- Git
- (Optional) Docker for containerized testing

## 1. Setting Up the Development Environment

### Clone the Repository
```bash
git clone https://github.com/your-org/kmip-adapter.git
cd kmip-adapter
```

### Install Dependencies
```bash
mvn clean install -DskipTests
```

## 2. Making Changes

### Code Style and Formatting
- Follow the [Code Style Guide](./code-style.md)
- Run code formatting before committing:
  ```bash
  // if spotless plugin installed
  mvn spotless:apply 
  ```

### Implementing Features
1. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
2. Make your changes following the implementation guides
3. Add unit tests for new functionality
4. Update relevant documentation

## 3. Writing Tests

### Unit Tests
1. Place test classes in `src/test/java` mirroring the main package structure
2. Follow the naming convention: `{ClassName}Test.java`
3. Example test structure:
   ```java
   @Test
   @DisplayName("Should perform expected behavior")
   void shouldPerformExpectedBehavior() {
       // Given
       var input = ...;
       
       // When
       var result = component.operation(input);
       
       // Then
       assertThat(result).isNotNull();
       // More assertions
   }
   ```

### Integration Tests
1. Place in `src/test/java` under `*.integration` package
2. Use `@SpringBootTest` for Spring integration tests
3. Use `@Testcontainers` for containerized testing

## 4. Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=com.example.YourTestClass
```

### Run Single Test Method
```bash
mvn test -Dtest=com.example.YourTestClass#testMethodName
```

## 5. Performance Testing

### Running JMH Benchmarks
```bash
mvn clean test-compile exec:exec -Dexec.executable="java" -Dexec.args="-cp target/test-classes:target/classes:$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q) org.openjdk.jmh.Main -f 1 -wi 3 -i 5"
```

Or use the JMH Maven plugin:
```bash
mvn clean test-compile exec:exec@jmh-benchmark
```

Add this to your `pom.xml` in the build/plugins section:
```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.5.0</version>
    <executions>
        <execution>
            <id>jmh-benchmark</id>
            <phase>test</phase>
            <goals>
                <goal>exec</goal>
            </goals>
            <configuration>
                <executable>java</executable>
                <arguments>
                    <argument>-cp</argument>
                    <classpath>
                        <mainClass>org.openjdk.jmh.Main</mainClass>
                    </classpath>
                    <argument>org.openjdk.jmh.Main</argument>
                    <argument>-f</argument>
                    <argument>1</argument>
                    <argument>-wi</argument>
                    <argument>3</argument>
                    <argument>-i</argument>
                    <argument>5</argument>
                </arguments>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Performance Test Parameters
- `-f`: Number of forks
- `-wi`: Warmup iterations
- `-i`: Measurement iterations
- `-r`: Duration of each iteration

## 6. Collecting Metrics

### Test Coverage Report
```bash
mvn clean test jacoco:report
```
View the report at: `target/site/jacoco/index.html`

### Performance Metrics
Performance reports are generated in:
- `target/jmh-results.json` (JSON format)
- `target/jmh-report` (HTML report)

## 7. Building the Project

### Create a Production Build
```bash
mvn clean package -DskipTests
```

The build artifacts will be available in the `target/` directory.

## 8. Updating Documentation

1. Update the relevant `.md` files in the `docs/` directory
2. For API documentation:
   ```bash
   mvn javadoc:javadoc
   ```
3. Documentation will be generated in `target/site/apidocs/`

## 9. Creating a Pull Request

1. Commit your changes:
   ```bash
   git add .
   git commit -m "feat: add new feature"
   ```
2. Push your branch:
   ```bash
   git push origin feature/your-feature-name
   ```
3. Create a Pull Request with the following sections:
   - Description of changes
   - Related issues
   - Testing performed
   - Performance impact
   - Documentation updates

## 10. Code Review and Merge

1. Address any code review feedback
2. Update tests if needed
3. Ensure all CI/CD checks pass
4. Squash and merge when approved

## Troubleshooting

### Common Issues
1. **Dependency Issues**:
   ```bash
   mvn clean install -U
   ```

2. **Test Failures**:
   - Check the test output in `target/surefire-reports/`
   - Run with debug output:
     ```bash
     mvn test -Dmaven.surefire.debug -Dtest=YourTestClass
     ```

3. **Build Failures**:
   - Check Java version compatibility
   - Verify Maven settings
   - Check for dependency conflicts:
     ```bash
     mvn dependency:tree -Dverbose
     ```

For additional help, refer to the [Development Guide](./development.md) or open an issue in the project's issue tracker.
