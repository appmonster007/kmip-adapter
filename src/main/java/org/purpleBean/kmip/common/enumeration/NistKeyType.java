package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP NistKeyType enumeration.
 */
@Data
@Builder
public class NistKeyType implements KmipEnumeration {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.NIST_KEY_TYPE);
    public static final EncodingType encodingType = EncodingType.ENUMERATION;
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    @NonNull
    private final Value value;

    public NistKeyType(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for NistKeyType is not supported for KMIP spec %s", value.getDescription(), spec)
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
                        String.format("No NistKeyType value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No NistKeyType value found for %d in KMIP spec %s", value, spec)
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
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return value.isSupportedFor(spec);
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        PRIVATE_SIGNATURE_KEY(0x00000001, "PrivateSignatureKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_SIGNATURE_VERIFICATION_KEY(0x00000002, "PublicSignatureVerificationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_AUTHENTICATION_KEY(0x00000003, "SymmetricAuthenticationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_AUTHENTICATION_KEY(0x00000004, "PrivateAuthenticationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_AUTHENTICATION_KEY(0x00000005, "PublicAuthenticationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_DATA_ENCRYPTION_KEY(0x00000006, "SymmetricDataEncryptionKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_KEY_WRAPPING_KEY(0x00000007, "SymmetricKeyWrappingKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_RANDOM_NUMBER_GENERATION_KEY(0x00000008, "SymmetricRandomNumberGenerationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_MASTER_KEY(0x00000009, "SymmetricMasterKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_KEY_TRANSPORT_KEY(0x0000000A, "PrivateKeyTransportKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_KEY_TRANSPORT_KEY(0x0000000B, "PublicKeyTransportKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_KEY_AGREEMENT_KEY(0x0000000C, "SymmetricKeyAgreementKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_STATIC_KEY_AGREEMENT_KEY(0x0000000D, "PrivateStaticKeyAgreementKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_STATIC_KEY_AGREEMENT_KEY(0x0000000E, "PublicStaticKeyAgreementKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_EPHEMERAL_KEY_AGREEMENT_KEY(0x0000000F, "PrivateEphemeralKeyAgreementKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_EPHEMERAL_KEY_AGREEMENT_KEY(0x00000010, "PublicEphemeralKeyAgreementKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_AUTHORIZATION_KEY(0x00000011, "SymmetricAuthorizationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_AUTHORIZATION_KEY(0x00000012, "PrivateAuthorizationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_AUTHORIZATION_KEY(0x00000013, "PublicAuthorizationKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
