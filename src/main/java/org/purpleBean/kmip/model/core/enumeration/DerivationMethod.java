package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * method used to derive a new key from an existing key or secret.
 * <p>
 * Key derivation is the process of generating one or more keys from a master key
 * or a shared secret, often using a pseudorandom function. This enumeration lists
 * the key derivation methods supported by KMIP.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code PBKDF2}: Password-Based Key Derivation Function 2.</li>
 *   <li>{@code HASH}: A hash-based key derivation function.</li>
 *   <li>{@code HMAC}: An HMAC-based key derivation function.</li>
 *   <li>{@code ENCRYPT}: A key derivation function based on encryption.</li>
 *   <li>{@code NIST800_108_C}: NIST SP 800-108 Counter Mode KDF.</li>
 *   <li>{@code NIST800_108_F}: NIST SP 800-108 Feedback Mode KDF.</li>
 *   <li>{@code NIST800_108_DPI}: NIST SP 800-108 Double-Pipeline Iteration Mode KDF.</li>
 *   <li>{@code ASYMMETRIC_KEY}: Key derivation using an asymmetric key.</li>
 *   <li>{@code AWS_SIGNATURE_VERSION_4}: AWS Signature Version 4 key derivation.</li>
 *   <li>{@code HKDF}: HMAC-based Extract-and-Expand Key Derivation Function.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see org.purpleBean.kmip.operation.DeriveKey
 */
@Data
@Builder(toBuilder = true)
public class DerivationMethod implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.DERIVATION_METHOD.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description.toLowerCase(Locale.ROOT), s);
        }

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DerivationMethod.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), DerivationMethod::fromName, DerivationMethod::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private DerivationMethod(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static DerivationMethod of(@NonNull Value value) {
        return new DerivationMethod(value);
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

        final String name = description.toLowerCase(Locale.ROOT);
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Value existingEnumByValue = VALUE_REGISTRY.get(value);
        Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(name);
        if (existingEnumByValue != null || existingEnumByDescription != null) {
            return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.putIfAbsent(value, custom);
        DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
        EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
        return custom;
    }

    /**
     * Look up by name.
     */
    public static Value fromName(String name) {
        final String nameLowerCase = name.toLowerCase(Locale.ROOT);
        KmipSpec spec = KmipContext.getSpec();
        Value v = DESCRIPTION_REGISTRY.get(nameLowerCase);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No DerivationMethod value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No DerivationMethod value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for DerivationMethod is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Derivation Methods.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        PBKDF2(0x00000001, "PBKDF2", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HASH(0x00000002, "HASH", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC(0x00000003, "HMAC", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ENCRYPT(0x00000004, "Encrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NIST800_108_C(0x00000005, "Nist800108C", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NIST800_108_F(0x00000006, "Nist800108F", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NIST800_108_DPI(0x00000007, "Nist800108Dpi", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYMMETRIC_KEY(0x00000008, "AsymmetricKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        AWS_SIGNATURE_VERSION_4(0x00000009, "AwsSignatureVersion4", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HKDF(0x0000000A, "Hkdf", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public DerivationMethod inst() {
            return DerivationMethod.of(this);
        }
    }

    /**
     * An interface representing a Derivation Method value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<DerivationMethod> {
    }

    /**
     * Represents a custom, vendor-specific Derivation Method.
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
        public DerivationMethod inst() {
            return DerivationMethod.of(this);
        }
    }
}
