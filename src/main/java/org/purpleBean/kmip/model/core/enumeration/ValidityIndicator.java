package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that indicates the
 * validity of a cryptographic object or other data.
 * <p>
 * This enumeration is used in the {@code Validate} operation response to indicate
 * the result of the validation check.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code VALID}: The object is valid.</li>
 *   <li>{@code INVALID}: The object is invalid.</li>
 *   <li>{@code UNKNOWN}: The validity of the object is unknown.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see org.purpleBean.kmip.operation.Validate
 */
@Data
@Builder(toBuilder = true)
public class ValidityIndicator implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.VALIDITY_INDICATOR.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ValidityIndicator.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), ValidityIndicator::fromName, ValidityIndicator::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private ValidityIndicator(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static ValidityIndicator of(@NonNull Value value) {
        return ValidityIndicator.builder().value(value).build();
    }

    private static void checkValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        if (value < extensionStart || value > 0) {
            throw new IllegalArgumentException(
                    String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
            );
        }
    }

    /**
     * Register an extension value.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        checkValidExtensionValue(value);
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Value existingEnumByValue = VALUE_REGISTRY.get(value);
        Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(description);
        if (existingEnumByValue != null || existingEnumByDescription != null) {
            return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.putIfAbsent(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
        return custom;
    }

    /**
     * Look up by name.
     */
    public static Value fromName(String name) {
        KmipSpec spec = KmipContext.getSpec();
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No ValidityIndicator value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Look up by value.
     */
    public static Value fromValue(int value) {
        KmipSpec spec = KmipContext.getSpec();
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No ValidityIndicator value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Get registered values.
     */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    private void validate() {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for ValidityIndicator is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && value.isSupported();
    }

    public int getValue() {
        return value.getValue();
    }

    /**
     * The standard enumeration of Validity Indicators.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        VALID(0x00000001, "Valid", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID(0x00000002, "Invalid", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        UNKNOWN(0x00000003, "Unknown", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = false;

        Standard(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public ValidityIndicator inst() {
            return ValidityIndicator.of(this);
        }
    }

    /**
     * An interface representing a Validity Indicator value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<ValidityIndicator> {
    }

    /**
     * Represents a custom, vendor-specific Validity Indicator.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public static class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = true;

        public Extension(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public ValidityIndicator inst() {
            return ValidityIndicator.of(this);
        }
    }
}
