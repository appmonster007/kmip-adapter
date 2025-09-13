# Structures Guide

This guide covers the implementation of KMIP structures in the PurpleBean KMIP Adapter.

## Table of Contents
- [Overview](#overview)
- [Structure Implementation](#structure-implementation)
- [Nested Structures](#nested-structures)
- [Versioning](#versioning)
- [Serialization](#serialization)
- [Validation](#validation)
- [Testing](#testing)
- [Best Practices](#best-practices)

## Overview

KMIP structures are complex data types that contain multiple fields, including other structures, attributes, and primitive values. They form the building blocks of KMIP protocol messages.

## Structure Implementation

### Base Structure Class

```java
public abstract class AbstractKmipStructure implements KmipStructure, Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String typeName;
    private final Map<String, Object> fields = new LinkedHashMap<>();
    private final Set<String> requiredFields = new HashSet<>();
    
    protected AbstractKmipStructure(String typeName) {
        this.typeName = Objects.requireNonNull(typeName, "Type name cannot be null");
    }
    
    protected final <T> T getField(String name) {
        @SuppressWarnings("unchecked")
        T value = (T) fields.get(name);
        return value;
    }
    
    protected final <T> T getRequiredField(String name) {
        T value = getField(name);
        if (value == null && requiredFields.contains(name)) {
            throw new IllegalStateException("Required field not set: " + name);
        }
        return value;
    }
    
    protected final <T> void setField(String name, T value, boolean required) {
        if (required) {
            requiredFields.add(name);
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null for required field: " + name);
            }
        }
        fields.put(name, value);
    }
    
    @Override
    public String getTypeName() {
        return typeName;
    }
    
    @Override
    public Map<String, Object> getFields() {
        return Collections.unmodifiableMap(fields);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractKmipStructure)) return false;
        AbstractKmipStructure that = (AbstractKmipStructure) o;
        return typeName.equals(that.typeName) &&
               fields.equals(that.fields) &&
               requiredFields.equals(that.requiredFields);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(typeName, fields, requiredFields);
    }
    
    @Override
    public String toString() {
        return typeName + fields;
    }
}
```

### Concrete Structure Implementation

```java
public class SampleStructure extends AbstractKmipStructure {
    private static final String FIELD_NAME = "name";
    private static final String FIELD_VALUE = "value";
    private static final String FIELD_TIMESTAMP = "timestamp";
    private static final String FIELD_ATTRIBUTES = "attributes";
    
    private SampleStructure(Builder builder) {
        super("SampleStructure");
        
        setField(FIELD_NAME, builder.name, true);
        setField(FIELD_VALUE, builder.value, true);
        setField(FIELD_TIMESTAMP, builder.timestamp, false);
        setField(FIELD_ATTRIBUTES, Collections.unmodifiableList(
            new ArrayList<>(builder.attributes)), false);
    }
    
    public String getName() {
        return getRequiredField(FIELD_NAME);
    }
    
    public int getValue() {
        return getRequiredField(FIELD_VALUE);
    }
    
    public Optional<Instant> getTimestamp() {
        return Optional.ofNullable(getField(FIELD_TIMESTAMP));
    }
    
    public List<KmipAttribute<?>> getAttributes() {
        @SuppressWarnings("unchecked")
        List<KmipAttribute<?>> attrs = getField(FIELD_ATTRIBUTES);
        return attrs != null ? attrs : Collections.emptyList();
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {
        private String name;
        private Integer value;
        private Instant timestamp;
        private final List<KmipAttribute<?>> attributes = new ArrayList<>();
        
        private Builder() {}
        
        public Builder withName(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            return this;
        }
        
        public Builder withValue(int value) {
            if (value < 0) {
                throw new IllegalArgumentException("Value cannot be negative");
            }
            this.value = value;
            return this;
        }
        
        public Builder withTimestamp(Instant timestamp) {
            if (timestamp != null && timestamp.isAfter(Instant.now())) {
                throw new IllegalArgumentException("Timestamp cannot be in the future");
            }
            this.timestamp = timestamp;
            return this;
        }
        
        public Builder addAttribute(KmipAttribute<?> attribute) {
            this.attributes.add(Objects.requireNonNull(attribute, "Attribute cannot be null"));
            return this;
        }
        
        public Builder addAttributes(Collection<? extends KmipAttribute<?>> attributes) {
            for (KmipAttribute<?> attr : attributes) {
                addAttribute(attr);
            }
            return this;
        }
        
        public SampleStructure build() {
            if (name == null) {
                throw new IllegalStateException("Name is required");
            }
            if (value == null) {
                throw new IllegalStateException("Value is required");
            }
            return new SampleStructure(this);
        }
    }
}
```

## Nested Structures

### Parent-Child Relationship

```java
public class ParentStructure extends AbstractKmipStructure {
    private static final String FIELD_CHILDREN = "children";
    
    private ParentStructure(Builder builder) {
        super("ParentStructure");
        setField(FIELD_CHILDREN, Collections.unmodifiableList(
            new ArrayList<>(builder.children)), true);
    }
    
    @SuppressWarnings("unchecked")
    public List<ChildStructure> getChildren() {
        return getRequiredField(FIELD_CHILDREN);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {
        private final List<ChildStructure> children = new ArrayList<>();
        
        public Builder addChild(ChildStructure child) {
            children.add(Objects.requireNonNull(child, "Child cannot be null"));
            return this;
        }
        
        public ParentStructure build() {
            if (children.isEmpty()) {
                throw new IllegalStateException("At least one child is required");
            }
            return new ParentStructure(this);
        }
    }
}
```

## Versioning

### Version-Specific Fields

```java
public class VersionedStructure extends AbstractKmipStructure {
    private static final String FIELD_NEW_FEATURE = "newFeature";
    
    private VersionedStructure(Builder builder) {
        super("VersionedStructure");
        
        // This field is only supported in KMIP 1.2 and later
        if (KmipContext.getSpec().isAtLeast(KmipSpec.V1_2)) {
            setField(FIELD_NEW_FEATURE, builder.newFeature, false);
        } else if (builder.newFeature != null) {
            throw new UnsupportedOperationException(
                "New feature is only supported in KMIP 1.2 and later");
        }
    }
    
    public Optional<String> getNewFeature() {
        return Optional.ofNullable(getField(FIELD_NEW_FEATURE));
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {
        private String newFeature;
        
        public Builder withNewFeature(String newFeature) {
            this.newFeature = newFeature;
            return this;
        }
        
        public VersionedStructure build() {
            return new VersionedStructure(this);
        }
    }
}
```

## Serialization

### JSON Serializer

```java
public class SampleStructureJsonSerializer extends JsonSerializer<SampleStructure> {
    @Override
    public void serialize(SampleStructure value, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        
        gen.writeStartObject();
        
        // Write standard fields
        gen.writeStringField("name", value.getName());
        gen.writeNumberField("value", value.getValue());
        
        // Write optional timestamp
        value.getTimestamp().ifPresent(timestamp -> {
            try {
                gen.writeStringField("timestamp", timestamp.toString());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        
        // Write attributes if present
        if (!value.getAttributes().isEmpty()) {
            gen.writeArrayFieldStart("attributes");
            for (KmipAttribute<?> attr : value.getAttributes()) {
                gen.writeObject(attr);
            }
            gen.writeEndArray();
        }
        
        gen.writeEndObject();
    }
}
```

## Validation

### Cross-Field Validation

```java
public class ValidatedStructure extends AbstractKmipStructure {
    private static final String FIELD_START_DATE = "startDate";
    private static final String FIELD_END_DATE = "endDate";
    
    private ValidatedStructure(Builder builder) {
        super("ValidatedStructure");
        
        // Basic validation
        if (builder.endDate != null && builder.startDate.isAfter(builder.endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        
        setField(FIELD_START_DATE, builder.startDate, true);
        setField(FIELD_END_DATE, builder.endDate, false);
    }
    
    public LocalDate getStartDate() {
        return getRequiredField(FIELD_START_DATE);
    }
    
    public Optional<LocalDate> getEndDate() {
        return Optional.ofNullable(getField(FIELD_END_DATE));
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        
        public Builder withStartDate(LocalDate startDate) {
            this.startDate = Objects.requireNonNull(startDate, "Start date cannot be null");
            return this;
        }
        
        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate; // Can be null
            return this;
        }
        
        public ValidatedStructure build() {
            if (startDate == null) {
                throw new IllegalStateException("Start date is required");
            }
            return new ValidatedStructure(this);
        }
    }
}
```

## Testing

### Structure Tests

```java
class SampleStructureTest extends BaseKmipTest {
    
    @Test
    @DisplayName("Should create with required fields")
    void shouldCreateWithRequiredFields() {
        SampleStructure structure = SampleStructure.builder()
            .withName("test")
            .withValue(42)
            .build();
            
        assertThat(structure)
            .hasName("test")
            .hasValue(42)
            .hasNoTimestamp()
            .hasNoAttributes();
    }
    
    @Test
    @DisplayName("Should create with all fields")
    void shouldCreateWithAllFields() {
        Instant now = Instant.now();
        SampleStructure structure = SampleStructure.builder()
            .withName("test")
            .withValue(42)
            .withTimestamp(now)
            .addAttribute(StringAttribute.builder("attr1").withValue("value1").build())
            .addAttribute(NumberAttribute.builder("attr2").withValue(123).build())
            .build();
            
        assertThat(structure)
            .hasName("test")
            .hasValue(42)
            .hasTimestamp(now)
            .hasAttributeCount(2)
            .hasAttribute("attr1")
            .hasAttribute("attr2");
    }
    
    @Test
    @DisplayName("Should reject null name")
    void shouldRejectNullName() {
        assertThatThrownBy(() -> 
            SampleStructure.builder()
                .withName(null)
                .withValue(42)
                .build()
        ).isInstanceOf(NullPointerException.class)
         .hasMessage("Name cannot be null");
    }
    
    @Test
    @DisplayName("Should reject negative value")
    void shouldRejectNegativeValue() {
        assertThatThrownBy(() -> 
            SampleStructure.builder()
                .withName("test")
                .withValue(-1)
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessage("Value cannot be negative");
    }
    
    @Test
    @DisplayName("Should reject future timestamp")
    void shouldRejectFutureTimestamp() {
        Instant future = Instant.now().plus(1, ChronoUnit.DAYS);
        
        assertThatThrownBy(() -> 
            SampleStructure.builder()
                .withName("test")
                .withValue(42)
                .withTimestamp(future)
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessage("Timestamp cannot be in the future");
    }
    
    @Test
    @DisplayName("Should serialize to JSON")
    void shouldSerializeToJson() throws Exception {
        SampleStructure structure = SampleStructure.builder()
            .withName("test")
            .withValue(42)
            .addAttribute(StringAttribute.builder("attr1").withValue("value1").build())
            .build();
            
        String json = jsonMapper.writeValueAsString(structure);
        
        assertThatJson(json)
            .isObject()
            .containsEntry("name", "test")
            .containsEntry("value", 42)
            .hasArray("attributes");
    }
    
    @Test
    @DisplayName("Should deserialize from JSON")
    void shouldDeserializeFromJson() throws Exception {
        String json = "{\"name\":\"test\",\"value\":42,\"attributes\":[{\"name\":\"attr1\",\"value\":\"value1\"}]}";
        
        SampleStructure structure = jsonMapper.readValue(json, SampleStructure.class);
        
        assertThat(structure)
            .hasName("test")
            .hasValue(42)
            .hasAttributeCount(1)
            .hasAttribute("attr1");
    }
}
```

## Best Practices

1. **Immutability**:
   - Make structures immutable after construction
   - Use the builder pattern for complex objects
   - Defensively copy mutable collections

2. **Validation**:
   - Validate all constructor/builder parameters
   - Perform cross-field validation
   - Provide clear error messages

3. **Documentation**:
   - Document thread safety
   - Document validation rules
   - Include examples in Javadoc

4. **Testing**:
   - Test all validation rules
   - Test edge cases
   - Test serialization/deserialization
   - Test version compatibility

5. **Performance**:
   - Reuse builders when possible
   - Cache expensive computations
   - Use lazy initialization for expensive fields
