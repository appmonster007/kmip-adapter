# KMIP Adapter

## Introduction

The KMIP Adapter is a modern, lightweight, and extensible Java library designed for encoding and decoding messages for the Key Management Interoperability Protocol (KMIP). It provides a robust framework for handling different versions of the KMIP specification and supports multiple data encoding formats, including TTLV, JSON, and XML.

Built with an emphasis on type safety and ease of use, the library leverages a thread-safe, context-aware design to seamlessly manage version-specific rules for serialization and deserialization.

## Key Features

- **Multi-Version Support:** Natively handles variations between different KMIP specifications (e.g., v1.2, v2.1, v3.0) through a flexible context system.
- **Multiple Encodings:** Provides built-in support for the standard TTLV (Tag-Type-Length-Value) format, as well as JSON and XML for broader interoperability.
- **Extensible Architecture:** Easily extend the library to support custom vendor extensions, new KMIP structures, or future specification versions.
- **Type-Safe API:** A clear and well-defined API built around interfaces (`KmipDataType`, `KmipStructure`, `KmipAttribute`) ensures compile-time safety and code clarity.
- **Developer-Friendly:** Uses modern Java features and libraries like Lombok to reduce boilerplate and improve developer productivity.

## Quick Start

Here is a brief example of how to serialize and deserialize a KMIP structure using the TTLV codec.

```java
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyWrappingData;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

// 1. Initialize the TtlvMapper
TtlvMapper ttlvMapper = new TtlvMapper();
// Register modules if you have custom types (recommended)
// ttlvMapper.registerModule(new KmipTtlvModule());

// 2. Create a KMIP model object
KeyWrappingData keyWrappingData = KeyWrappingData.builder()
        .wrappingMethod(new WrappingMethod(WrappingMethod.Value.ENCRYPT))
        .build();

// 3. Serialize the object within a specific KMIP version context
byte[] ttlvBytes = KmipContext.withSpec(KmipSpec.V2_1, () -> {
    try {
        return ttlvMapper.writeValueAsBytes(keyWrappingData);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
});

// 4. Deserialize the bytes back into an object
KeyWrappingData deserializedObject = KmipContext.withSpec(KmipSpec.V2_1, () -> {
    try {
        return ttlvMapper.readValue(ttlvBytes, KeyWrappingData.class);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
});

System.out.println("Objects are equal: " + keyWrappingData.equals(deserializedObject));
```

## Documentation

For more detailed information, please refer to the following documents:

- **[Core Concepts](./core-concepts.md):** An essential guide to the fundamental interfaces and design principles of the library.
- **[Project Architecture](./architecture.md):** A high-level overview of the project's structure and modules.
- **[Development Guide](./development-guide.md):** Instructions on how to add new KMIP types and contribute to the codebase.
- **[Contribution Guidelines](./contributing.md):** Information on how to contribute to the project.
```