package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ProfileName enumeration.
 */
@Data
@Builder
public class ProfileName implements KmipEnumeration {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROFILE_NAME);
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProfileName.class);
        }
    }

    @NonNull
    private final Value value;

    public ProfileName(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for ProfileName is not supported for KMIP spec %s", value.getDescription(), spec)
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
                        String.format("No ProfileName value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No ProfileName value found for %d in KMIP spec %s", value, spec)
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
        COMPLETE_SERVER_BASIC(0x00000104, "CompleteServerBasic", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        COMPLETE_SERVER_TLS_V1_2(0x00000105, "CompleteServerTlsV12", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TAPE_LIBRARY_CLIENT(0x00000106, "TapeLibraryClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TAPE_LIBRARY_SERVER(0x00000107, "TapeLibraryServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_KEY_LIFECYCLE_CLIENT(0x00000108, "SymmetricKeyLifecycleClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_KEY_LIFECYCLE_SERVER(0x00000109, "SymmetricKeyLifecycleServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYMMETRIC_KEY_LIFECYCLE_CLIENT(0x0000010A, "AsymmetricKeyLifecycleClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYMMETRIC_KEY_LIFECYCLE_SERVER(0x0000010B, "AsymmetricKeyLifecycleServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BASIC_CRYPTOGRAPHIC_CLIENT(0x0000010C, "BasicCryptographicClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BASIC_CRYPTOGRAPHIC_SERVER(0x0000010D, "BasicCryptographicServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ADVANCED_CRYPTOGRAPHIC_CLIENT(0x0000010E, "AdvancedCryptographicClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ADVANCED_CRYPTOGRAPHIC_SERVER(0x0000010F, "AdvancedCryptographicServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_CRYPTOGRAPHIC_CLIENT(0x00000110, "RngCryptographicClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_CRYPTOGRAPHIC_SERVER(0x00000111, "RngCryptographicServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BASIC_SYMMETRIC_KEY_FOUNDRY_CLIENT(0x00000112, "BasicSymmetricKeyFoundryClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTERMEDIATE_SYMMETRIC_KEY_FOUNDRY_CLIENT(0x00000113, "IntermediateSymmetricKeyFoundryClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ADVANCED_SYMMETRIC_KEY_FOUNDRY_CLIENT(0x00000114, "AdvancedSymmetricKeyFoundryClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_KEY_FOUNDRY_SERVER(0x00000115, "SymmetricKeyFoundryServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OPAQUE_MANAGED_OBJECT_STORE_CLIENT(0x00000116, "OpaqueManagedObjectStoreClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OPAQUE_MANAGED_OBJECT_STORE_SERVER(0x00000117, "OpaqueManagedObjectStoreServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        STORAGE_ARRAY_WITH_SELF_ENCRYPTING_DRIVE_CLIENT(0x0000011C, "StorageArrayWithSelfEncryptingDriveClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        STORAGE_ARRAY_WITH_SELF_ENCRYPTING_DRIVE_SERVER(0x0000011D, "StorageArrayWithSelfEncryptingDriveServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HTTPS_CLIENT(0x0000011E, "HttpsClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HTTPS_SERVER(0x0000011F, "HttpsServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        JSON_CLIENT(0x00000120, "JsonClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        JSON_SERVER(0x00000121, "JsonServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        XML_CLIENT(0x00000122, "XmlClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        XML_SERVER(0x00000123, "XmlServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        AES_XTS_CLIENT(0x00000124, "AesXtsClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        AES_XTS_SERVER(0x00000125, "AesXtsServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUANTUM_SAFE_CLIENT(0x00000126, "QuantumSafeClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUANTUM_SAFE_SERVER(0x00000127, "QuantumSafeServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_CLIENT(0x00000128, "Pkcs11Client", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_SERVER(0x00000129, "Pkcs11Server", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BASELINE_CLIENT(0x0000012A, "BaselineClient", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BASELINE_SERVER(0x0000012B, "BaselineServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        COMPLETE_SERVER(0x0000012C, "CompleteServer", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
