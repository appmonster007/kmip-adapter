package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP UniqueIdentifier enumeration.
 */
@Data
@Builder
public class UniqueIdentifier implements KmipEnumeration {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER);
    public static final EncodingType encodingType = EncodingType.ENUMERATION;
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, UniqueIdentifier.class);
        }
    }

    @NonNull
    private final Value value;

    public UniqueIdentifier(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for UniqueIdentifier is not supported for KMIP spec %s", value.getDescription(), spec)
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
    public static Value fromName(String name) {
        KmipSpec spec = KmipContext.getSpec();
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No UniqueIdentifier value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No UniqueIdentifier value found for %d in KMIP spec %s", value, spec)
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

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        ID_PLACEHOLDER(0x00000001, "IdPlaceholder", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFY(0x00000002, "Certify", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE(0x00000003, "Create", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_KEY_PAIR(0x00000004, "CreateKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_KEY_PAIR_PRIVATE_KEY(0x00000005, "CreateKeyPairPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_KEY_PAIR_PUBLIC_KEY(0x00000006, "CreateKeyPairPublicKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_SPLIT_KEY(0x00000007, "CreateSplitKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DERIVE_KEY(0x00000008, "DeriveKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        IMPORT(0x00000009, "Import", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        JOIN_SPLIT_KEY(0x0000000A, "JoinSplitKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LOCATE(0x0000000B, "Locate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        REGISTER(0x0000000C, "Register", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_KEY(0x0000000D, "ReKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_CERTIFY(0x0000000E, "ReCertify", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_KEY_KEY_PAIR(0x0000000F, "ReKeyKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_KEY_KEY_PAIR_PRIVATE_KEY(0x00000010, "ReKeyKeyPairPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_KEY_KEY_PAIR_PUBLIC_KEY(0x00000011, "ReKeyKeyPairPublicKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_PROVISION(0x00000012, "ReProvision", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREATE_USER(0x00000013, "CreateUser", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREATE_GROUP(0x00000014, "CreateGroup", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREATE_CREDENTIAL(0x00000015, "CreateCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
    }

    // ----- Value hierarchy -----
    public interface Value {
        int getValue();

        String getDescription();

        boolean isSupported();

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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }
}
