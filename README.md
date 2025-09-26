# KMIP Adapter

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License: LGPL v3](https://img.shields.io/badge/License-LGPL%20v3-blue.svg)](https://www.gnu.org/licenses/lgpl-3.0)
[![Tests](https://img.shields.io/badge/Tests-500%2B%20passing-brightgreen.svg)](https://github.com/purplebean/kmip-adapter/actions)
[![Documentation](https://img.shields.io/badge/Docs-latest-brightgreen.svg)](docs/)

A comprehensive Java library for building and managing KMIP (Key Management Interoperability Protocol) data types with support for TTLV serialization, JSON/XML mapping, and extensible type systems.

## 📚 Documentation

### Docs Index

- Testing & Coverage: [Testing Guide](docs/03-guides/testing.md)
- Boilerplates:
  - [Structure Boilerplate](docs/03-guides/development/boilerplate-structure.md)
  - [Attribute Boilerplate](docs/03-guides/development/boilerplate-attribute.md)
  - [Enumeration Boilerplate](docs/03-guides/development/boilerplate-enum.md)
  - [Tests Index (Per-Class Mapping)](docs/03-guides/tests-index.md)
  - [Quick Start for New Types](docs/03-guides/quick-start-new-types.md)
- Performance:
  - [Performance Testing Guide](docs/04-performance/performance-testing-guide.md)
  - [Benchmarking Guide](docs/03-guides/benchmarking.md)

### Getting Started
- [Installation](docs/01-getting-started/installation.md) - How to add the library to your project
- [Build System](docs/01-getting-started/build-system.md) - Using Makefile, Taskfile, and justfile
- [Configuration](docs/01-getting-started/configuration.md) - Basic setup and configuration

### Architecture
- [Core Concepts](docs/02-architecture/core-concepts.md) - Key architectural concepts and design decisions
- [Type System](docs/02-architecture/type-system.md) - Understanding KMIP's type system
- [Serialization](docs/02-architecture/serialization.md) - Serialization formats and protocols

### Guides
- [Development Guide](docs/03-guides/development/boilerplate.md) - Overview and checklists; see linked FooDemo boilerplates
  - [Testing Guide](docs/03-guides/testing.md) - Testing patterns and best practices
  - [Quick Start](docs/03-guides/quick-start-new-types.md) - Fast path to add new types

- [Implementation Reference](docs/05-guides/implementation/) - Comprehensive implementation details
  - [Core Concepts](docs/05-guides/implementation/core/README.md) - Base interfaces and patterns
  - [Enumerations](docs/05-guides/implementation/enumerations/README.md) - Type-safe enumerations
  - [Attributes](docs/05-guides/implementation/attributes/README.md) - Attribute implementation
  - [Structures](docs/05-guides/implementation/structures/README.md) - Complex data structures
  - [Serialization](docs/05-guides/implementation/serialization/README.md) - Advanced serialization
  - [Testing](docs/05-guides/implementation/testing/README.md) - Advanced testing patterns
  - [Validation](docs/05-guides/implementation/validation/README.md) - Advanced validation

### API Reference
- [Enumerations](docs/04-api/enumerations.md) - Available enumeration types and their usage
- [Attributes](docs/04-api/attributes.md) - KMIP attribute types and validation rules
- [Structures](docs/04-api/structures.md) - Complex KMIP data structures and composition

### Contributing
- [Code Style](docs/05-contributing/code-style.md) - Coding standards and conventions
- [Pull Requests](docs/05-contributing/pull-requests.md) - How to contribute code
- [Release Process](docs/05-contributing/release-process.md) - Versioning and releases

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Maven

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.purpleBean</groupId>
    <artifactId>kmip-adapter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Basic Usage

```java
// Minimal JSON round-trip with a built-in type
import com.fasterxml.jackson.databind.ObjectMapper;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.KmipJsonModule;

KmipContext.setSpec(KmipSpec.V1_2);
try {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new KmipJsonModule());

    ProtocolVersion original = ProtocolVersion.of(1, 2);
    String json = mapper.writeValueAsString(original);
    ProtocolVersion restored = mapper.readValue(json, ProtocolVersion.class);
} finally {
    KmipContext.clear();
}
```

## 🔍 Features

### Core Features
- **Type-safe KMIP data structures** with compile-time validation
- **Extensible enumeration system** supporting custom values and version compatibility
- **Multi-format serialization** (TTLV, JSON, XML) with Jackson integration
- **Thread-safe codec contexts** for version-specific processing

### Advanced Features
- **Custom type registration** for extending the type system
- **Validation framework** for input verification
- **Comprehensive test suite** with 500+ tests
- **Performance optimized** for high-throughput scenarios

## Detailed Examples

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Installation

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>org.purpleBean</groupId>
    <artifactId>kmip-adapter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Basic Usage

#### Creating KMIP Data Types

```java
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.KmipSpec;

// Set KMIP specification context
KmipContext.setSpec(KmipSpec.V1_2);

// Create basic types
State activeState = new State(State.Standard.ACTIVE);
ActivationDate activationDate = ActivationDate.builder()
        .dateTime(OffsetDateTime.now())
        .build();

// Create complex structures
SampleStructure structure = SampleStructure.builder()
        .activationDate(activationDate)
        .state(activeState)
        .build();
```

#### Custom Enumeration Registration

```java
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.KmipSpec;

// Register custom state values
State.Value customState = State.register(
    -1000001, 
    "CustomPendingState", 
    Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)
);

// Use custom state
State myState = new State(customState);
```

#### JSON Serialization

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());

// Serialize to JSON
String json = mapper.writeValueAsString(structure);

// Deserialize from JSON
SampleStructure restored = mapper.readValue(json, SampleStructure.class);
```

#### XML Serialization

```java
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

XmlMapper xmlMapper = new XmlMapper();
xmlMapper.registerModule(new JavaTimeModule());

// Serialize to XML
String xml = xmlMapper.writeValueAsString(structure);

// Deserialize from XML
SampleStructure restored = xmlMapper.readValue(xml, SampleStructure.class);
```

## Architecture

### Core Components

#### KmipTag System
- **Standard Tags**: All KMIP 1.2 specification tags pre-registered
- **Extension Support**: Custom tag registration with validation
- **Byte Operations**: Efficient tag-to-byte conversion for TTLV encoding
- **Registry Management**: Thread-safe tag lookup and management

#### Encoding Types
- **TTLV Types**: Complete KMIP encoding type enumeration
- **Type Safety**: Compile-time validation of encoding compatibility
- **Extensibility**: Support for custom encoding types

#### Version Management
- **KmipSpec Enum**: Version-specific feature support
- **Compatibility Checking**: Automatic validation of type/version combinations
- **Context Management**: Thread-local specification contexts

### Data Structures

#### Base Interfaces
```java
public interface KmipDataType {
    KmipTag getKmipTag();
    EncodingType getEncodingType();
    boolean isSupportedFor(KmipSpec spec);
}

public interface KmipStructure extends KmipDataType {
    // Marker interface for complex structures
}
```

#### Common Types
- **ActivationDateAttribute**: Date/time attributes with timezone support
- **State**: Extensible enumeration for object states
- **ProtocolVersion**: KMIP protocol version management
- **SampleStructure**: Example composite structure implementation

### Codec System

#### Context Management

The `KmipContext` is a critical component that manages KMIP specification versions in a thread-safe manner. It uses ThreadLocal storage to ensure each thread has its own isolated context.

**Why Use KmipContext:**
- **Version Validation**: Ensures KMIP objects are created with compatible specification versions
- **Thread Safety**: Prevents version conflicts in multi-threaded applications
- **Automatic Validation**: KMIP types automatically validate against the current context
- **Error Prevention**: Catches unsupported value/version combinations at creation time

**When to Use KmipContext:**

1. **Before Creating KMIP Objects** - Always set the context before instantiating KMIP types:
   ```java
   // Set context first
   KmipContext.setSpec(KmipSpec.V1_2);
   
   // Then create objects - they will validate against V1_2
   State activeState = new State(State.Standard.ACTIVE);
   ```

2. **In Multi-threaded Applications** - Each thread should set its own context:
   ```java
   // Thread 1
   CompletableFuture.runAsync(() -> {
       KmipContext.setSpec(KmipSpec.V1_2);
       // Process V1_2 objects safely
   });
   
   // Thread 2 - independent context
   CompletableFuture.runAsync(() -> {
       KmipContext.setSpec(KmipSpec.V1_2);
       // Process with isolated context
   });
   ```

3. **When Processing Different KMIP Versions** - Switch contexts for different specification versions:
   ```java
   // Process legacy data
   KmipContext.setSpec(KmipSpec.UnknownVersion);
   // ... process older format
   
   // Switch to current version
   KmipContext.setSpec(KmipSpec.V1_2);
   // ... process current format
   ```

4. **In Web Applications** - Set context per request:
   ```java
   @RestController
   public class KmipController {
       
       @PostMapping("/kmip/v1.2/objects")
       public ResponseEntity<?> createObject(@RequestBody ObjectRequest request) {
           // Set context for this request
           KmipContext.setSpec(KmipSpec.V1_2);
           
           try {
               // Create and process KMIP objects
               return ResponseEntity.ok(processRequest(request));
           } finally {
               // Clean up context
               KmipContext.clear();
           }
       }
   }
   ```

**Context Management Best Practices:**
```java
// Always clear context when done (optional but recommended)
try {
    KmipContext.setSpec(KmipSpec.V1_2);
    // ... your KMIP operations
} finally {
    KmipContext.clear(); // Resets to UnknownVersion
}

// Check current context
KmipSpec current = KmipContext.getSpec();
```

**What Happens Without Context:**
- Objects default to `UnknownVersion` which may not support all features
- Version validation may fail for newer KMIP types
- Serialization behavior may be inconsistent

#### Serialization Pipeline
1. **Validation**: Type/version compatibility checking
2. **Transformation**: Object to intermediate representation
3. **Encoding**: Format-specific serialization (TTLV/JSON/XML)
4. **Output**: Serialized byte array or string

## Testing

The library includes a comprehensive test suite with 540+ tests covering:

- **Unit Tests**: Individual component validation
- **Integration Tests**: End-to-end workflow testing
- **Serialization Tests**: Multi-format round-trip validation
- **Parallel Tests**: Thread safety and concurrent access
- **Edge Cases**: Boundary conditions and error scenarios

### Running Tests

```bash
# Run unit tests (default)
mvn test

# Include integration tests (tagged with @Tag("integration"))
mvn -Pwith-integration test

# Run with coverage report (HTML report under target/site/jacoco/index.html)
mvn clean test

# Run specific test class
mvn test -Dtest=ProtocolVersionTest

# Run performance benchmarks (JMH) without affecting unit tests
mvn -Pperf verify
# Optional: pass JMH args
mvn -Pperf -Dbench.args="-wi 3 -i 5 -f 1 -rf json -rff target/jmh.json" verify
```

### Coverage (quick)

- Generate coverage and open HTML report:

```bash
mvn clean test
open target/site/jacoco/index.html   # macOS; use xdg-open on Linux
```

### Test Coverage

Coverage reports are generated at:
- target/site/jacoco/index.html

Optional strict coverage gate (fail build below thresholds):

```bash
mvn -Pcoverage-strict verify
```

Current test coverage includes:
- **Core Types**: 100% coverage of all KMIP data types
- **Serialization**: Complete JSON/XML round-trip testing
- **Registry Operations**: Full enumeration extension testing
- **Thread Safety**: Concurrent codec context validation
- **Error Handling**: Comprehensive exception scenario testing

Note: Per-class codec tests are organized to mirror runtime packages for easier discoverability, for example:

- JSON: `src/test/java/org/purpleBean/kmip/codec/json/common/...`
- XML: `src/test/java/org/purpleBean/kmip/codec/xml/common/...`
- TTLV: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/...`

## Configuration

### Maven Dependencies

Core dependencies automatically included:
- **Jackson Databind** (2.20.0): JSON/XML processing
- **Jackson JSR310** (2.20.0): Java Time API support
- **Lombok** (1.18.40): Code generation and null safety
- **Spring Boot** (3.5.5): Optional dependency injection support

### Optional Configuration

#### Spring Integration
```java
@Configuration
public class KmipConfig {
    
    @Bean
    public ObjectMapper kmipObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
```

#### Custom Serialization
```java
// Custom Jackson module for KMIP types
public class KmipModule extends SimpleModule {
    public KmipModule() {
        addSerializer(State.class, new StateSerializer());
        addDeserializer(State.class, new StateDeserializer());
    }
}
```

## Performance Considerations

### Memory Usage
- **Immutable Objects**: All KMIP types are immutable for thread safety
- **Registry Caching**: Enumeration values cached for fast lookup
- **Lazy Loading**: Complex structures built on-demand

### Thread Safety
- **Context Isolation**: Thread-local codec contexts prevent interference
- **Concurrent Registry**: Thread-safe enumeration registration
- **Immutable State**: No shared mutable state between threads

### Optimization Tips
- Reuse `ObjectMapper` instances for better performance
- Set codec context once per thread rather than per operation
- Use builder patterns for complex object construction

## Contributing

### Development Setup

1. **Clone Repository**
   ```bash
   git clone https://github.com/purplebean/kmip-adapter.git
   cd kmip-adapter
   ```

2. **Build Project**
   ```bash
   mvn clean compile
   ```

3. **Run Tests**
   ```bash
   mvn test
   ```

### Code Quality

The project enforces code quality through:
- **Checkstyle**: Google Java Style compliance
- **JaCoCo**: Code coverage reporting
- **JUnit 5**: Modern testing framework
- **AssertJ**: Fluent assertion library

### Contribution Guidelines

- Follow Google Java Style Guide
- Maintain test coverage above 90%
- Include comprehensive JavaDoc documentation
- Add integration tests for new features
- Update README for API changes

## License

This project is licensed under the GNU Lesser General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## Support

- **Documentation**: [API Documentation](docs/)
- **Issues**: [GitHub Issues](https://github.com/purplebean/kmip-adapter/issues)
- **Discussions**: [GitHub Discussions](https://github.com/purplebean/kmip-adapter/discussions)

## Changelog

### Version 1.0-SNAPSHOT
- Initial release with KMIP 1.2 support
- Complete TTLV, JSON, and XML serialization
- Extensible enumeration system
- Thread-safe codec contexts
- Comprehensive test suite (540+ tests)

---

**Built with ❤️ by the PurpleBean Development Team**
