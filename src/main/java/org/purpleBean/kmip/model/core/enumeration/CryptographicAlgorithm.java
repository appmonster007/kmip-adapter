package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * cryptographic algorithm of a key or other cryptographic object.
 * <p>
 * This enumeration is a critical attribute for any cryptographic object, as it defines
 * the mathematical operations that the object can be used for. It includes a wide range
 * of symmetric and asymmetric algorithms.
 *
 * @see KmipEnumeration
 * @see KmipAttribute
 */
@Data
@Builder(toBuilder = true)
public class CryptographicAlgorithm implements KmipEnumeration, KmipAttribute {
    public static final KmipTag kmipTag = KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CryptographicAlgorithm.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CryptographicAlgorithm.class, CryptographicAlgorithm::of);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private CryptographicAlgorithm(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static CryptographicAlgorithm of(@NonNull Value value) {
        return CryptographicAlgorithm.builder().value(value).build();
    }

    public static CryptographicAlgorithm of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (!attributeName.getValue().equals(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()))) {
            throw new IllegalArgumentException("Invalid attribute name");
        }
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueEnumeration enumeration)) {
            throw new IllegalArgumentException("Invalid encoding type");
        }
        CryptographicAlgorithm.Value v = CryptographicAlgorithm.fromValue(enumeration.getValue());
        return CryptographicAlgorithm.builder().value(v).build();
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
                        String.format("No CryptographicAlgorithm value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No CryptographicAlgorithm value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for CryptographicAlgorithm is not supported for KMIP spec %s", value.getDescription(), spec)
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

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValueEnumeration.of(value.getValue());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }

    @Override
    public boolean isAlwaysPresent() {
        return true;
    }

    @Override
    public boolean isServerInitializable() {
        return true;
    }

    @Override
    public boolean isClientInitializable() {
        return false;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return false;
    }

    @Override
    public boolean isClientModifiable(State state) {
        return false;
    }

    @Override
    public boolean isClientDeletable() {
        return false;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return false;
    }

    public int getValue() {
        return value.getValue();
    }

    /**
     * The standard enumeration of Cryptographic Algorithms.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        // TODO: fix all enum descriptions
        DES(0x00000001, "DES", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TRIPLE_DES(0x00000002, "TRIPLEDES", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        AES(0x00000003, "AES", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RSA(0x00000004, "RSA", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DSA(0x00000005, "DSA", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDSA(0x00000006, "ECDSA", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA1(0x00000007, "HmacSha1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA224(0x00000008, "HmacSha224", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA256(0x00000009, "HmacSha256", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA384(0x0000000A, "HmacSha384", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA512(0x0000000B, "HmacSha512", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_MD5(0x0000000C, "HmacMd5", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DH(0x0000000D, "Dh", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECDH(0x0000000E, "Ecdh", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ECMQV(0x0000000F, "Ecmqv", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        BLOWFISH(0x00000010, "Blowfish", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CAMELLIA(0x00000011, "Camellia", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CAST5(0x00000012, "Cast5", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        IDEA(0x00000013, "Idea", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MARS(0x00000014, "Mars", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RC2(0x00000015, "Rc2", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RC4(0x00000016, "Rc4", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RC5(0x00000017, "Rc5", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SKIPJACK(0x00000018, "Skipjack", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TWOFISH(0x00000019, "Twofish", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        EC(0x0000001A, "Ec", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ONE_TIME_PAD(0x0000001B, "OneTimePad", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CHACHA20(0x0000001C, "ChaCha20", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        POLY1305(0x0000001D, "Poly1305", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CHACHA20_POLY1305(0x0000001E, "ChaCha20Poly1305", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_224(0x0000001F, "Sha3224", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_256(0x00000020, "Sha3256", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_384(0x00000021, "Sha3384", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHA3_512(0x00000022, "Sha3512", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA3_224(0x00000023, "HmacSha3224", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA3_256(0x00000024, "HmacSha3256", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA3_384(0x00000025, "HmacSha3384", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HMAC_SHA3_512(0x00000026, "HmacSha3512", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHAKE_128(0x00000027, "Shake128", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHAKE_256(0x00000028, "Shake256", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ARIA(0x00000029, "Aria", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SEED(0x0000002A, "Seed", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SM2(0x0000002B, "Sm2", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SM3(0x0000002C, "Sm3", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SM4(0x0000002D, "Sm4", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GOST_R_34_10_2012(0x0000002E, "GostR34102012", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GOST_R_34_11_2012(0x0000002F, "GostR34112012", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GOST_R_34_13_2015(0x00000030, "GostR34132015", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GOST_28147_89(0x00000031, "Gost2814789", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        XMSS(0x00000032, "Xmss", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SPHINCS_256(0x00000033, "Sphincs256", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MCELIECE(0x00000034, "McEliece", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MCELIECE_6960119(0x00000035, "McEliece6960119", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MCELIECE_8192128(0x00000036, "McEliece8192128", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ED25519(0x00000037, "Ed25519", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ED448(0x00000038, "Ed448", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ML_KEM_512(0x00000039, "MLKEM512", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ML_KEM_768(0x0000003A, "MLKEM768", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ML_KEM_1024(0x0000003B, "MLKEM1024", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ML_DSA_44(0x0000003C, "MLDSA44", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ML_DSA_65(0x0000003D, "MLDSA65", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ML_DSA_87(0x0000003E, "MLDSA87", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHA2_128S(0x0000003F, "SLHDSASHA2128s", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHA2_128F(0x00000040, "SLHDSASHA2128f", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHA2_192S(0x00000041, "SLHDSASHA2192s", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHA2_192F(0x00000042, "SLHDSASHA2192f", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHA2_256S(0x00000043, "SLHDSASHA2256s", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHA2_256F(0x00000044, "SLHDSASHA2256f", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHAKE_128S(0x00000045, "SLHDSASHAKE128s", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHAKE_128F(0x00000046, "SLHDSASHAKE128f", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHAKE_192S(0x00000047, "SLHDSASHAKE192s", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHAKE_192F(0x00000048, "SLHDSASHAKE192f", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHAKE_256S(0x00000049, "SLHDSASHAKE256s", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SLH_DSA_SHAKE_256F(0x0000004A, "SLHDSASHAKE256f", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
        public CryptographicAlgorithm inst() {
            return CryptographicAlgorithm.of(this);
        }
    }

    /**
     * An interface representing a Cryptographic Algorithm value, which can be either a standard
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
         * @return a new instance of the {@link CryptographicAlgorithm} with the current value.
         */
        CryptographicAlgorithm inst();
    }

    /**
     * Represents a custom, vendor-specific Cryptographic Algorithm.
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
        public CryptographicAlgorithm inst() {
            return CryptographicAlgorithm.of(this);
        }
    }
}
