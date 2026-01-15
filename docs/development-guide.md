# Development Guide

This guide provides instructions on how to extend the KMIP Adapter with new data types, structures, and attributes.

## Adding a New KMIP Structure

To add a new KMIP structure, you need to perform the following steps:

1.  **Create the Model Class:**
    -   Create a new Java class in the appropriate package under `org.purpleBean.kmip.model.core.structure`.
    -   The class should implement the `KmipStructure` interface.
    -   Use Lombok annotations (`@Data`, `@Builder`) to reduce boilerplate code.
    -   Define the fields of the structure as `final` and use the `@NonNull` annotation for required fields.
    -   Define a static `kmipTag` field with the appropriate `KmipTag.Standard` value.
    -   Define a static `supportedVersions` set to specify which KMIP versions support this structure.
    -   Implement the `getValues()` method to return a list of all the fields in the structure.

    **Example: `KeyWrappingData.java`**
    ```java
    @Data
    @Builder(toBuilder = true)
    public class KeyWrappingData implements KmipStructure {
        public static final KmipTag kmipTag = KmipTag.Standard.KEY_WRAPPING_DATA.inst();
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2, KmipSpec.V2_1);

        @NonNull
        private final WrappingMethod wrappingMethod;
        // ... other fields

        @Override
        public List<KmipDataType> getValues() {
            return Stream.of(wrappingMethod, /* other fields */)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }
    ```

2.  **Create the Deserializers:**
    -   Create a new deserializer class for each supported encoding format (TTLV, JSON, XML) in the corresponding `codec` package.
    -   The deserializer should extend the appropriate abstract base class (e.g., `AbstractKmipStructureTtlvDeserializer`).
    -   Implement the `createBuilder()`, `setValue()`, and `build()` methods.

    **Example: `KeyWrappingDataTtlvDeserializer.java`**
    ```java
    public class KeyWrappingDataTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<KeyWrappingData, KeyWrappingData.KeyWrappingDataBuilder> {
        // ... constructor and other methods

        @Override
        protected void setValue(KeyWrappingData.KeyWrappingDataBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
            switch (nodeTag) {
                case KmipTag.Standard.WRAPPING_METHOD -> builder.wrappingMethod(mapper.readValue(p, WrappingMethod.class));
                // ... other cases
            }
        }
    }
    ```

3.  **Create the Serializers:**
    -   Create a new serializer class for each supported encoding format.
    -   The serializer should extend the appropriate abstract base class (e.g., `AbstractKmipStructureTtlvSerializer`).
    -   In most cases, the base class will handle the serialization, so the class can be empty.

4.  **Create Tests:**
    -   Add a new test class for the model in the `src/test/java/org/purpleBean/kmip/model/core/structure` package.
    -   Add new test classes for the serializers and deserializers in the corresponding `codec` test packages.

## Adding a New KMIP Enumeration

To add a new KMIP enumeration, follow these steps:

1.  **Create the Enum Class:**
    -   Create a new Java enum in the `org.purpleBean.kmip.model.core.enumeration` package.
    -   The enum should implement the `KmipEnumeration` interface.
    -   Define the enum constants with their integer values and descriptions.
    -   Implement the `getValue()` and `getDescription()` methods.

2.  **Create Deserializers and Serializers:**
    -   Follow the same process as for structures, but extend the appropriate enumeration base classes.

3.  **Create Tests:**
    -   Add tests for the new enumeration and its codecs.

## Adding a New KMIP Attribute

Adding a new attribute is similar to adding a structure, but the model class should implement the `KmipAttribute` interface. You will also need to implement the capability flag methods (e.g., `isServerInitializable`).
```