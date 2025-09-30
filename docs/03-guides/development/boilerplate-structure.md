# KMIP Structure Boilerplate (Generated)

This guide shows the actual generated code for KMIP structure classes using `FooStructure` as an example.

## 1. Core Structure Class

**File:** `src/main/java/org/purpleBean/kmip/common/structure/FooStructure.java`

```java
package org.purpleBean.kmip.common.structure;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.KmipStructure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP FooStructure structure.
 */
@Data
@Builder
public class FooStructure implements KmipStructure {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_STRUCTURE);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, FooStructure.class);
        }
    }

    @NonNull
    private final ActivationDate activationDate;
    private final State state;

    // If required, then provide static constructor 'of' methods, with appropriate validation and null checks
    // Example:
    public static FooStructure of(@NonNull ActivationDate activationDate, State state) {
        return FooStructure.builder().activationDate(activationDate).state(state).build();
    }
    
    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }
    
    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(activationDate, state)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Preferably, add validations in All Arg Constructor
    public static class FooStructureBuilder {
        public FooStructure build() {
            // Validate required fields
            validate();
            return new FooStructure(activationDate, state);
        }

        private void validate() {
            List<KmipDataType> fields = Stream.of(activationDate, state)
                                              .filter(Objects::nonNull)
                                              .collect(Collectors.toList());

            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupported()) {
                    throw new IllegalArgumentException(
                        String.format("%s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), spec)
                    );
                }
            }

            // Validate required fields
            // Add required-field checks as needed
        }
    }
}
```

## 2. JSON Serializer

**File:** `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/structure/FooStructureJsonSerializer.java`

```java
public class FooStructureJsonSerializer extends KmipDataTypeJsonSerializer<FooStructure> {

    @Override
    public void serialize(FooStructure fooStructure, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (fooStructure == null) {
            return;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!fooStructure.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", fooStructure.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(fooStructure.getKmipTag());
        gen.writeStringField("type", fooStructure.getEncodingType().getDescription());
        
        gen.writeArrayFieldStart("value");
        for (KmipDataType value : fooStructure.getValues()) {
            gen.writeObject(value);
        }
        gen.writeEndArray();
        
        gen.writeEndObject();
    }
}
```

## 3. JSON Deserializer

**File:** `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/structure/FooStructureJsonDeserializer.java`

```java
public class FooStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<FooStructure> {
    @Override
    public FooStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        // Validation and parsing logic...
        
        // Parse structure components from value array
        JsonNode valueNode = node.get("value");
        if (!valueNode.isArray()) {
            ctxt.reportInputMismatch(FooStructure.class, "Expected array for structure value");
            return null;
        }
        
        // Extract components based on expected structure
        ActivationDate activationDate = null;
        State state = null;
        
        // Parse each component from the array...
        
        FooStructure result = FooStructure.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
        
        if (!result.isSupported()) {
            throw new NoSuchElementException(
                String.format("FooStructure is not supported for KMIP spec %s", spec)
            );
        }
        
        return result;
    }
}
```

## 4. Test Class

**File:** `src/test/java/org/purpleBean/kmip/common/structure/FooStructureTest.java`

```java
@DisplayName("FooStructure Domain Tests")
class FooStructureTest extends AbstractKmipStructureSuite<FooStructure> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<FooStructure> type() {
        return FooStructure.class;
    }

    @Override
    protected FooStructure createDefault() {
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        State state = new State(State.Standard.ACTIVE);
        return FooStructure.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // Validate component types
        assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }
}
```

## Key Features

1. **Composite structure** with multiple KMIP data types
2. **Builder validation** with KMIP spec compatibility checking
3. **Component management** through `getValues()` method
4. **Static factory methods** for common construction patterns
5. **Comprehensive serialization** support (JSON/XML/TTLV)
6. **Immutable design** for thread safety
7. **Validation at build time** to ensure consistency

## Usage

```java
// Create with factory method
ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
State state = new State(State.Standard.ACTIVE);
FooStructure foo = FooStructure.of(activationDate, state);

// Create with builder
FooStructure foo = FooStructure.builder()
    .activationDate(activationDate)
    .state(state)
    .build();

// Access components
List<KmipDataType> components = foo.getValues();
ActivationDate date = foo.getActivationDate();
State currentState = foo.getState();
```

## Notes

- The generated template uses `ActivationDate` and `State` as example fields
- Update the fields based on your actual structure requirements
- Add validation logic in the builder's `validate()` method
- Consider adding additional factory methods for common use cases
- The `getValues()` method returns all non-null components for serialization
