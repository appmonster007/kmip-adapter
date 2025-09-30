# Enumerations

See also: [Tests Index](../03-guides/tests-index.md) • [Boilerplate: Enumeration](../03-guides/development/boilerplate-enum.md)

## Core Enumerations

### FooEnum

Example enumeration implementation following the KMIP adapter pattern.

**Example:**
```java
// Create a FooEnum using a standard value
FooEnum fooEnum = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);
int value = fooEnum.getValue().getValue(); // 0x00000001
String desc = fooEnum.getValue().getDescription(); // "Placeholder1"

// Access KMIP metadata
KmipTag tag = fooEnum.getKmipTag(); // FOO_ENUM tag
EncodingType encoding = fooEnum.getEncodingType(); // ENUMERATION
```

### State

Represents the state of a managed object in the KMIP system.

**Example:**
```java
// Set KMIP spec for version-aware operations
KmipContext.setSpec(KmipSpec.V1_4);
try {
    // Create a State using a standard value
    State state = new State(State.Standard.ACTIVE);
    int value = state.getValue().getValue(); // 2
    String desc = state.getValue().getDescription(); // "Active"
} finally {
    KmipContext.clear();
}
```

### CryptographicAlgorithm

Represents cryptographic algorithms supported by KMIP.

**Example:**
```java
CryptographicAlgorithm algo = CryptographicAlgorithm.AES;
int value = algo.getValue(); // 3
String name = algo.name(); // "AES"
```

## Working with Enumerations

### Getting FooEnum values by code or name

```java
// By integer code
FooEnum.Value valueByCode = FooEnum.fromValue(0x00000001);

// By name (case-insensitive)
FooEnum.Value valueByName = FooEnum.fromName("Placeholder1");

// Construct FooEnum instances
FooEnum foo1 = new FooEnum(valueByCode);
FooEnum foo2 = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);

// Check equality
assertThat(foo1).isEqualTo(foo2);
```

### Accessing standard values

```java
// Standard values are available via the nested enum
FooEnum.Standard std = FooEnum.Standard.PLACEHOLDER_1;
int code = std.getValue(); // 0x00000001
String description = std.getDescription(); // "Placeholder1"
boolean isSupported = std.isSupported(); // checks current KmipContext
```

### Registry and Extension Support

```java
// Register custom enumeration values
FooEnum.Value custom = FooEnum.register(
    0x80000001, 
    "CustomValue", 
    Set.of(KmipSpec.V1_4)
);

// Use custom value
FooEnum customEnum = new FooEnum(custom);
```

### Custom Enumerations

You can define custom enumerations by implementing the `KmipEnumeration` interface:

Refer boilerplate code in [boilerplate-enum](../03-guides/development/boilerplate-enum.md)

## Best Practices

1. **Use Enums for Fixed Sets**: Use enumerations for fixed sets of values
2. **Handle Unknown Values**: Always handle the case of unknown values
3. **Use Descriptive Names**: Choose clear, descriptive names for enum values
4. **Document Values**: Document what each value represents
5. **Consider Extensibility**: Design enums to handle future extensions
