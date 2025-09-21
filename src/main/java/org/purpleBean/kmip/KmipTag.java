package org.purpleBean.kmip;

import lombok.*;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@EqualsAndHashCode
@ToString
public final class KmipTag {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, KmipTag.Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    @EqualsAndHashCode.Include
    @NonNull
    private final Value value;

    private static boolean isValidExtensionValue(int value) {
        int extensionStart = 0x540000;
        int extensionEnd = 0x54FFFF;
        return !(value < extensionStart || value > extensionEnd);
    }

    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        if (!isValidExtensionValue(value)) {
            throw new IllegalArgumentException(String.format("Extension value %d must be between 0x540000 and 0x54FFFF", value));
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Value custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.put(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        return VALUE_REGISTRY.compute(value, (k, existing) -> existing != null ? existing : custom);
    }

    public static Value fromBytes(KmipSpec spec, byte[] bytes) {
        if (bytes == null || bytes.length != TtlvConstants.TAG_SIZE) {
            throw new IllegalArgumentException(String.format("Expected %s byte array for tag", TtlvConstants.TAG_SIZE));
        }
        int value = ((bytes[0] & 0xFF) << 16) |
                ((bytes[1] & 0xFF) << 8) |
                (bytes[2] & 0xFF);
        return fromValue(spec, value);
    }

    public static Value fromValue(KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for %d in KMIP spec %s", value, spec)
                ));
    }

    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    public byte[] getTagBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(EncodingType.INTEGER.getRawByteSize());
        buffer.putInt(value.getValue());

        // Convert last 3 bytes to String using UTF-8
        byte[] tagBytes = new byte[3];
        System.arraycopy(buffer.array(), 1, tagBytes, 0, 3);

        return tagBytes;
    }

    public String getTagHexString() {
        byte[] tagBytes = getTagBytes();
        StringBuilder hexString = new StringBuilder().append("0x");

        for (byte b : tagBytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    public boolean isSupportedFor(KmipSpec spec) {
        if (spec == null) {
            return true;
        }
        return value.isSupportedFor(spec);
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        ACTIVATION_DATE(0x420001, "ActivationDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        APPLICATION_DATA(0x420002, "ApplicationData", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        APPLICATION_NAMESPACE(0x420003, "ApplicationNamespace", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        APPLICATION_SPECIFIC_INFORMATION(0x420004, "ApplicationSpecificInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ARCHIVE_DATE(0x420005, "ArchiveDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ASYNCHRONOUS_CORRELATION_VALUE(0x420006, "AsynchronousCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ASYNCHRONOUS_INDICATOR(0x420007, "AsynchronousIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTRIBUTE(0x420008, "Attribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTRIBUTE_INDEX(0x420009, "AttributeIndex", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTRIBUTE_NAME(0x42000A, "AttributeName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTRIBUTE_VALUE(0x42000B, "AttributeValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        AUTHENTICATION(0x42000C, "Authentication", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        BATCH_COUNT(0x42000D, "BatchCount", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        BATCH_ERROR_CONTINUATION_OPTION(0x42000E, "BatchErrorContinuationOption", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        BATCH_ITEM(0x42000F, "BatchItem", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        BATCH_ORDER_OPTION(0x420010, "BatchOrderOption", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        BLOCK_CIPHER_MODE(0x420011, "BlockCipherMode", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CANCELLATION_RESULT(0x420012, "CancellationResult", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE(0x420013, "Certificate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE_REQUEST(0x420018, "CertificateRequest", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE_REQUEST_TYPE(0x420019, "CertificateRequestType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE_TYPE(0x42001D, "CertificateType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE_VALUE(0x42001E, "CertificateValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        COMMON_TEMPLATE_ATTRIBUTE(0x42001F, "CommonTemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        COMPROMISE_DATE(0x420020, "CompromiseDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        COMPROMISE_OCCURRENCE_DATE(0x420021, "CompromiseOccurrenceDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CONTACT_INFORMATION(0x420022, "ContactInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CREDENTIAL(0x420023, "Credential", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CREDENTIAL_TYPE(0x420024, "CredentialType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CREDENTIAL_VALUE(0x420025, "CredentialValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRITICALITY_INDICATOR(0x420026, "CriticalityIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRT_COEFFICIENT(0x420027, "CrtCoefficient", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRYPTOGRAPHIC_ALGORITHM(0x420028, "CryptographicAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRYPTOGRAPHIC_DOMAIN_PARAMETERS(0x420029, "CryptographicDomainParameters", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRYPTOGRAPHIC_LENGTH(0x42002A, "CryptographicLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRYPTOGRAPHIC_PARAMETERS(0x42002B, "CryptographicParameters", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CRYPTOGRAPHIC_USAGE_MASK(0x42002C, "CryptographicUsageMask", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CUSTOM_ATTRIBUTE(0x42002D, "CustomAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        D(0x42002E, "D", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DEACTIVATION_DATE(0x42002F, "DeactivationDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DERIVATION_DATA(0x420030, "DerivationData", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DERIVATION_METHOD(0x420031, "DerivationMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DERIVATION_PARAMETERS(0x420032, "DerivationParameters", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DESTROY_DATE(0x420033, "DestroyDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DIGEST(0x420034, "Digest", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DIGEST_VALUE(0x420035, "DigestValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ENCRYPTION_KEY_INFORMATION(0x420036, "EncryptionKeyInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        G(0x420037, "G", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        HASHING_ALGORITHM(0x420038, "HashingAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        INITIAL_DATE(0x420039, "InitialDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        INITIALIZATION_VECTOR(0x42003A, "InitializationVector", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ITERATION_COUNT(0x42003C, "IterationCount", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        IV_COUNTER_NONCE(0x42003D, "IvCounterNonce", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        J(0x42003E, "J", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY(0x42003F, "Key", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_BLOCK(0x420040, "KeyBlock", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_COMPRESSION_TYPE(0x420041, "KeyCompressionType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_FORMAT_TYPE(0x420042, "KeyFormatType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_MATERIAL(0x420043, "KeyMaterial", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_PART_IDENTIFIER(0x420044, "KeyPartIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_VALUE(0x420045, "KeyValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_WRAPPING_DATA(0x420046, "KeyWrappingData", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_WRAPPING_SPECIFICATION(0x420047, "KeyWrappingSpecification", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        LAST_CHANGE_DATE(0x420048, "LastChangeDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        LEASE_TIME(0x420049, "LeaseTime", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        LINK(0x42004A, "Link", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        LINK_TYPE(0x42004B, "LinkType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        LINKED_OBJECT_IDENTIFIER(0x42004C, "LinkedObjectIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MAC_SIGNATURE(0x42004D, "MacSignature", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MAC_SIGNATURE_KEY_INFORMATION(0x42004E, "MacSignatureKeyInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MAXIMUM_ITEMS(0x42004F, "MaximumItems", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MAXIMUM_RESPONSE_SIZE(0x420050, "MaximumResponseSize", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MESSAGE_EXTENSION(0x420051, "MessageExtension", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MODULUS(0x420052, "Modulus", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NAME(0x420053, "Name", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NAME_TYPE(0x420054, "NameType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NAME_VALUE(0x420055, "NameValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OBJECT_GROUP(0x420056, "ObjectGroup", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OBJECT_TYPE(0x420057, "ObjectType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OFFSET(0x420058, "Offset", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OPAQUE_DATA_TYPE(0x420059, "OpaqueDataType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OPAQUE_DATA_VALUE(0x42005A, "OpaqueDataValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OPAQUE_OBJECT(0x42005B, "OpaqueObject", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OPERATION(0x42005C, "Operation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OPERATION_POLICY_NAME(0x42005D, "OperationPolicyName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        P(0x42005E, "P", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PADDING_METHOD(0x42005F, "PaddingMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIME_EXPONENT_P(0x420060, "PrimeExponentP", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIME_EXPONENT_Q(0x420061, "PrimeExponentQ", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIME_FIELD_SIZE(0x420062, "PrimeFieldSize", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIVATE_EXPONENT(0x420063, "PrivateExponent", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIVATE_KEY(0x420064, "PrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIVATE_KEY_TEMPLATE_ATTRIBUTE(0x420065, "PrivateKeyTemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIVATE_KEY_UNIQUE_IDENTIFIER(0x420066, "PrivateKeyUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PROCESS_START_DATE(0x420067, "ProcessStartDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PROTECT_STOP_DATE(0x420068, "ProtectStopDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PROTOCOL_VERSION(0x420069, "ProtocolVersion", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PROTOCOL_VERSION_MAJOR(0x42006A, "ProtocolVersionMajor", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PROTOCOL_VERSION_MINOR(0x42006B, "ProtocolVersionMinor", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PUBLIC_EXPONENT(0x42006C, "PublicExponent", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PUBLIC_KEY(0x42006D, "PublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PUBLIC_KEY_TEMPLATE_ATTRIBUTE(0x42006E, "PublicKeyTemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PUBLIC_KEY_UNIQUE_IDENTIFIER(0x42006F, "PublicKeyUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PUT_FUNCTION(0x420070, "PutFunction", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        Q(0x420071, "Q", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        Q_STRING(0x420072, "QString", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        QLENGTH(0x420073, "Qlength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        QUERY_FUNCTION(0x420074, "QueryFunction", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RECOMMENDED_CURVE(0x420075, "RecommendedCurve", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REPLACED_UNIQUE_IDENTIFIER(0x420076, "ReplacedUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REQUEST_HEADER(0x420077, "RequestHeader", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REQUEST_MESSAGE(0x420078, "RequestMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REQUEST_PAYLOAD(0x420079, "RequestPayload", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RESPONSE_HEADER(0x42007A, "ResponseHeader", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RESPONSE_MESSAGE(0x42007B, "ResponseMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RESPONSE_PAYLOAD(0x42007C, "ResponsePayload", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RESULT_MESSAGE(0x42007D, "ResultMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RESULT_REASON(0x42007E, "ResultReason", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RESULT_STATUS(0x42007F, "ResultStatus", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REVOCATION_MESSAGE(0x420080, "RevocationMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REVOCATION_REASON(0x420081, "RevocationReason", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        REVOCATION_REASON_CODE(0x420082, "RevocationReasonCode", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_ROLE_TYPE(0x420083, "KeyRoleType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SALT(0x420084, "Salt", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SECRET_DATA(0x420085, "SecretData", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SECRET_DATA_TYPE(0x420086, "SecretDataType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SERVER_INFORMATION(0x420088, "ServerInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SPLIT_KEY(0x420089, "SplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SPLIT_KEY_METHOD(0x42008A, "SplitKeyMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SPLIT_KEY_PARTS(0x42008B, "SplitKeyParts", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SPLIT_KEY_THRESHOLD(0x42008C, "SplitKeyThreshold", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        STATE(0x42008D, "State", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        STORAGE_STATUS_MASK(0x42008E, "StorageStatusMask", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SYMMETRIC_KEY(0x42008F, "SymmetricKey", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        TEMPLATE(0x420090, "Template", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        TEMPLATE_ATTRIBUTE(0x420091, "TemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        TIME_STAMP(0x420092, "TimeStamp", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        UNIQUE_BATCH_ITEM_ID(0x420093, "UniqueBatchItemId", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        UNIQUE_IDENTIFIER(0x420094, "UniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        USAGE_LIMITS(0x420095, "UsageLimits", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        USAGE_LIMITS_COUNT(0x420096, "UsageLimitsCount", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        USAGE_LIMITS_TOTAL(0x420097, "UsageLimitsTotal", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        USAGE_LIMITS_UNIT(0x420098, "UsageLimitsUnit", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        USERNAME(0x420099, "Username", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        VALIDITY_DATE(0x42009A, "ValidityDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        VALIDITY_INDICATOR(0x42009B, "ValidityIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        VENDOR_EXTENSION(0x42009C, "VendorExtension", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        VENDOR_IDENTIFICATION(0x42009D, "VendorIdentification", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        WRAPPING_METHOD(0x42009E, "WrappingMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        X(0x42009F, "X", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        Y(0x4200A0, "Y", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PASSWORD(0x4200A1, "Password", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DEVICE_IDENTIFIER(0x4200A2, "DeviceIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ENCODING_OPTION(0x4200A3, "EncodingOption", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        EXTENSION_INFORMATION(0x4200A4, "ExtensionInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        EXTENSION_NAME(0x4200A5, "ExtensionName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        EXTENSION_TAG(0x4200A6, "ExtensionTag", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        EXTENSION_TYPE(0x4200A7, "ExtensionType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        FRESH(0x4200A8, "Fresh", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MACHINE_IDENTIFIER(0x4200A9, "MachineIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MEDIA_IDENTIFIER(0x4200AA, "MediaIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NETWORK_IDENTIFIER(0x4200AB, "NetworkIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        OBJECT_GROUP_MEMBER(0x4200AC, "ObjectGroupMember", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE_LENGTH(0x4200AD, "CertificateLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DIGITAL_SIGNATURE_ALGORITHM(0x4200AE, "DigitalSignatureAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        CERTIFICATE_SERIAL_NUMBER(0x4200AF, "CertificateSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DEVICE_SERIAL_NUMBER(0x4200B0, "DeviceSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ISSUER_ALTERNATIVE_NAME(0x4200B1, "IssuerAlternativeName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ISSUER_DISTINGUISHED_NAME(0x4200B2, "IssuerDistinguishedName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SUBJECT_ALTERNATIVE_NAME(0x4200B3, "SubjectAlternativeName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SUBJECT_DISTINGUISHED_NAME(0x4200B4, "SubjectDistinguishedName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        X_509_CERTIFICATE_IDENTIFIER(0x4200B5, "X509CertificateIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        X_509_CERTIFICATE_ISSUER(0x4200B6, "X509CertificateIssuer", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        X_509_CERTIFICATE_SUBJECT(0x4200B7, "X509CertificateSubject", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_VALUE_LOCATION(0x4200B8, "KeyValueLocation", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_VALUE_LOCATION_VALUE(0x4200B9, "KeyValueLocationValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_VALUE_LOCATION_TYPE(0x4200BA, "KeyValueLocationType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        KEY_VALUE_PRESENT(0x4200BB, "KeyValuePresent", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ORIGINAL_CREATION_DATE(0x4200BC, "OriginalCreationDate", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PGP_KEY(0x4200BD, "PgpKey", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PGP_KEY_VERSION(0x4200BE, "PgpKeyVersion", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ALTERNATIVE_NAME(0x4200BF, "AlternativeName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ALTERNATIVE_NAME_VALUE(0x4200C0, "AlternativeNameValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ALTERNATIVE_NAME_TYPE(0x4200C1, "AlternativeNameType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DATA(0x4200C2, "Data", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        SIGNATURE_DATA(0x4200C3, "SignatureData", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        DATA_LENGTH(0x4200C4, "DataLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        RANDOM_IV(0x4200C5, "RandomIv", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        MAC_DATA(0x4200C6, "MacData", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTESTATION_TYPE(0x4200C7, "AttestationType", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NONCE(0x4200C8, "Nonce", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NONCE_ID(0x4200C9, "NonceId", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        NONCE_VALUE(0x4200CA, "NonceValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTESTATION_MEASUREMENT(0x4200CB, "AttestationMeasurement", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTESTATION_ASSERTION(0x4200CC, "AttestationAssertion", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        IV_LENGTH(0x4200CD, "IvLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        TAG_LENGTH(0x4200CE, "TagLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        FIXED_FIELD_LENGTH(0x4200CF, "FixedFieldLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        COUNTER_LENGTH(0x4200D0, "CounterLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        INITIAL_COUNTER_VALUE(0x4200D1, "InitialCounterValue", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        INVOCATION_FIELD_LENGTH(0x4200D2, "InvocationFieldLength", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTESTATION_CAPABLE_INDICATOR(0x4200D3, "AttestationCapableIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2);


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

    public interface Value {
        int getValue();

        String getDescription();

        boolean isSupportedFor(KmipSpec spec);

        boolean isCustom();
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    @EqualsAndHashCode
    private static final class Extension implements Value {
        @EqualsAndHashCode.Include
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = true;

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }
}
