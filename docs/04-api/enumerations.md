# Enumerations

## Core Enumerations

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

### KeyFormatType

Represents the format of a cryptographic key.

**Example:**
```java
KeyFormatType format = KeyFormatType.RAW;
int value = format.getValue(); // 0x01
String name = format.name(); // "RAW"
```

## Working with Enumerations

### Getting a State value by code or name

```java
// Lookups require a KMIP spec
KmipContext.setSpec(KmipSpec.V1_4);
try {
    // By integer code
    State.Value activeByValue = State.fromValue(KmipSpec.V1_4, 0x00000002);

    // By name (case-insensitive)
    State.Value activeByName = State.fromName(KmipSpec.V1_4, "Active");

    // Construct a State instance
    State s1 = new State(activeByValue);
    State s2 = new State(State.Standard.ACTIVE);
} finally {
    KmipContext.clear();
}
```

### Accessing standard values

```java
// Standard values are available via the nested enum
State.Standard std = State.Standard.ACTIVE;
int code = std.getValue();
String description = std.getDescription();
```

### Custom Enumerations

You can define custom enumerations by implementing the `KmipEnumeration` interface:

Refer boilerplate code in [boilerplate-enum](../03-guides/boilerplate-enum.md)

## Best Practices

1. **Use Enums for Fixed Sets**: Use enumerations for fixed sets of values
2. **Handle Unknown Values**: Always handle the case of unknown values
3. **Use Descriptive Names**: Choose clear, descriptive names for enum values
4. **Document Values**: Document what each value represents
5. **Consider Extensibility**: Design enums to handle future extensions
