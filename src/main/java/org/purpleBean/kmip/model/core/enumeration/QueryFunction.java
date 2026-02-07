package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * type of information being requested in a {@code Query} operation.
 * <p>
 * The {@code Query} operation is a general-purpose mechanism for a client to discover
 * the capabilities and status of a KMIP server. This enumeration defines the different
 * categories of information that can be queried.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code QUERY_OPERATIONS}: Request a list of the operations supported by the server.</li>
 *   <li>{@code QUERY_OBJECTS}: Request a list of the object types supported by the server.</li>
 *   <li>{@code QUERY_SERVER_INFORMATION}: Request general information about the server.</li>
 *   <li>{@code QUERY_APPLICATION_NAMESPACES}: Request a list of the application namespaces supported by the server.</li>
 *   <li>{@code QUERY_EXTENSION_LIST}: Request a list of the extensions supported by the server.</li>
 *   <li>{@code QUERY_EXTENSION_MAP}: Request a map of the extensions supported by the server.</li>
 *   <li>{@code QUERY_ATTESTATION_TYPES}: Request a list of the attestation types supported by the server.</li>
 *   <li>{@code QUERY_RNGS}: Request information about the Random Number Generators (RNGs) supported by the server.</li>
 *   <li>{@code QUERY_VALIDATIONS}: Request information about the validation mechanisms supported by the server.</li>
 *   <li>{@code QUERY_PROFILES}: Request a list of the profiles supported by the server.</li>
 *   <li>{@code QUERY_CAPABILITIES}: Request a comprehensive list of the server's capabilities.</li>
 *   <li>{@code QUERY_CLIENT_REGISTRATION_METHODS}: Request a list of the client registration methods supported by the server.</li>
 *   <li>{@code QUERY_DEFAULTS_INFORMATION}: Request information about the server's default attribute values.</li>
 *   <li>{@code QUERY_STORAGE_PROTECTION_MASKS}: Request information about the storage protection masks supported by the server.</li>
 *   <li>{@code QUERY_CREDENTIAL_INFORMATION}: Request information about the credential types supported by the server.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see org.purpleBean.kmip.operation.Query
 */
@Data
@Builder(toBuilder = true)
public class QueryFunction implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.QUERY_FUNCTION.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, QueryFunction.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), QueryFunction::fromName, QueryFunction::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private QueryFunction(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static QueryFunction of(@NonNull Value value) {
        return new QueryFunction(value);
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
                        String.format("No QueryFunction value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No QueryFunction value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for QueryFunction is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Query Functions.
     */
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public QueryFunction inst() {
            return QueryFunction.of(this);
        }
    }

    /**
     * An interface representing a Query Function value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<QueryFunction> {
    }

    /**
     * Represents a custom, vendor-specific Query Function.
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
        public QueryFunction inst() {
            return QueryFunction.of(this);
        }
    }
}
