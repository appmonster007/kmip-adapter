# Core Concepts

The KMIP Adapter is built on a set of core interfaces that provide a consistent and type-safe foundation for all KMIP data types. Understanding these concepts is essential for using and extending the library.

## The `KmipDataType` Interface

`KmipDataType` is the root interface for all data types within the KMIP framework. It establishes a common contract for identifying and handling different kinds of data in KMIP messages.

### Key Responsibilities:

- **Type Identification:** Every KMIP data type must provide its `KmipTag` and `EncodingType`, which together uniquely identify it within a specific KMIP specification version.
- **Dynamic Registration:** A static registry (`TAG_REGISTRY`) allows for the dynamic registration of KMIP data types. This mechanism maps a combination of `KmipSpec`, `KmipTag.Value`, and `EncodingType` to the Java class that implements the data type. This is crucial for the codec to know which class to instantiate during deserialization.
- **Compatibility Checking:** The `isSupported()` method allows for runtime checks to determine if a given data type is supported under the currently active `KmipContext`.

## The `KmipStructure` Interface

`KmipStructure` extends `KmipDataType` and serves as a marker for all KMIP data types that are structures. A KMIP Structure is a composite data type that contains an ordered sequence of other `KmipDataType` instances.

### Key Characteristics:

- **Fixed Encoding:** All KMIP Structures have a predefined `EncodingType` of `STRUCTURE`.
- **Composite Nature:** A structure is composed of a list of other KMIP data types, which can be simple values, enumerations, or other structures.

## The `KmipAttribute` Interface

`KmipAttribute` extends `KmipDataType` and defines the contract for all KMIP attributes. Attributes are used to describe the properties of managed objects (e.g., cryptographic keys, certificates).

### Key Features:

- **Capability Flags:** Defines a set of methods (e.g., `isServerInitializable`, `isClientModifiable`) that describe the behavior and constraints of the attribute as defined by the KMIP specification.
- **State-Dependent Behavior:** Some attribute capabilities, like modifiability, can depend on the `State` of the managed object.
- **Attribute Representation:** Provides methods to get the generic `AttributeName` and `AttributeValue` of the attribute.

## The `KmipEnumeration` Interface

`KmipEnumeration` extends `KmipDataType` and serves as the base for all KMIP enumerations. An enumeration in KMIP is a set of named integer constants that represent specific values for a given attribute or parameter.

### Key Characteristics:

- **Fixed Encoding:** All KMIP enumerations have a predefined `EncodingType` of `ENUMERATION`.
- **Integer Value:** Each enumeration constant has an underlying integer value that is used in the TTLV encoding.

## The `KmipContext` Class

`KmipContext` manages the thread-local context for KMIP operations. This class provides a mechanism to set, get, and clear the current KMIP specification version on a per-thread basis. This is crucial for the codec to correctly serialize and deserialize data according to the rules of a specific KMIP version.

### Usage:

The `withSpec` method is the recommended way to manage the context:

```java
KmipContext.withSpec(KmipSpec.V1_2, () -> {
    // All KMIP operations within this lambda will use KMIP 1.2 rules.
    byte[] request = ttlvMapper.writeValueAsBytes(myRequest);
    // ...
});
```

## The `KmipTag` Class

`KmipTag` represents a KMIP tag, which is a 3-byte value that identifies a specific element in a KMIP message. This class provides a way to work with both standard and extension KMIP tags.

### Key Features:

- **Standard and Extension Tags:** The class provides an enum for standard tags and a mechanism to register custom extension tags.
- **Type-Safe and Descriptive:** Tags are represented as objects, not raw integers, which improves code readability and reduces errors.
- **Version-Aware:** Each tag knows which KMIP specifications it is supported in.
```