package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * reason for a particular {@link ResultStatus} in a KMIP response.
 * <p>
 * This enumeration provides more granular detail about why an operation succeeded,
 * failed, or was partially successful. It often accompanies a {@link ResultStatus}
 * of {@code OperationFailed} or {@code OperationPartiallySuccessful}.
 *
 * <p><b>Hierarchy of Reasons:</b></p>
 * This class maintains an internal mapping to reflect this a hierarchy of result
 * reasons, where some reasons are more specific instances of a broader parent reason,
 * accessible via {@link #getParentReason(Value)}.
 * For example, {@code ItemNotFound} is a child of {@code GeneralFailure}.
 *
 * @see KmipEnumeration
 * @see ResultStatus
 */
@Data
@Builder(toBuilder = true)
public class ResultReason implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.RESULT_REASON.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<Value, Value> PARENT_REASON_MAP = new HashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description.toLowerCase(Locale.ROOT), s);
        }

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ResultReason.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), ResultReason::fromName, ResultReason::fromValue);
        }

        // Map each enum to its parent, ensuring parent value < child value
        // Level 1 - Direct children of GENERAL_FAILURE (0x00000100)
        PARENT_REASON_MAP.put(Standard.ITEM_NOT_FOUND, Standard.GENERAL_FAILURE);  // 0x00000001
        PARENT_REASON_MAP.put(Standard.RESPONSE_TOO_LARGE, Standard.GENERAL_FAILURE);  // 0x00000002
        PARENT_REASON_MAP.put(Standard.AUTHENTICATION_NOT_SUCCESSFUL, Standard.GENERAL_FAILURE);  // 0x00000003
        PARENT_REASON_MAP.put(Standard.INVALID_MESSAGE, Standard.GENERAL_FAILURE);  // 0x00000004
        PARENT_REASON_MAP.put(Standard.OPERATION_NOT_SUPPORTED, Standard.GENERAL_FAILURE);  // 0x00000005
        PARENT_REASON_MAP.put(Standard.MISSING_DATA, Standard.INVALID_MESSAGE);  // 0x00000006
        PARENT_REASON_MAP.put(Standard.INVALID_FIELD, Standard.INVALID_MESSAGE);  // 0x00000007
        PARENT_REASON_MAP.put(Standard.FEATURE_NOT_SUPPORTED, Standard.OPERATION_NOT_SUPPORTED);  // 0x00000008
        PARENT_REASON_MAP.put(Standard.OPERATION_CANCELED_BY_REQUESTER, Standard.GENERAL_FAILURE);  // 0x00000009
        PARENT_REASON_MAP.put(Standard.CRYPTOGRAPHIC_FAILURE, Standard.GENERAL_FAILURE);  // 0x0000000A
        PARENT_REASON_MAP.put(Standard.ILLEGAL_OPERATION, Standard.GENERAL_FAILURE);  // 0x0000000B
        PARENT_REASON_MAP.put(Standard.PERMISSION_DENIED, Standard.AUTHENTICATION_NOT_SUCCESSFUL);  // 0x0000000C
        PARENT_REASON_MAP.put(Standard.OBJECT_ARCHIVED, Standard.GENERAL_FAILURE);  // 0x0000000D
        PARENT_REASON_MAP.put(Standard.INDEX_OUT_OF_BOUNDS, Standard.GENERAL_FAILURE);  // 0x0000000E
        PARENT_REASON_MAP.put(Standard.APPLICATION_NAMESPACE_NOT_SUPPORTED, Standard.OPERATION_NOT_SUPPORTED);  // 0x0000000F
        PARENT_REASON_MAP.put(Standard.KEY_FORMAT_TYPE_NOT_SUPPORTED, Standard.OPERATION_NOT_SUPPORTED);  // 0x00000010
        PARENT_REASON_MAP.put(Standard.KEY_COMPRESSION_TYPE_NOT_SUPPORTED, Standard.OPERATION_NOT_SUPPORTED);  // 0x00000011
        PARENT_REASON_MAP.put(Standard.ENCODING_OPTION_ERROR, Standard.GENERAL_FAILURE);  // 0x00000012
        PARENT_REASON_MAP.put(Standard.KEY_VALUE_NOT_PRESENT, Standard.GENERAL_FAILURE);  // 0x00000013
        PARENT_REASON_MAP.put(Standard.ATTESTATION_REQUIRED, Standard.GENERAL_FAILURE);  // 0x00000014
        PARENT_REASON_MAP.put(Standard.ATTESTATION_FAILED, Standard.ATTESTATION_REQUIRED);  // 0x00000015
        PARENT_REASON_MAP.put(Standard.SENSITIVE, Standard.GENERAL_FAILURE);  // 0x00000016
        PARENT_REASON_MAP.put(Standard.NOT_EXTRACTABLE, Standard.GENERAL_FAILURE);  // 0x00000017
        PARENT_REASON_MAP.put(Standard.OBJECT_ALREADY_EXISTS, Standard.GENERAL_FAILURE);  // 0x00000018
        PARENT_REASON_MAP.put(Standard.INVALID_TICKET, Standard.INVALID_MESSAGE);  // 0x00000019
        PARENT_REASON_MAP.put(Standard.USAGE_LIMIT_EXCEEDED, Standard.GENERAL_FAILURE);  // 0x0000001A
        PARENT_REASON_MAP.put(Standard.NUMERIC_RANGE, Standard.GENERAL_FAILURE);  // 0x0000001B
        PARENT_REASON_MAP.put(Standard.INVALID_DATA_TYPE, Standard.INVALID_MESSAGE);  // 0x0000001C
        PARENT_REASON_MAP.put(Standard.READ_ONLY_ATTRIBUTE, Standard.INVALID_ATTRIBUTE);  // 0x0000001D
        PARENT_REASON_MAP.put(Standard.MULTI_VALUED_ATTRIBUTE, Standard.INVALID_ATTRIBUTE);  // 0x0000001E
        PARENT_REASON_MAP.put(Standard.UNSUPPORTED_ATTRIBUTE, Standard.OPERATION_NOT_SUPPORTED);  // 0x0000001F
        PARENT_REASON_MAP.put(Standard.ATTRIBUTE_INSTANCE_NOT_FOUND, Standard.ATTRIBUTE_NOT_FOUND);  // 0x00000020
        PARENT_REASON_MAP.put(Standard.ATTRIBUTE_NOT_FOUND, Standard.INVALID_ATTRIBUTE);  // 0x00000021
        PARENT_REASON_MAP.put(Standard.ATTRIBUTE_READ_ONLY, Standard.READ_ONLY_ATTRIBUTE);  // 0x00000022
        PARENT_REASON_MAP.put(Standard.ATTRIBUTE_SINGLE_VALUED, Standard.MULTI_VALUED_ATTRIBUTE);  // 0x00000023
        PARENT_REASON_MAP.put(Standard.BAD_CRYPTOGRAPHIC_PARAMETERS, Standard.CRYPTOGRAPHIC_FAILURE);  // 0x00000024
        PARENT_REASON_MAP.put(Standard.BAD_PASSWORD, Standard.AUTHENTICATION_NOT_SUCCESSFUL);  // 0x00000025
        PARENT_REASON_MAP.put(Standard.CODEC_ERROR, Standard.GENERAL_FAILURE);  // 0x00000026
        PARENT_REASON_MAP.put(Standard.RESERVED, Standard.GENERAL_FAILURE);  // 0x00000027
        PARENT_REASON_MAP.put(Standard.ILLEGAL_OBJECT_TYPE, Standard.ILLEGAL_OPERATION);  // 0x00000028
        PARENT_REASON_MAP.put(Standard.INCOMPATIBLE_CRYPTOGRAPHIC_USAGE_MASK, Standard.CRYPTOGRAPHIC_FAILURE);  // 0x00000029
        PARENT_REASON_MAP.put(Standard.INTERNAL_SERVER_ERROR, Standard.GENERAL_FAILURE);  // 0x0000002A
        PARENT_REASON_MAP.put(Standard.INVALID_ASYNCHRONOUS_CORRELATION_VALUE, Standard.INVALID_MESSAGE);  // 0x0000002B
        PARENT_REASON_MAP.put(Standard.INVALID_ATTRIBUTE, Standard.INVALID_MESSAGE);  // 0x0000002C
        PARENT_REASON_MAP.put(Standard.INVALID_ATTRIBUTE_VALUE, Standard.INVALID_ATTRIBUTE);  // 0x0000002D
        PARENT_REASON_MAP.put(Standard.INVALID_CORRELATION_VALUE, Standard.INVALID_MESSAGE);  // 0x0000002E
        PARENT_REASON_MAP.put(Standard.INVALID_CSR, Standard.INVALID_MESSAGE);  // 0x0000002F
        PARENT_REASON_MAP.put(Standard.INVALID_OBJECT_TYPE, Standard.INVALID_MESSAGE);  // 0x00000030
        PARENT_REASON_MAP.put(Standard.RESERVED_2, Standard.RESERVED);  // 0x00000031
        PARENT_REASON_MAP.put(Standard.KEY_WRAP_TYPE_NOT_SUPPORTED, Standard.OPERATION_NOT_SUPPORTED);  // 0x00000032
        PARENT_REASON_MAP.put(Standard.RESERVED_3, Standard.RESERVED);  // 0x00000033
        PARENT_REASON_MAP.put(Standard.MISSING_INITIALIZATION_VECTOR, Standard.CRYPTOGRAPHIC_FAILURE);  // 0x00000034
        PARENT_REASON_MAP.put(Standard.NON_UNIQUE_NAME_ATTRIBUTE, Standard.INVALID_ATTRIBUTE);  // 0x00000035
        PARENT_REASON_MAP.put(Standard.OBJECT_DESTROYED, Standard.OBJECT_ARCHIVED);  // 0x00000036
        PARENT_REASON_MAP.put(Standard.OBJECT_NOT_FOUND, Standard.GENERAL_FAILURE);  // 0x00000037
        PARENT_REASON_MAP.put(Standard.RESERVED_4, Standard.RESERVED);  // 0x00000038
        PARENT_REASON_MAP.put(Standard.NOT_AUTHORISED, Standard.AUTHENTICATION_NOT_SUCCESSFUL);  // 0x00000039
        PARENT_REASON_MAP.put(Standard.SERVER_LIMIT_EXCEEDED, Standard.USAGE_LIMIT_EXCEEDED);  // 0x0000003A
        PARENT_REASON_MAP.put(Standard.UNKNOWN_ENUMERATION, Standard.GENERAL_FAILURE);  // 0x0000003B
        PARENT_REASON_MAP.put(Standard.UNKNOWN_MESSAGE_EXTENSION, Standard.UNKNOWN_ENUMERATION);  // 0x0000003C
        PARENT_REASON_MAP.put(Standard.UNKNOWN_TAG, Standard.UNKNOWN_ENUMERATION);  // 0x0000003D
        PARENT_REASON_MAP.put(Standard.UNSUPPORTED_CRYPTOGRAPHIC_PARAMETERS, Standard.CRYPTOGRAPHIC_FAILURE);  // 0x0000003E
        PARENT_REASON_MAP.put(Standard.UNSUPPORTED_PROTOCOL_VERSION, Standard.OPERATION_NOT_SUPPORTED);  // 0x0000003F
        PARENT_REASON_MAP.put(Standard.WRAPPING_OBJECT_ARCHIVED, Standard.OBJECT_ARCHIVED);  // 0x00000040
        PARENT_REASON_MAP.put(Standard.WRAPPING_OBJECT_DESTROYED, Standard.OBJECT_DESTROYED);  // 0x00000041
        PARENT_REASON_MAP.put(Standard.WRAPPING_OBJECT_NOT_FOUND, Standard.OBJECT_NOT_FOUND);  // 0x00000042
        PARENT_REASON_MAP.put(Standard.WRONG_KEY_LIFECYCLE_STATE, Standard.ILLEGAL_OPERATION);  // 0x00000043
        PARENT_REASON_MAP.put(Standard.PROTECTION_STORAGE_UNAVAILABLE, Standard.INTERNAL_SERVER_ERROR);  // 0x00000044
        PARENT_REASON_MAP.put(Standard.PKCS11_CODEC_ERROR, Standard.CODEC_ERROR);  // 0x00000045
        PARENT_REASON_MAP.put(Standard.PKCS11_INVALID_FUNCTION, Standard.PKCS11_CODEC_ERROR);  // 0x00000046
        PARENT_REASON_MAP.put(Standard.PKCS11_INVALID_INTERFACE, Standard.PKCS11_CODEC_ERROR);  // 0x00000047
        PARENT_REASON_MAP.put(Standard.PRIVATE_PROTECTION_STORAGE_UNAVAILABLE, Standard.PROTECTION_STORAGE_UNAVAILABLE);  // 0x00000048
        PARENT_REASON_MAP.put(Standard.PUBLIC_PROTECTION_STORAGE_UNAVAILABLE, Standard.PROTECTION_STORAGE_UNAVAILABLE);  // 0x00000049
        PARENT_REASON_MAP.put(Standard.UNKNOWN_OBJECT_GROUP, Standard.OBJECT_NOT_FOUND);  // 0x0000004A
        PARENT_REASON_MAP.put(Standard.CONSTRAINT_VIOLATION, Standard.ILLEGAL_OPERATION);  // 0x0000004B
        PARENT_REASON_MAP.put(Standard.DUPLICATE_PROCESS_REQUEST, Standard.ILLEGAL_OPERATION);  // 0x0000004C
        PARENT_REASON_MAP.put(Standard.CIRCULAR_LINK_ERROR, Standard.ILLEGAL_OPERATION);  // 0x0000004D
        // GENERAL_FAILURE is the root (0x00000100)
    }

    @NonNull
    private final Value value;

    @Builder
    private ResultReason(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static ResultReason of(@NonNull Value value) {
        return new ResultReason(value);
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
     * Registers a custom, vendor-specific Result Reason.
     *
     * @param value             The integer value of the reason (must be within the extension range).
     * @param description       A unique, non-empty description for the reason.
     * @param supportedVersions A set of {@link KmipSpec} versions that support this reason.
     * @param parentReason      The parent {@link Value} in the reason hierarchy.
     * @return The registered {@link Value} instance.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions, @NonNull Value parentReason) {
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
        PARENT_REASON_MAP.putIfAbsent(custom, parentReason);
        EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
        return custom;
    }

    /**
     * Looks up a {@link Value} instance from its descriptive name.
     *
     * @param name The case-sensitive description of the reason.
     * @return The corresponding {@link Value}.
     * @throws NoSuchElementException if no reason is found for the given name in the current KMIP context.
     */
    public static Value fromName(String name) {
        final String nameLowerCase = name.toLowerCase(Locale.ROOT);
        KmipSpec spec = KmipContext.getSpec();
        Value v = DESCRIPTION_REGISTRY.get(nameLowerCase);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No ResultReason value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Looks up a {@link Value} instance from its integer value.
     *
     * @param value The integer value of the reason.
     * @return The corresponding {@link Value}.
     * @throws NoSuchElementException if no reason is found for the given value in the current KMIP context.
     */
    public static Value fromValue(int value) {
        KmipSpec spec = KmipContext.getSpec();
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No ResultReason value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Gets a collection of all registered custom (extension) Result Reason values.
     *
     * @return A {@link Collection} of extension {@link Value} instances.
     */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    /**
     * Gets the parent reason for a given result reason.
     *
     * @param reason The result reason to get the parent for.
     * @return The parent reason, or {@code null} if the reason is {@code GENERAL_FAILURE} or not found.
     */
    public static Value getParentReason(Value reason) {
        return PARENT_REASON_MAP.get(reason);
    }

    private void validate() {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for ResultReason is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Result Reasons.
     */
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
        ILLEGAL_OPERATION(0x0000000B, "IllegalOperation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        PERMISSION_DENIED(0x0000000C, "PermissionDenied", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_ARCHIVED(0x0000000D, "ObjectArchived", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INDEX_OUT_OF_BOUNDS(0x0000000E, "IndexOutOfBounds", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
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
        RESERVED(0x00000027, "Reserved", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        ILLEGAL_OBJECT_TYPE(0x00000028, "IllegalObjectType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INCOMPATIBLE_CRYPTOGRAPHIC_USAGE_MASK(0x00000029, "IncompatibleCryptographicUsageMask", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTERNAL_SERVER_ERROR(0x0000002A, "InternalServerError", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_ASYNCHRONOUS_CORRELATION_VALUE(0x0000002B, "InvalidAsynchronousCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_ATTRIBUTE(0x0000002C, "InvalidAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_ATTRIBUTE_VALUE(0x0000002D, "InvalidAttributeValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_CORRELATION_VALUE(0x0000002E, "InvalidCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_CSR(0x0000002F, "InvalidCsr", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INVALID_OBJECT_TYPE(0x00000030, "InvalidObjectType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RESERVED_2(0x00000031, "Reserved", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        KEY_WRAP_TYPE_NOT_SUPPORTED(0x00000032, "KeyWrapTypeNotSupported", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RESERVED_3(0x00000033, "Reserved", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        MISSING_INITIALIZATION_VECTOR(0x00000034, "MissingInitializationVector", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NON_UNIQUE_NAME_ATTRIBUTE(0x00000035, "NonUniqueNameAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_DESTROYED(0x00000036, "ObjectDestroyed", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_NOT_FOUND(0x00000037, "ObjectNotFound", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RESERVED_4(0x00000038, "Reserved", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        NOT_AUTHORISED(0x00000039, "NotAuthorized", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
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
        GENERAL_FAILURE(0x00000100, "GeneralFailure", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public ResultReason inst() {
            return ResultReason.of(this);
        }
    }

    /**
     * An interface representing a Result Reason value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<ResultReason> {
    }

    /**
     * Represents a custom, vendor-specific Result Reason.
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
        public ResultReason inst() {
            return ResultReason.of(this);
        }
    }
}
