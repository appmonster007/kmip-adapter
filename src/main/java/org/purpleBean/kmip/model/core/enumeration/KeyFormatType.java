package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * format of a cryptographic key.
 * <p>
 * This enumeration is used to indicate the encoding or structure of a key's material,
 * allowing the server and client to correctly interpret the key data.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code RAW}: The raw bytes of the key.</li>
 *   <li>{@code OPAQUE}: An opaque, server-specific representation of the key.</li>
 *   <li>{@code PKCS_1}: The PKCS#1 format, typically for RSA keys.</li>
 *   <li>{@code PKCS_8}: The PKCS#8 format for private keys.</li>
 *   <li>{@code X_509}: The X.509 format for public key certificates.</li>
 *   <li>{@code EC_PRIVATE_KEY}: An elliptic curve private key format.</li>
 *   <li>{@code TRANSPARENT_SYMMETRIC_KEY}: A transparent symmetric key format.</li>
 *   <li>{@code TRANSPARENT_DSA_PRIVATE_KEY}, {@code TRANSPARENT_DSA_PUBLIC_KEY}: Transparent DSA key formats.</li>
 *   <li>{@code TRANSPARENT_RSA_PRIVATE_KEY}, {@code TRANSPARENT_RSA_PUBLIC_KEY}: Transparent RSA key formats.</li>
 *   <li>{@code TRANSPARENT_DH_PRIVATE_KEY}, {@code TRANSPARENT_DH_PUBLIC_KEY}: Transparent Diffie-Hellman key formats.</li>
 *   <li>{@code TRANSPARENT_ECDSA_PRIVATE_KEY}, {@code TRANSPARENT_ECDSA_PUBLIC_KEY}: Transparent ECDSA key formats.</li>
 *   <li>{@code TRANSPARENT_ECDH_PRIVATE_KEY}, {@code TRANSPARENT_ECDH_PUBLIC_KEY}: Transparent ECDH key formats.</li>
 *   <li>{@code TRANSPARENT_ECMQV_PRIVATE_KEY}, {@code TRANSPARENT_ECMQV_PUBLIC_KEY}: Transparent ECMQV key formats.</li>
 *   <li>{@code TRANSPARENT_EC_PRIVATE_KEY}, {@code TRANSPARENT_EC_PUBLIC_KEY}: Generic transparent elliptic curve key formats.</li>
 *   <li>{@code PKCS_12}: The PKCS#12 format for storing cryptographic objects.</li>
 *   <li>{@code PKCS_10}: The PKCS#10 format for certificate signing requests.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see KeyMaterial
 */
@Data
@Builder(toBuilder = true)
public class KeyFormatType implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.KEY_FORMAT_TYPE.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyFormatType.class);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private KeyFormatType(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static KeyFormatType of(@NonNull Value value) {
        return KeyFormatType.builder().value(value).build();
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
                        String.format("No KeyFormatType value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No KeyFormatType value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for KeyFormatType is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Key Format Types.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        RAW(0x00000001, "Raw", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPAQUE(0x00000002, "Opaque", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_1(0x00000003, "Pkcs1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_8(0x00000004, "Pkcs8", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        X_509(0x00000005, "X509", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        EC_PRIVATE_KEY(0x00000006, "EcPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_SYMMETRIC_KEY(0x00000007, "TransparentSymmetricKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_DSA_PRIVATE_KEY(0x00000008, "TransparentDsaPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_DSA_PUBLIC_KEY(0x00000009, "TransparentDsaPublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_RSA_PRIVATE_KEY(0x0000000A, "TransparentRsaPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_RSA_PUBLIC_KEY(0x0000000B, "TransparentRsaPublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_DH_PRIVATE_KEY(0x0000000C, "TransparentDhPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_DH_PUBLIC_KEY(0x0000000D, "TransparentDhPublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_ECDSA_PRIVATE_KEY(0x0000000E, "TransparentEcdsaPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        TRANSPARENT_ECDSA_PUBLIC_KEY(0x0000000F, "TransparentEcdsaPublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        TRANSPARENT_ECDH_PRIVATE_KEY(0x00000010, "TransparentEcdhPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        TRANSPARENT_ECDH_PUBLIC_KEY(0x00000011, "TransparentEcdhPublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        TRANSPARENT_ECMQV_PRIVATE_KEY(0x00000012, "TransparentEcmqvPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        TRANSPARENT_ECMQV_PUBLIC_KEY(0x00000013, "TransparentEcmqvPublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        TRANSPARENT_EC_PRIVATE_KEY(0x00000014, "TransparentEcPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TRANSPARENT_EC_PUBLIC_KEY(0x00000015, "TransparentEcPublicKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_12(0x00000016, "Pkcs12", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_10(0x00000017, "Pkcs10", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public KeyFormatType inst() {
            return KeyFormatType.of(this);
        }
    }

    /**
     * An interface representing a Key Format Type value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value {
        /**
         * @return the integer value of the enumeration.
         */
        int getValue();

        /**
         * @return the description of the enumeration.
         */
        String getDescription();

        /**
         * @return true if the enumeration is supported in the current KMIP context, false otherwise.
         */
        boolean isSupported();

        /**
         * @return true if the enumeration is a custom extension, false otherwise.
         */
        boolean isCustom();

        /**
         * @return a new instance of the {@link KeyFormatType} with the current value.
         */
        KeyFormatType inst();
    }

    /**
     * Represents a custom, vendor-specific Key Format Type.
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
        public KeyFormatType inst() {
            return KeyFormatType.of(this);
        }
    }
}
