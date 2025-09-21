package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ResultReason enumeration.
 */
@Data
@Builder
public class ResultReason implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.RESULT_REASON);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public ResultReason(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for ResultReason is not supported for KMIP spec %s", value.getDescription(), spec)
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
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.put(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
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
                        String.format("No ResultReason value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No ResultReason value found for %d in KMIP spec %s", value, spec)
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
        ITEM_NOT_FOUND(0x00000001, "ItemNotFound", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESPONSE_TOO_LARGE(0x00000002, "ResponseTooLarge", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        AUTHENTICATION_NOT_SUCCESSFUL(0x00000003, "AuthenticationNotSuccessful", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_MESSAGE(0x00000004, "InvalidMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPERATION_NOT_SUPPORTED(0x00000005, "OperationNotSupported", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MISSING_DATA(0x00000006, "MissingData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_FIELD(0x00000007, "InvalidField", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        FEATURE_NOT_SUPPORTED(0x00000008, "FeatureNotSupported", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPERATION_CANCELED_BY_REQUESTER(0x00000009, "OperationCanceledByRequester", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRYPTOGRAPHIC_FAILURE(0x0000000A, "CryptographicFailure", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ILLEGAL_OPERATION(0x0000000B, "IllegalOperation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PERMISSION_DENIED(0x0000000C, "PermissionDenied", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_ARCHIVED(0x0000000D, "ObjectArchived", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INDEX_OUT_OF_BOUNDS(0x0000000E, "IndexOutOfBounds", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        APPLICATION_NAMESPACE_NOT_SUPPORTED(0x0000000F, "ApplicationNamespaceNotSupported", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_FORMAT_TYPE_NOT_SUPPORTED(0x00000010, "KeyFormatTypeNotSupported", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_COMPRESSION_TYPE_NOT_SUPPORTED(0x00000011, "KeyCompressionTypeNotSupported", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ENCODING_OPTION_ERROR(0x00000012, "EncodingOptionError", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_VALUE_NOT_PRESENT(0x00000013, "KeyValueNotPresent", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_REQUIRED(0x00000014, "AttestationRequired", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_FAILED(0x00000015, "AttestationFailed", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SENSITIVE(0x00000016, "Sensitive", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NOT_EXTRACTABLE(0x00000017, "NotExtractable", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_ALREADY_EXISTS(0x00000018, "ObjectAlreadyExists", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_TICKET(0x00000019, "InvalidTicket", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        USAGE_LIMIT_EXCEEDED(0x0000001A, "UsageLimitExceeded", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NUMERIC_RANGE(0x0000001B, "NumericRange", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_DATA_TYPE(0x0000001C, "InvalidDataType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        READ_ONLY_ATTRIBUTE(0x0000001D, "ReadOnlyAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MULTI_VALUED_ATTRIBUTE(0x0000001E, "MultiValuedAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNSUPPORTED_ATTRIBUTE(0x0000001F, "UnsupportedAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_INSTANCE_NOT_FOUND(0x00000020, "AttributeInstanceNotFound", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_NOT_FOUND(0x00000021, "AttributeNotFound", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_READ_ONLY(0x00000022, "AttributeReadOnly", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_SINGLE_VALUED(0x00000023, "AttributeSingleValued", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BAD_CRYPTOGRAPHIC_PARAMETERS(0x00000024, "BadCryptographicParameters", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BAD_PASSWORD(0x00000025, "BadPassword", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CODEC_ERROR(0x00000026, "CodecError", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ILLEGAL_OBJECT_TYPE(0x00000028, "IllegalObjectType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INCOMPATIBLE_CRYPTOGRAPHIC_USAGE_MASK(0x00000029, "IncompatibleCryptographicUsageMask", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTERNAL_SERVER_ERROR(0x0000002A, "InternalServerError", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_ASYNCHRONOUS_CORRELATION_VALUE(0x0000002B, "InvalidAsynchronousCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_ATTRIBUTE(0x0000002C, "InvalidAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_ATTRIBUTE_VALUE(0x0000002D, "InvalidAttributeValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_CORRELATION_VALUE(0x0000002E, "InvalidCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_CSR(0x0000002F, "InvalidCsr", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_OBJECT_TYPE(0x00000030, "InvalidObjectType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_WRAP_TYPE_NOT_SUPPORTED(0x00000032, "KeyWrapTypeNotSupported", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MISSING_INITIALIZATION_VECTOR(0x00000034, "MissingInitializationVector", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NON_UNIQUE_NAME_ATTRIBUTE(0x00000035, "NonUniqueNameAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_DESTROYED(0x00000036, "ObjectDestroyed", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_NOT_FOUND(0x00000037, "ObjectNotFound", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_LIMIT_EXCEEDED(0x0000003A, "ServerLimitExceeded", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNKNOWN_ENUMERATION(0x0000003B, "UnknownEnumeration", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNKNOWN_MESSAGE_EXTENSION(0x0000003C, "UnknownMessageExtension", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNKNOWN_TAG(0x0000003D, "UnknownTag", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNSUPPORTED_CRYPTOGRAPHIC_PARAMETERS(0x0000003E, "UnsupportedCryptographicParameters", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNSUPPORTED_PROTOCOL_VERSION(0x0000003F, "UnsupportedProtocolVersion", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        WRAPPING_OBJECT_ARCHIVED(0x00000040, "WrappingObjectArchived", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        WRAPPING_OBJECT_DESTROYED(0x00000041, "WrappingObjectDestroyed", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        WRAPPING_OBJECT_NOT_FOUND(0x00000042, "WrappingObjectNotFound", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        WRONG_KEY_LIFECYCLE_STATE(0x00000043, "WrongKeyLifecycleState", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTECTION_STORAGE_UNAVAILABLE(0x00000044, "ProtectionStorageUnavailable", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS11_CODEC_ERROR(0x00000045, "Pkcs11CodecError", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS11_INVALID_FUNCTION(0x00000046, "Pkcs11InvalidFunction", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS11_INVALID_INTERFACE(0x00000047, "Pkcs11InvalidInterface", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_PROTECTION_STORAGE_UNAVAILABLE(0x00000048, "PrivateProtectionStorageUnavailable", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_PROTECTION_STORAGE_UNAVAILABLE(0x00000049, "PublicProtectionStorageUnavailable", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNKNOWN_OBJECT_GROUP(0x0000004A, "UnknownObjectGroup", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        CONSTRAINT_VIOLATION(0x0000004B, "ConstraintViolation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DUPLICATE_PROCESS_REQUEST(0x0000004C, "DuplicateProcessRequest", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CIRCULAR_LINK_ERROR(0x0000004D, "CircularLinkError", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        GENERAL_FAILURE(0x00000100, "GeneralFailure", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NOT_AUTHORised(0x00000039, "NotAuthorised", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
