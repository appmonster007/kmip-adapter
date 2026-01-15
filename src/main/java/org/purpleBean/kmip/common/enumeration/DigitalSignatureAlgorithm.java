package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * algorithm to be used for digital signatures.
 * <p>
 * This enumeration lists various combinations of hashing algorithms and public-key
 * algorithms that can be used to create and verify digital signatures.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code MD2_WITH_RSA_ENCRYPTION}, {@code MD5_WITH_RSA_ENCRYPTION}: Older RSA-based signature schemes.</li>
 *   <li>{@code SHA_1_WITH_RSA_ENCRYPTION}, {@code SHA_224_WITH_RSA_ENCRYPTION}, etc.: RSA-based signatures with various SHA hashing algorithms.</li>
 *   <li>{@code RSASSA_PSS}: The RSA Signature Scheme with Appendix - Probabilistic Signature Scheme.</li>
 *   <li>{@code DSA_WITH_SHA_1}, {@code DSA_WITH_SHA224}, etc.: DSA-based signatures with various SHA hashing algorithms.</li>
 *   <li>{@code ECDSA_WITH_SHA_1}, {@code ECDSA_WITH_SHA224}, etc.: ECDSA-based signatures with various SHA hashing algorithms.</li>
 *   <li>{@code SHA3_256_WITH_RSA_ENCRYPTION}, etc.: RSA-based signatures with SHA-3 hashing algorithms.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see CryptographicAlgorithm
 * @see HashingAlgorithm
 */
@Data
@Builder(toBuilder = true)
public class DigitalSignatureAlgorithm implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DigitalSignatureAlgorithm.class);
        }
    }

    @NonNull
    private final Value value;

    public DigitalSignatureAlgorithm(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for DigitalSignatureAlgorithm is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    public static DigitalSignatureAlgorithm of(@NonNull Value value) {
        return new DigitalSignatureAlgorithm(value);
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
                        String.format("No DigitalSignatureAlgorithm value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No DigitalSignatureAlgorithm value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Get registered values.
     */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
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
     * The standard enumeration of Digital Signature Algorithms.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        MD2_WITH_RSA_ENCRYPTION(0x00000001, "Md2WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MD5_WITH_RSA_ENCRYPTION(0x00000002, "Md5WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA_1_WITH_RSA_ENCRYPTION(0x00000003, "Sha1WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA_224_WITH_RSA_ENCRYPTION(0x00000004, "Sha224WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA_256_WITH_RSA_ENCRYPTION(0x00000005, "Sha256WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA_384_WITH_RSA_ENCRYPTION(0x00000006, "Sha384WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA_512_WITH_RSA_ENCRYPTION(0x00000007, "Sha512WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RSASSA_PSS(0x00000008, "RsassaPss", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DSA_WITH_SHA_1(0x00000009, "DsaWithSha1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DSA_WITH_SHA224(0x0000000A, "DsaWithSha224", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DSA_WITH_SHA256(0x0000000B, "DsaWithSha256", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDSA_WITH_SHA_1(0x0000000C, "EcdsaWithSha1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDSA_WITH_SHA224(0x0000000D, "EcdsaWithSha224", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDSA_WITH_SHA256(0x0000000E, "EcdsaWithSha256", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDSA_WITH_SHA384(0x0000000F, "EcdsaWithSha384", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDSA_WITH_SHA512(0x00000010, "EcdsaWithSha512", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_256_WITH_RSA_ENCRYPTION(0x00000011, "Sha3256WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_384_WITH_RSA_ENCRYPTION(0x00000012, "Sha3384WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_512_WITH_RSA_ENCRYPTION(0x00000013, "Sha3512WithRsaEncryption", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public DigitalSignatureAlgorithm inst() {
            return DigitalSignatureAlgorithm.of(this);
        }
    }

    /**
     * An interface representing a Digital Signature Algorithm value, which can be either a standard
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
         * @return a new instance of the {@link DigitalSignatureAlgorithm} with the current value.
         */
        DigitalSignatureAlgorithm inst();
    }

    /**
     * Represents a custom, vendor-specific Digital Signature Algorithm.
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
        public DigitalSignatureAlgorithm inst() {
            return DigitalSignatureAlgorithm.of(this);
        }
    }
}