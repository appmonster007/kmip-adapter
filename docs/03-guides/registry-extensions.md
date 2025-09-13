# Registry and Extensions Guide

## Overview

This guide consolidates the enumeration/extension registry patterns used across the project so you can safely add vendor-specific values and verify support across KMIP versions.

Relevant types in this repository that implement this pattern:
- `org.purplebean.kmip.common.enumeration.State`
- `org.purplebean.kmip.KmipTag` (tag registry)

The canonical reference patterns originate from the root guide `KMIP_IMPLEMENTATION_GUIDE.md` and have been adapted here to match the actual code.

## Enumeration Registry Pattern

- Backing registries map integer values and descriptions to `Value` implementations.
- A set of standard values (enum) is pre-registered.
- You can register extensions at runtime with validation.
- KMIP version support is validated both on registration and on object construction.

### Example API (adapted to `State`)

```java
// Register a custom state (use UnknownVersion + specific versions)
State.Value customState = State.register(
    0x80000001,
    "MyCustomState",
    Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_4)
);

// Use the registered value to construct a State instance
KmipContext.setSpec(KmipSpec.V1_4);
try {
    State state = new State(customState);
    // ... use state ...
} finally {
    KmipContext.clear();
}
```

### Lookup Helpers

```java
// Set the KMIP spec for the current context
KmipContext.setSpec(KmipSpec.V1_4);
try {
    // Look up by integer value
    State.Value activeByValue = State.fromValue(KmipSpec.V1_4, 0x00000002); // ACTIVE
    
    // Look up by name (case-insensitive)
    State.Value activeByName = State.fromName(KmipSpec.V1_4, "ACTIVE");
    
    // Create a new State instance
    State state = new State(activeByValue);
} finally {
    // Always clear the context
    KmipContext.clear();
}
```

### Validation Rules (enforced by guides and serializers)
- Extension values should be in the vendor extension range (0x80000000 - 0xFFFFFFFF).
- Description must be non-empty and unique.
- Provide at least one supported `KmipSpec` (include `UnknownVersion` plus specific versions you target).
- Always set `KmipContext` before constructing or serializing values so version checks can occur.

## Tag Registry Pattern (KmipTag)

`KmipTag` supports registering custom tags for vendor extensions.

```java
// Define and register a custom tag (example)
KmipTag.Value customTag = KmipTag.register(
    0x540001,
    "TestCustomTag"
);

// Use the registered tag where applicable (e.g., in structures/serializers)
```

Use these tags in serializers and structures in the same way as standard tags.

## Version Validation with KmipContext

Always set the spec for the current thread before constructing objects that depend on version:

```java
KmipContext.setSpec(KmipSpec.V1_4);
try {
    // Construct a version-aware value
    State state = new State(State.Standard.ACTIVE);
    // ... use state ...
} finally {
    KmipContext.clear();
}
```

## Serializer/Module Registration Recap

- JSON: register `KmipJsonModule` on an `ObjectMapper`.
- XML: register `KmipXmlModule` on an `XmlMapper`.
- TTLV: use `TtlvMapper` and register `KmipTtlvModule`.

```java
TtlvMapper ttlv = new TtlvMapper();
ttlv.registerModule(new KmipTtlvModule());
```

## Best Practices

- Keep extension value ranges and descriptions unique and documented.
- Gate all construction paths with KMIP spec checks (`KmipContext.getSpec()`).
- Provide negative tests for invalid extension values, duplicate names, and unsupported specs.
- Prefer immutable value objects and builders for complex structures.
