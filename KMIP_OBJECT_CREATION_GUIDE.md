# KMIP Object Creation Guide

A comprehensive step-by-step guide for creating new KMIP objects with all supporting classes, validations, and tests. This guide reflects the current state of the codebase with 551 passing tests and 100% code coverage.

## Table of Contents

- [Overview](#overview)
- [Quick Start Checklist](#quick-start-checklist)
- [Detailed Guides](#detailed-guides)
- [File Structure](#file-structure)
- [Validation Strategy](#validation-strategy)
- [Testing Strategy](#testing-strategy)

## Overview

When creating a new KMIP object supported by the KMIP specification, you need to create several supporting components. Before starting, please review these core guidelines:

### Core Guidelines

1. **Inheritance Rules**:
   - All new KMIP objects must extend either `KmipEnumeration` (for enumerations) or some combination of `KmipStructure` and `KmipAttribute` (for complex types).
   - Maintain strict type hierarchy to ensure consistency across the codebase.

2. **Version Support Requirements**:
   - All new enum values or extensions must support `KmipSpec.UnknownVersion` by default.
   - All new objects or extensions must support `KmipSpec.UnknownVersion` by default.
   - Always implement `isSupportedFor(KmipSpec spec)` to handle version compatibility.

3. **Registration**:
   - Register all new enum values with proper version support information.
   - Ensure proper initialization order when registering new values.


### Required Components
1. **Main KMIP Object** - The core data type (Enumeration, Attribute, or Structure)
2. **Serializers/Deserializers** - JSON, XML, and TTLV format support
3. **Module Registration** - Register serializers in codec modules
4. **Comprehensive Tests** - Unit, integration, and serialization tests
5. **Validation Logic** - Input validation and KMIP spec compliance

### File Organization
```
src/main/java/org/purpleBean/kmip/
├── common/
│   ├── enumeration/YourEnumeration.java
│   ├── structure/YourStructure.java
│   └── YourAttribute.java
├── codec/
│   ├── json/
│   │   ├── serializer/kmip/common/YourTypeJsonSerializer.java
│   │   └── deserializer/kmip/common/YourTypeJsonDeserializer.java
│   ├── xml/
│   │   ├── serializer/kmip/common/YourTypeXmlSerializer.java
│   │   └── deserializer/kmip/common/YourTypeXmlDeserializer.java
│   └── ttlv/
│       ├── serializer/kmip/common/YourTypeTtlvSerializer.java
│       └── deserializer/kmip/common/YourTypeTtlvDeserializer.java
└── test/java/org/purpleBean/kmip/common/YourTypeTest.java
```

## Test Coverage Status

- **Total Tests**: 551 passing tests
- **Code Coverage**: 100% of classes analyzed
- **Test Categories**:
  - Unit Tests
  - Integration Tests
  - Serialization Tests (JSON/XML/TTLV)
  - Validation Tests
  - Edge Case Tests

## Quick Start Checklist

### Phase 1: Core Object Creation
- [ ] Create main KMIP object class
- [ ] Add KMIP tag to KmipTag.Standard enum
- [ ] Implement validation logic
- [ ] Ensure all public methods have proper null checks
- [ ] Add @NonNull annotations for required fields

### Phase 2: Serialization Support
- [ ] Create JSON serializer/deserializer
- [ ] Create XML serializer/deserializer  
- [ ] Create TTLV serializer/deserializer
- [ ] Register all serializers in modules

### Phase 3: Testing
- [ ] Create unit tests for core functionality (100% coverage required)
- [ ] Add serialization tests for all formats (JSON/XML/TTLV)
- [ ] Include edge case tests and null checks
- [ ] Add integration tests for multi-threaded scenarios
- [ ] Verify all tests pass with `mvn clean test`
- [ ] Add validation tests
- [ ] Add edge case and error handling tests

### Phase 4: Integration
- [ ] Update KmipTestDataFactory
- [ ] Add to integration test suites
- [ ] Update documentation

## Detailed Guides

This guide is split into specialized files for detailed implementation:

- **[KMIP_IMPLEMENTATION_GUIDE.md](KMIP_IMPLEMENTATION_GUIDE.md)** - Main object class templates
- **[KMIP_SERIALIZATION_GUIDE.md](./KMIP_SERIALIZATION_GUIDE.md)** - Serializer/deserializer templates
- **[KMIP_TESTING_GUIDE.md](./KMIP_TESTING_GUIDE.md)** - Comprehensive test templates
- **[KMIP_VALIDATION_PATTERNS.md](./KMIP_VALIDATION_PATTERNS.md)** - Validation implementation patterns

## File Structure

### Main Object Location
- **Enumerations**: `src/main/java/org/purpleBean/kmip/common/enumeration/`
- **Attributes**: `src/main/java/org/purpleBean/kmip/common/`
- **Structures**: `src/main/java/org/purpleBean/kmip/common/structure/`

### Serializer Locations
```
src/main/java/org/purpleBean/kmip/codec/
├── json/serializer/kmip/common/[enumeration|structure]/
├── json/deserializer/kmip/common/[enumeration|structure]/
├── xml/serializer/kmip/common/[enumeration|structure]/
├── xml/deserializer/kmip/common/[enumeration|structure]/
├── ttlv/serializer/kmip/common/[enumeration|structure]/
└── ttlv/deserializer/kmip/common/[enumeration|structure]/
```

### Test Locations
```
src/test/java/org/purpleBean/kmip/
├── common/enumeration/YourEnumerationTest.java
├── common/structure/YourStructureTest.java
├── common/YourAttributeTest.java
└── codec/
    ├── JsonSerializationTest.java (add your type)
    ├── XmlSerializationTest.java (add your type)
    └── TtlvSerializationTest.java (add your type)
```

## Validation Strategy

### Where to Include Validations

#### 1. Constructor Validation (Main Code)
```java
public YourType(Value value) {
    KmipSpec spec = KmipContext.getSpec();
    if (!value.isSupportedFor(spec)) {
        throw new IllegalArgumentException(
            String.format("Unsupported value of %s provided for %s", 
                this.getKmipTag().getDescription(), spec)
        );
    }
    this.value = Objects.requireNonNull(value, "Value cannot be null");
}
```

#### 2. Builder Validation (Main Code)
```java
public YourStructure build() {
    // Validate required fields
    if (requiredField == null) {
        throw new IllegalArgumentException("Required field cannot be null");
    }
    
    // Validate KMIP spec compatibility
    KmipSpec spec = KmipContext.getSpec();
    validateFieldSupport(requiredField, spec);
    validateFieldSupport(optionalField, spec);
    
    return new YourStructure(requiredField, optionalField);
}
```

#### 3. Serializer Validation (Main Code)
```java
@Override
public void serialize(YourType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    KmipSpec spec = KmipContext.getSpec();
    if (!value.isSupportedFor(spec)) {
        throw new UnsupportedEncodingException("Type not supported for spec " + spec);
    }
    // ... serialization logic
}
```

### Why Include Validations

#### Main Code Validations
- **Type Safety**: Prevent invalid object creation at compile/runtime
- **KMIP Compliance**: Ensure objects conform to KMIP specification versions
- **Data Integrity**: Validate required fields and constraints
- **Early Failure**: Catch issues at object creation rather than serialization

#### Test Code Validations
- **Behavior Verification**: Ensure validations work as expected
- **Edge Case Coverage**: Test boundary conditions and error scenarios
- **Regression Prevention**: Catch validation logic changes
- **Documentation**: Show expected behavior through test examples

## Testing Strategy

### Test Categories

#### 1. Unit Tests
- Constructor validation
- Field access and equality
- KMIP spec compatibility
- Custom value registration (for enumerations)

#### 2. Serialization Tests
- JSON round-trip serialization
- XML round-trip serialization
- TTLV round-trip serialization
- Cross-format compatibility

#### 3. Validation Tests
- Invalid input handling
- Null value handling
- KMIP spec version mismatches
- Required field validation

#### 4. Integration Tests
- End-to-end message processing
- Multi-threaded codec contexts
- Performance benchmarks

### Test File Organization

Each KMIP object should have a dedicated test file with nested test classes:

```java
@DisplayName("YourType Tests")
class YourTypeTest extends BaseKmipTest {
    
    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties { }
    
    @Nested
    @DisplayName("Validation")
    class Validation { }
    
    @Nested
    @DisplayName("Serialization")
    class Serialization { }
    
    @Nested
    @DisplayName("KMIP Spec Compatibility")
    class KmipSpecCompatibility { }
}
```

## Next Steps

1. Review the detailed guides in the linked files
2. Choose your KMIP object type (Enumeration, Attribute, or Structure)
3. Follow the step-by-step implementation in the appropriate guide
4. Use the provided boilerplate code as templates
5. Implement comprehensive tests using the testing guide
6. Validate your implementation against the checklist

For specific implementation details, refer to the specialized guide files listed above.
