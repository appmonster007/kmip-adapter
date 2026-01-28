package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies a
 * variation of the FIPS 186 standard for digital signatures.
 * <p>
 * This enumeration is used to indicate a specific version or change notice of the
 * FIPS 186 standard that applies to a given cryptographic operation or object.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code UNSPECIFIED}: The variation is not specified.</li>
 *   <li>{@code GP_X_ORIGINAL}: The original version of the standard.</li>
 *   <li>{@code GP_X_CHANGE_NOTICE}: A change notice to the standard.</li>
 *   <li>{@code X_ORIGINAL}: The original version of the X parameter generation.</li>
 *   <li>{@code X_CHANGE_NOTICE}: A change notice to the X parameter generation.</li>
 *   <li>{@code K_ORIGINAL}: The original version of the K parameter generation.</li>
 *   <li>{@code K_CHANGE_NOTICE}: A change notice to the K parameter generation.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see DigitalSignatureAlgorithm
 */
@Data
@Builder(toBuilder = true)
public class Fips186Variation implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.FIPS186_VARIATION.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Fips186Variation.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), Fips186Variation::fromName, Fips186Variation::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private Fips186Variation(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static Fips186Variation of(@NonNull Value value) {
        return Fips186Variation.builder().value(value).build();
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
                        String.format("No Fips186Variation value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No Fips186Variation value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for Fips186Variation is not supported for KMIP spec %s", value.getDescription(), spec)
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

    public int getIntValue() {
        return value.getValue();
    }

    /**
     * The standard enumeration of FIPS 186 Variations.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        UNSPECIFIED(0x00000001, "Unspecified", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GP_X_ORIGINAL(0x00000002, "GpXOriginal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GP_X_CHANGE_NOTICE(0x00000003, "GpXChangeNotice", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        X_ORIGINAL(0x00000004, "XOriginal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        X_CHANGE_NOTICE(0x00000005, "XChangeNotice", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        K_ORIGINAL(0x00000006, "KOriginal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        K_CHANGE_NOTICE(0x00000007, "KChangeNotice", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public Fips186Variation inst() {
            return Fips186Variation.of(this);
        }
    }

    /**
     * An interface representing a FIPS 186 Variation value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<Fips186Variation> {
    }

    /**
     * Represents a custom, vendor-specific FIPS 186 Variation.
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
        public Fips186Variation inst() {
            return Fips186Variation.of(this);
        }
    }
}
