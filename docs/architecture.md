# Project Architecture

The KMIP Adapter is organized into a modular structure that separates the core API, data models, and codec implementations. This design promotes a clean separation of concerns and makes the library easy to navigate and extend.

## Directory Structure

The main source code is located in `src/main/java/org/purpleBean/kmip` and is organized as follows:

- **`api/`**: Contains the core interfaces and classes that define the fundamental building blocks of the KMIP type system. This includes `KmipDataType`, `KmipStructure`, `KmipAttribute`, `KmipTag`, and `KmipContext`. This is the primary entry point for understanding the library's design.

- **`model/`**: Contains the concrete implementations of the KMIP data types defined in the `api/` directory. This includes structures, enumerations, and other data types that are part of the KMIP specification.
    - **`core/`**: Contains the core data types that are fundamental to the KMIP specification.
        - **`structure/`**: Contains the implementation of KMIP structures (e.g., `KeyWrappingData`, `ProtocolVersion`).
        - **`enumeration/`**: Contains the implementation of KMIP enumerations (e.g., `WrappingMethod`, `ResultStatus`).
        - **`type/`**: Contains the implementation of simple KMIP data types (e.g., `TextString`, `Integer`, `DateTime`).

- **`codec/`**: Contains the serialization and deserialization logic for different encoding formats (TTLV, JSON, XML).
    - **`ttlv/`**: The implementation for the standard TTLV (Tag-Type-Length-Value) encoding.
        - **`serializer/`**: Contains the logic for serializing Java objects into TTLV byte streams.
        - **`deserializer/`**: Contains the logic for deserializing TTLV byte streams into Java objects.
        - **`mapper/`**: Contains the central `TtlvMapper` class that orchestrates the TTLV serialization and deserialization process.
    - **`json/`**: The implementation for JSON encoding.
    - **`xml/`**: The implementation for XML encoding.

- **`util/`**: Contains utility classes that are used throughout the project.

## Key Design Principles

- **Interface-Driven:** The library is heavily reliant on interfaces (`KmipDataType`, `KmipStructure`, etc.) to define the contracts for KMIP data types. This allows for a flexible and extensible design.
- **Composition over Inheritance:** The library favors composition over inheritance. For example, a `KeyWrappingData` structure is composed of other `KmipDataType` instances, rather than inheriting from them.
- **Static Registries:** The library uses static registries to map KMIP tags and encoding types to their corresponding Java classes. This allows for a dynamic and extensible codec system.
- **Immutability:** Where possible, the data model objects are immutable, which makes them thread-safe and predictable.
- **Context-Awareness:** The `KmipContext` class allows the library to be aware of the current KMIP specification version, which is crucial for handling version-specific rules.
```