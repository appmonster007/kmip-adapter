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
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public UniqueIdentifier(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
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
    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No UniqueIdentifier value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No UniqueIdentifier value found for %d in KMIP spec %s", value, spec)
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
        ID_PLACEHOLDER(0x00000001, "IdPlaceholder", KmipSpec.UnknownVersion,  KmipSpec.V2_1),
        CERTIFY(0x00000002, "Certify", KmipSpec.UnknownVersion,  KmipSpec.V2_1),
        CREATE(0x00000003, "Create", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        CREATE_KEY_PAIR(0x00000004, "CreateKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        CREATE_KEY_PAIR_PRIVATE_KEY(0x00000005, "CreateKeyPairPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        CREATE_KEY_PAIR_PUBLIC_KEY(0x00000006, "CreateKeyPairPublicKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        CREATE_SPLIT_KEY(0x00000007, "CreateSplitKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        DERIVE_KEY(0x00000008, "DeriveKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        IMPORT(0x00000009, "Import", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        JOIN_SPLIT_KEY(0x0000000A, "JoinSplitKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        LOCATE(0x0000000B, "Locate", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        REGISTER(0x0000000C, "Register", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        RE_KEY(0x0000000D, "ReKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        RE_CERTIFY(0x0000000E, "ReCertify", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        RE_KEY_KEY_PAIR(0x0000000F, "ReKeyKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        RE_KEY_KEY_PAIR_PRIVATE_KEY(0x00000010, "ReKeyKeyPairPrivateKey", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        RE_KEY_KEY_PAIR_PUBLIC_KEY(0x00000011, "ReKeyKeyPairPublicKey", KmipSpec.UnknownVersion, KmipSpec.V2_1);

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
