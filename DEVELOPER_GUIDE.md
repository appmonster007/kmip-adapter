# KMIP Adapter Developer Guide

A comprehensive guide for extending and contributing to the KMIP Adapter library. This document covers core concepts, architecture, and development workflows.

## Table of Contents

- [Overview](#overview)
- [Development Setup](#development-setup)
- [KMIP Type Architecture](#kmip-type-architecture)
- [Creating New KMIP Objects](#creating-new-kmip-objects)
- [Contributors Guide](#contributors-guide)
- [Usage Patterns](#usage-patterns)

## Overview

The KMIP Adapter follows a structured approach to building KMIP-compliant data types. The current test suite includes 500+ tests with high coverage across core components. The library implements:

- **Type Safety**: Compile-time validation through interfaces and `@NonNull` annotations
- **Version Compatibility**: Automatic validation against KMIP specifications
- **Extensibility**: Runtime registration of custom values with proper cleanup
- **Thread Safety**: Concurrent access through ThreadLocal contexts
- **Serialization**: Multi-format support (TTLV, JSON, XML)
- **Comprehensive Testing**: Extensive tests with strong coverage
- **Test Isolation**: Clean test state management
- **Parameterized Testing**: Extensive use of JUnit 5 parameterized tests

## Development Setup

### Prerequisites

```bash
# Required tools
Java 21+
Maven 3.6+
Git

# Recommended IDE plugins
- Lombok plugin
- Checkstyle plugin
- JaCoCo coverage plugin
```

### Project Structure

```
src/main/java/org/purpleBean/kmip/
├── KmipDataType.java           # Base interface
├── KmipEnumeration.java        # Enumeration interface
├── KmipAttribute.java          # Attribute interface
├── KmipStructure.java          # Structure interface
├── EncodingType.java           # TTLV encoding types
├── KmipTag.java               # Tag registry system
├── KmipSpec.java              # Version management
├── codec/                     # Serialization codecs
├── common/                    # Standard implementations
│   ├── enumeration/          # KMIP enumerations
│   ├── structure/            # KMIP structures
│   └── ActivationDateAttribute.java
└── v1_2/                     # Version-specific types
```

## KMIP Type Architecture

### Core Interfaces

All KMIP types implement the base `KmipDataType` interface:

```java
public interface KmipDataType {
    KmipTag getKmipTag();
    EncodingType getEncodingType();
    boolean isSupportedFor(KmipSpec spec);
}
```

### Specialized Interfaces

- **KmipEnumeration**: For extensible enumerations
- **KmipAttribute**: For object attributes with state-based modifiability
- **KmipStructure**: For complex composite structures

## Creating New KMIP Objects

For detailed instructions on creating new KMIP objects, refer to the specialized guides:

### 📚 **Comprehensive Creation Guides**

- **[Quick Start for New Types](docs/03-guides/quick-start-new-types.md)** - Main overview with quick start checklist and file organization
- **[Boilerplate: Enumerations](docs/03-guides/boilerplate-enum.md)** - Templates for extensible enumerations
- **[Boilerplate: Attributes](docs/03-guides/boilerplate-attribute.md)** - Templates for attributes
- **[Boilerplate: Structures](docs/03-guides/boilerplate-structure.md)** - Templates for structures
- **[Serialization Guide](docs/02-architecture/serialization.md)** - JSON, XML, and TTLV serializer/deserializer overview
- **[Testing Guide](docs/03-guides/testing.md)** - Comprehensive test templates with validation examples
- **[Context Management](docs/03-guides/context-management.md)** - Version/context handling patterns

### 🚀 **Quick Start**

1. **Choose your KMIP object type**: Enumeration, Attribute, or Structure
2. **Follow the step-by-step guide** in the appropriate specialized guide
3. **Use the boilerplate templates** to create your main class
4. **Implement serializers/deserializers** for all three formats (JSON, XML, TTLV)
5. **Register in codec modules** and create comprehensive tests
6. **Add validation patterns** as specified in the validation guide

### 📋 **Implementation Checklist**

- [ ] Create main KMIP object class with proper validation
- [ ] Add KMIP tag to `KmipTag.Standard` enum
- [ ] Implement JSON serializer/deserializer
- [ ] Implement XML serializer/deserializer  
- [ ] Implement TTLV serializer/deserializer
- [ ] Register all serializers in codec modules
- [ ] Create comprehensive unit tests
- [ ] Add serialization round-trip tests
- [ ] Update `KmipTestDataFactory` with test data methods
- [ ] Add integration tests

For detailed serialization and testing information, refer to the specialized guides linked above.

## Contributors Guide

### Getting Started

1. **Fork and Clone**
   ```bash
   git clone https://github.com/your-username/kmip-adapter.git
   cd kmip-adapter
   ```

2. **Set Up Development Environment**
   ```bash
   # Install dependencies
   mvn clean compile
   
   # Run tests
   mvn test
   
   # Check code quality
   mvn checkstyle:check
   ```

3. **Create Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

### Development Workflow

1. **Follow Coding Standards**
   - Use Google Java Style Guide
   - Add comprehensive JavaDoc
   - Include `@NonNull` annotations
   - Follow existing naming conventions
   - Write self-documenting test names with `@DisplayName`
   - Use parameterized tests for testing multiple inputs
   - Ensure proper test cleanup with `@AfterEach`
   - Use descriptive assertion messages

2. **Testing Guidelines**

   1. **Test Structure**
      - Follow the Arrange-Act-Assert pattern
      - Use descriptive test method names with `@DisplayName`
      - Test both happy path and error conditions
      - Include edge cases and boundary conditions
      - Use nested test classes for better organization
      - Group related tests using `@Nested`

   2. **Test Coverage**
      - Aim for 100% code coverage
      - Test all public methods and constructors
      - Include negative test cases
      - Test serialization/deserialization
      - Test thread safety for concurrent access
      - Test registry cleanup and isolation

   3. **Test Patterns**
      - Use parameterized tests for testing multiple inputs
      - Implement test cleanup with `@AfterEach`
      - Use `assertThatThrownBy` for exception testing
      - Verify error messages in exception tests
      - Test equals/hashCode contracts
      - Test toString() for debugging

   4. **Test Data**
      - Use test data factories for complex objects
      - Generate unique test data to avoid collisions
      - Clean up test data after tests
      - Use random values where order doesn't matter

3. **Implementation Guidelines**
   - Use Lombok annotations consistently
   - Implement all required interfaces
   - Add version compatibility checks
   - Include proper error handling

4. **Documentation**
   - Update README.md for API changes
   - Add JavaDoc for all public methods
   - Include usage examples
   - Update this developer guide if needed
   - Note: Code formatting via Spotless is optional; the plugin is disabled by default in `pom.xml`. Enable it first if you plan to use `mvn spotless:apply` or equivalent task runner commands.

### Code Review Process

1. **Self Review**
   - Run full test suite: `mvn clean test`
   - Check code coverage: `mvn jacoco:report`
   - Validate style: `mvn checkstyle:check`
   - Test serialization formats

2. **Submit Pull Request**
   - Clear description of changes
   - Link to related issues
   - Include test results
   - Add breaking change notes

3. **Review Criteria**
   - Code follows established patterns
   - Tests provide adequate coverage
   - Documentation is complete
   - No breaking changes without justification

### Release Process

1. **Version Bumping**
   ```xml
   <!-- Update pom.xml version -->
   <version>1.1.0-SNAPSHOT</version>
   ```

2. **Changelog Updates**
   - Document new features
   - List breaking changes
   - Include migration guide

3. **Release Validation**
   - Full test suite passes
   - Integration tests pass
   - Documentation builds correctly
   - Examples work as expected

## Usage Patterns

### Common Patterns

#### 1. Context Management Pattern
```java
public void processKmipData(KmipSpec targetVersion) {
    try {
        KmipContext.setSpec(targetVersion);
        // Process KMIP objects
    } finally {
        KmipContext.clear();
    }
}
```

#### 2. Builder Validation Pattern
```java
public static class YourStructureBuilder {
    public YourStructure build() {
        validateRequiredFields();
        validateVersionCompatibility();
        return new YourStructure(/* fields */);
    }
}
```

#### 3. Registry Extension Pattern
```java
// Register custom values at application startup
@PostConstruct
public void registerCustomValues() {
    State.register(-1000001, "CustomState", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
}
```

#### 4. Serialization Pattern
```java
public String serializeToJson(KmipDataType object) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return mapper.writeValueAsString(object);
}
```

### Anti-Patterns to Avoid

❌ **Don't create KMIP objects without setting context**
```java
// BAD - context not set
State state = new State(State.Standard.ACTIVE); // May fail
```

✅ **Always set context first**
```java
// GOOD - context set before creation
KmipContext.setSpec(KmipSpec.V1_2);
State state = new State(State.Standard.ACTIVE);
```

❌ **Don't ignore version compatibility**
```java
// BAD - no version checking
public void processAnyVersion(KmipDataType data) {
    // Process without validation
}
```

✅ **Always validate version compatibility**
```java
// GOOD - validate before processing
public void processData(KmipDataType data, KmipSpec targetSpec) {
    if (!data.isSupportedFor(targetSpec)) {
        throw new IllegalArgumentException("Unsupported version");
    }
    // Process with confidence
}
```

### Performance Optimization

1. **Reuse ObjectMapper instances**
2. **Set context once per thread/request**
3. **Use builder pattern for complex objects**
4. **Cache registry lookups when possible**
5. **Prefer immutable objects for thread safety**

---

## Support and Resources

- **Main Documentation**: [README.md](README.md)
- **API Reference**: Generated JavaDoc
- **Issues**: [GitHub Issues](https://github.com/purplebean/kmip-adapter/issues)
- **Discussions**: [GitHub Discussions](https://github.com/purplebean/kmip-adapter/discussions)

---

**Happy coding! 🚀**
