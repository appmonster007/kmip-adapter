package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP Operation enumeration.
 */
@Data
@Builder
public class Operation implements KmipEnumeration {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OPERATION);
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

    public Operation(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for Operation is not supported for KMIP spec %s", value.getDescription(), spec)
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
                        String.format("No Operation value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No Operation value found for %d in KMIP spec %s", value, spec)
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
        CREATE(0x00000001, "Create", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_KEY_PAIR(0x00000002, "CreateKeyPair", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REGISTER(0x00000003, "Register", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_KEY(0x00000004, "ReKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DERIVE_KEY(0x00000005, "DeriveKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFY(0x00000006, "Certify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_CERTIFY(0x00000007, "ReCertify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        LOCATE(0x00000008, "Locate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CHECK(0x00000009, "Check", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        GET(0x0000000A, "Get", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        GET_ATTRIBUTES(0x0000000B, "GetAttributes", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        GET_ATTRIBUTE_LIST(0x0000000C, "GetAttributeList", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ADD_ATTRIBUTE(0x0000000D, "AddAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MODIFY_ATTRIBUTE(0x0000000E, "ModifyAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DELETE_ATTRIBUTE(0x0000000F, "DeleteAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OBTAIN_LEASE(0x00000010, "ObtainLease", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        GET_USAGE_ALLOCATION(0x00000011, "GetUsageAllocation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ACTIVATE(0x00000012, "Activate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REVOKE(0x00000013, "Revoke", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DESTROY(0x00000014, "Destroy", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ARCHIVE(0x00000015, "Archive", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RECOVER(0x00000016, "Recover", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATE(0x00000017, "Validate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY(0x00000018, "Query", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CANCEL(0x00000019, "Cancel", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        POLL(0x0000001A, "Poll", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NOTIFY(0x0000001B, "Notify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PUT(0x0000001C, "Put", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_KEY_KEY_PAIR(0x0000001D, "ReKeyKeyPair", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DISCOVER_VERSIONS(0x0000001E, "DiscoverVersions", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ENCRYPT(0x0000001F, "Encrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DECRYPT(0x00000020, "Decrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SIGN(0x00000021, "Sign", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SIGNATURE_VERIFY(0x00000022, "SignatureVerify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAC(0x00000023, "Mac", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAC_VERIFY(0x00000024, "MacVerify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_RETRIEVE(0x00000025, "RngRetrieve", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_SEED(0x00000026, "RngSeed", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HASH(0x00000027, "Hash", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_SPLIT_KEY(0x00000028, "CreateSplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        JOIN_SPLIT_KEY(0x00000029, "JoinSplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        IMPORT(0x0000002A, "Import", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EXPORT(0x0000002B, "Export", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LOG(0x0000002C, "Log", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LOGIN(0x0000002D, "Login", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LOGOUT(0x0000002E, "Logout", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DELEGATED_LOGIN(0x0000002F, "DelegatedLogin", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ADJUST_ATTRIBUTE(0x00000030, "AdjustAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SET_ATTRIBUTE(0x00000031, "SetAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SET_ENDPOINT_ROLE(0x00000032, "SetEndpointRole", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11(0x00000033, "Pkcs11", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTEROP(0x00000034, "Interop", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RE_PROVISION(0x00000035, "ReProvision", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SET_DEFAULTS(0x00000036, "SetDefaults", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SET_CONSTRAINTS(0x00000037, "SetConstraints", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        GET_CONSTRAINTS(0x00000038, "GetConstraints", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_ASYNCHRONOUS_REQUESTS(0x00000039, "QueryAsynchronousRequests", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROCESS(0x0000003A, "Process", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PING(0x0000003B, "Ping", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CREATE_GROUP(0x0000003C, "CreateGroup", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OBLITERATE(0x0000003D, "Obliterate", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREATE_USER(0x0000003E, "CreateUser", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREATE_CREDENTIAL(0x0000003F, "CreateCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DEACTIVATE(0x00000040, "Deactivate", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
