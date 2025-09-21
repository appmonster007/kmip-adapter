package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP KeyFormatType enumeration.
 */
@Data
@Builder
public class KeyFormatType implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_FORMAT_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public KeyFormatType(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for KeyFormatType is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
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
    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No KeyFormatType value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Look up by value.
     */
    public static Value fromValue(KmipSpec spec, int value) {
        // Check standard values first
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
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

    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return value.isSupportedFor(spec);
    }

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
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    // ----- Value hierarchy -----
    public interface Value {
        int getValue();

        String getDescription();

        boolean isSupportedFor(KmipSpec spec);

        boolean isCustom();
    }

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
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }
}
