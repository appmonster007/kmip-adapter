package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * method used to split a key into multiple parts.
 * <p>
 * Key splitting is a technique used to divide a key into multiple shares, where a
 * certain threshold of shares is required to reconstruct the original key. This
 * enumeration lists the methods supported by KMIP for this purpose.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code XOR}: The key is split using a simple XOR operation.</li>
 *   <li>{@code POLYNOMIAL_SHARING_GF_216}: The key is split using Shamir's Secret Sharing over the Galois Field GF(2^16).</li>
 *   <li>{@code POLYNOMIAL_SHARING_PRIME_FIELD}: The key is split using Shamir's Secret Sharing over a prime field.</li>
 *   <li>{@code POLYNOMIAL_SHARING_GF_28}: The key is split using Shamir's Secret Sharing over the Galois Field GF(2^8).</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see org.purpleBean.kmip.operation.CreateSplitKey
 * @see org.purpleBean.kmip.operation.JoinSplitKey
 */
@Data
@Builder(toBuilder = true)
public class SplitKeyMethod implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.SPLIT_KEY_METHOD.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SplitKeyMethod.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), SplitKeyMethod::fromName, SplitKeyMethod::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private SplitKeyMethod(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static SplitKeyMethod of(@NonNull Value value) {
        return SplitKeyMethod.builder().value(value).build();
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
                        String.format("No SplitKeyMethod value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No SplitKeyMethod value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for SplitKeyMethod is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Split Key Methods.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        XOR(0x00000001, "XOR", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        POLYNOMIAL_SHARING_GF_216(0x00000002, "PolynomialSharingGF2_16", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        POLYNOMIAL_SHARING_PRIME_FIELD(0x00000003, "PolynomialSharingPrimeField", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        POLYNOMIAL_SHARING_GF_28(0x00000004, "PolynomialSharingGF2_8", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public SplitKeyMethod inst() {
            return SplitKeyMethod.of(this);
        }
    }

    /**
     * An interface representing a Split Key Method value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<SplitKeyMethod> {
    }

    /**
     * Represents a custom, vendor-specific Split Key Method.
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
        public SplitKeyMethod inst() {
            return SplitKeyMethod.of(this);
        }
    }
}
