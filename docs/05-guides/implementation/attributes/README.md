# Attributes Guide

This guide covers the implementation of KMIP attributes in the PurpleBean KMIP Adapter.

## Table of Contents
- [Overview](#overview)
- [Attribute Implementation](#attribute-implementation)
- [Common Attribute Patterns](#common-attribute-patterns)
- [Validation](#validation)
- [Serialization](#serialization)
- [Testing](#testing)
- [Best Practices](#best-practices)

## Overview

KMIP attributes represent metadata associated with managed objects like keys, certificates, and other cryptographic objects. They provide additional information about these objects and control their behavior.

## Attribute Implementation

### Base Attribute Class

```java
public abstract class AbstractKmipAttribute<T> implements KmipAttribute<T> {
    private final String name;
    private final T value;
    private final boolean required;
    
    protected AbstractKmipAttribute(String name, T value, boolean required) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.value = validate(value);
        this.required = required;
    }
    
    protected abstract T validate(T value);
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public T getValue() {
        return value;
    }
    
    @Override
    public boolean isRequired() {
        return required;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractKmipAttribute)) return false;
        AbstractKmipAttribute<?> that = (AbstractKmipAttribute<?>) o;
        return required == that.required &&
               name.equals(that.name) &&
               Objects.equals(value, that.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, value, required);
    }
    
    @Override
    public String toString() {
        return name + "=" + value + (required ? " (required)" : "");
    }
}
```

### Concrete Attribute Implementation

```java
public class StringAttribute extends AbstractKmipAttribute<String> {
    private final int minLength;
    private final int maxLength;
    private final Pattern pattern;
    
    private StringAttribute(Builder builder) {
        super(builder.name, builder.value, builder.required);
        this.minLength = builder.minLength;
        this.maxLength = builder.maxLength;
        this.pattern = builder.pattern;
    }
    
    @Override
    protected String validate(String value) {
        if (value == null) {
            if (isRequired()) {
                throw new IllegalArgumentException("Value is required");
            }
            return null;
        }
        
        if (value.length() < minLength) {
            throw new IllegalArgumentException("Value must be at least " + minLength + " characters");
        }
        
        if (value.length() > maxLength) {
            throw new IllegalArgumentException("Value cannot exceed " + maxLength + " characters");
        }
        
        if (pattern != null && !pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Value does not match pattern: " + pattern);
        }
        
        return value;
    }
    
    public static Builder builder(String name) {
        return new Builder(name);
    }
    
    public static final class Builder {
        private final String name;
        private String value;
        private boolean required = false;
        private int minLength = 0;
        private int maxLength = Integer.MAX_VALUE;
        private Pattern pattern;
        
        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
        }
        
        public Builder withValue(String value) {
            this.value = value;
            return this;
        }
        
        public Builder required() {
            this.required = true;
            return this;
        }
        
        public Builder withMinLength(int minLength) {
            if (minLength < 0) {
                throw new IllegalArgumentException("Minimum length cannot be negative");
            }
            this.minLength = minLength;
            return this;
        }
        
        public Builder withMaxLength(int maxLength) {
            if (maxLength < 0) {
                throw new IllegalArgumentException("Maximum length cannot be negative");
            }
            this.maxLength = maxLength;
            return this;
        }
        
        public Builder withPattern(String regex) {
            this.pattern = Pattern.compile(regex);
            return this;
        }
        
        public StringAttribute build() {
            return new StringAttribute(this);
        }
    }
}
```

## Common Attribute Patterns

### Required vs. Optional Attributes

```java
// Required attribute
StringAttribute requiredAttr = StringAttribute.builder("requiredAttr")
    .withValue("value")
    .required()
    .build();

// Optional attribute (default)
StringAttribute optionalAttr = StringAttribute.builder("optionalAttr")
    .withValue("value")
    .build();
```

### Bounded Length Attributes

```java
// String with length constraints
StringAttribute nameAttr = StringAttribute.builder("name")
    .withValue("John Doe")
    .withMinLength(1)
    .withMaxLength(100)
    .build();
```

### Pattern Validation

```java
// Email attribute with pattern validation
StringAttribute emailAttr = StringAttribute.builder("email")
    .withValue("user@example.com")
    .withPattern("^[A-Za-z0-9+_.-]+@(.+)$")
    .build();
```

### Date/Time Attributes

```java
public class DateTimeAttribute extends AbstractKmipAttribute<Instant> {
    private DateTimeAttribute(Builder builder) {
        super(builder.name, builder.value, builder.required);
    }
    
    @Override
    protected Instant validate(Instant value) {
        if (value == null) {
            if (isRequired()) {
                throw new IllegalArgumentException("Value is required");
            }
            return null;
        }
        
        // Ensure the date is not in the future
        if (value.isAfter(Instant.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        
        return value;
    }
    
    public static Builder builder(String name) {
        return new Builder(name);
    }
    
    public static final class Builder {
        private final String name;
        private Instant value;
        private boolean required = false;
        
        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
        }
        
        public Builder withValue(Instant value) {
            this.value = value;
            return this;
        }
        
        public Builder withValue(LocalDateTime value) {
            this.value = value.atZone(ZoneOffset.UTC).toInstant();
            return this;
        }
        
        public Builder required() {
            this.required = true;
            return this;
        }
        
        public DateTimeAttribute build() {
            return new DateTimeAttribute(this);
        }
    }
}
```

## Validation

### Custom Validators

```java
public class CustomStringAttribute extends StringAttribute {
    private final Predicate<String> customValidator;
    
    private CustomStringAttribute(Builder builder) {
        super(builder);
        this.customValidator = builder.customValidator;
    }
    
    @Override
    protected String validate(String value) {
        value = super.validate(value);
        
        if (value != null && customValidator != null && !customValidator.test(value)) {
            throw new IllegalArgumentException("Custom validation failed");
        }
        
        return value;
    }
    
    public static Builder builder(String name) {
        return new Builder(name);
    }
    
    public static final class Builder extends StringAttribute.Builder {
        private Predicate<String> customValidator;
        
        private Builder(String name) {
            super(name);
        }
        
        public Builder withCustomValidator(Predicate<String> validator) {
            this.customValidator = validator;
            return this;
        }
        
        @Override
        public CustomStringAttribute build() {
            return new CustomStringAttribute(this);
        }
    }
}
```

## Serialization

### JSON Serializer

```java
public class StringAttributeJsonSerializer extends JsonSerializer<StringAttribute> {
    @Override
    public void serialize(StringAttribute value, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeStartObject();
            gen.writeStringField("name", value.getName());
            gen.writeStringField("value", value.getValue());
            gen.writeBooleanField("required", value.isRequired());
            gen.writeEndObject();
        }
    }
}
```

### XML Serializer

```java
public class StringAttributeXmlSerializer extends XmlSerializer<StringAttribute> {
    @Override
    public void serialize(StringAttribute value, XmlGenerator gen, SerializerProvider provider) 
            throws IOException {
        
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeStartObject();
            gen.writeStringField("name", value.getName());
            gen.writeStringField("value", value.getValue());
            gen.writeBooleanField("required", value.isRequired());
            gen.writeEndObject();
        }
    }
}
```

## Testing

### Basic Tests

```java
class StringAttributeTest {
    
    @Test
    @DisplayName("Should create with valid parameters")
    void shouldCreateWithValidParameters() {
        StringAttribute attr = StringAttribute.builder("test")
            .withValue("value")
            .build();
            
        assertThat(attr)
            .hasName("test")
            .hasValue("value")
            .isNotRequired();
    }
    
    @Test
    @DisplayName("Should require value when marked as required")
    void shouldRequireValueWhenMarkedAsRequired() {
        assertThatThrownBy(() -> 
            StringAttribute.builder("test")
                .required()
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessage("Value is required");
    }
    
    @Test
    @DisplayName("Should validate minimum length")
    void shouldValidateMinimumLength() {
        assertThatThrownBy(() -> 
            StringAttribute.builder("test")
                .withValue("a")
                .withMinLength(2)
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessage("Value must be at least 2 characters");
    }
    
    @Test
    @DisplayName("Should validate maximum length")
    void shouldValidateMaximumLength() {
        assertThatThrownBy(() -> 
            StringAttribute.builder("test")
                .withValue("abc")
                .withMaxLength(2)
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessage("Value cannot exceed 2 characters");
    }
    
    @Test
    @DisplayName("Should validate pattern")
    void shouldValidatePattern() {
        StringAttribute attr = StringAttribute.builder("test")
            .withValue("abc123")
            .withPattern("^[a-z0-9]+$")
            .build();
            
        assertThat(attr).isNotNull();
        
        assertThatThrownBy(() -> 
            StringAttribute.builder("test")
                .withValue("abc@123")
                .withPattern("^[a-z0-9]+$")
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessage("Value does not match pattern: ^[a-z0-9]+$");
    }
    
    @Test
    @DisplayName("Should serialize to JSON")
    void shouldSerializeToJson() throws Exception {
        StringAttribute attr = StringAttribute.builder("test")
            .withValue("value")
            .required()
            .build();
            
        String json = jsonMapper.writeValueAsString(attr);
        
        assertThatJson(json)
            .isObject()
            .containsEntry("name", "test")
            .containsEntry("value", "value")
            .containsEntry("required", true);
    }
    
    @Test
    @DisplayName("Should deserialize from JSON")
    void shouldDeserializeFromJson() throws Exception {
        String json = "{\"name\":\"test\",\"value\":\"value\",\"required\":true}";
        
        StringAttribute attr = jsonMapper.readValue(json, StringAttribute.class);
        
        assertThat(attr)
            .hasName("test")
            .hasValue("value")
            .isRequired();
    }
}
```

## Best Practices

1. **Immutability**:
   - Make attribute classes immutable
   - Use the builder pattern for construction
   - Validate all inputs

2. **Validation**:
   - Validate values at construction time
   - Provide clear error messages
   - Support both required and optional attributes

3. **Documentation**:
   - Document validation rules
   - Include examples in Javadoc
   - Document thread safety

4. **Testing**:
   - Test all validation rules
   - Test edge cases
   - Test serialization/deserialization
   - Test thread safety

5. **Performance**:
   - Cache validation patterns
   - Reuse validators when possible
   - Minimize object creation in hot paths
