package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP QueryFunction enumeration.
 */
@Data
@Builder
public class QueryFunction implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.QUERY_FUNCTION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public QueryFunction(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for QueryFunction is not supported for KMIP spec %s", value.getDescription(), spec)
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
                        String.format("No QueryFunction value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No QueryFunction value found for %d in KMIP spec %s", value, spec)
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
        QUERY_OPERATIONS(0x00000001, "QueryOperations", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_OBJECTS(0x00000002, "QueryObjects", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_SERVER_INFORMATION(0x00000003, "QueryServerInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_APPLICATION_NAMESPACES(0x00000004, "QueryApplicationNamespaces", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_EXTENSION_LIST(0x00000005, "QueryExtensionList", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_EXTENSION_MAP(0x00000006, "QueryExtensionMap", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_ATTESTATION_TYPES(0x00000007, "QueryAttestationTypes", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_RNGS(0x00000008, "QueryRngs", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_VALIDATIONS(0x00000009, "QueryValidations", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_PROFILES(0x0000000A, "QueryProfiles", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_CAPABILITIES(0x0000000B, "QueryCapabilities", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_CLIENT_REGISTRATION_METHODS(0x0000000C, "QueryClientRegistrationMethods", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_DEFAULTS_INFORMATION(0x0000000D, "QueryDefaultsInformation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_STORAGE_PROTECTION_MASKS(0x0000000E, "QueryStorageProtectionMasks", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_CREDENTIAL_INFORMATION(0x0000000F, "QueryCredentialInformation", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
