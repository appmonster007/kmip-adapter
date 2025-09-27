package org.purpleBean.kmip;

import lombok.*;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@EqualsAndHashCode
@ToString
public class KmipTag {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

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
        ACTIVATION_DATE(0x420001, "ActivationDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        APPLICATION_DATA(0x420002, "ApplicationData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        APPLICATION_NAMESPACE(0x420003, "ApplicationNamespace", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        APPLICATION_SPECIFIC_INFORMATION(0x420004, "ApplicationSpecificInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ARCHIVE_DATE(0x420005, "ArchiveDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYNCHRONOUS_CORRELATION_VALUE(0x420006, "AsynchronousCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYNCHRONOUS_INDICATOR(0x420007, "AsynchronousIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE(0x420008, "Attribute", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_INDEX(0x420009, "AttributeIndex", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        ATTRIBUTE_NAME(0x42000A, "AttributeName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_VALUE(0x42000B, "AttributeValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        AUTHENTICATION(0x42000C, "Authentication", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        BATCH_COUNT(0x42000D, "BatchCount", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        BATCH_ERROR_CONTINUATION_OPTION(0x42000E, "BatchErrorContinuationOption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        BATCH_ITEM(0x42000F, "BatchItem", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        BATCH_ORDER_OPTION(0x420010, "BatchOrderOption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        BLOCK_CIPHER_MODE(0x420011, "BlockCipherMode", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CANCELLATION_RESULT(0x420012, "CancellationResult", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE(0x420013, "Certificate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_REQUEST(0x420018, "CertificateRequest", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_REQUEST_TYPE(0x420019, "CertificateRequestType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_TYPE(0x42001D, "CertificateType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_VALUE(0x42001E, "CertificateValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        COMMON_TEMPLATE_ATTRIBUTE(0x42001F, "CommonTemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        COMPROMISE_DATE(0x420020, "CompromiseDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        COMPROMISE_OCCURRENCE_DATE(0x420021, "CompromiseOccurrenceDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CONTACT_INFORMATION(0x420022, "ContactInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CREDENTIAL(0x420023, "Credential", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CREDENTIAL_TYPE(0x420024, "CredentialType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CREDENTIAL_VALUE(0x420025, "CredentialValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRITICALITY_INDICATOR(0x420026, "CriticalityIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRT_COEFFICIENT(0x420027, "CrtCoefficient", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRYPTOGRAPHIC_ALGORITHM(0x420028, "CryptographicAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRYPTOGRAPHIC_DOMAIN_PARAMETERS(0x420029, "CryptographicDomainParameters", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRYPTOGRAPHIC_LENGTH(0x42002A, "CryptographicLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRYPTOGRAPHIC_PARAMETERS(0x42002B, "CryptographicParameters", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CRYPTOGRAPHIC_USAGE_MASK(0x42002C, "CryptographicUsageMask", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CUSTOM_ATTRIBUTE(0x42002D, "Custom", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        D(0x42002E, "D", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DEACTIVATION_DATE(0x42002F, "DeactivationDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DERIVATION_DATA(0x420030, "DerivationData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DERIVATION_METHOD(0x420031, "DerivationMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DERIVATION_PARAMETERS(0x420032, "DerivationParameters", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DESTROY_DATE(0x420033, "DestroyDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DIGEST(0x420034, "Digest", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DIGEST_VALUE(0x420035, "DigestValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ENCRYPTION_KEY_INFORMATION(0x420036, "EncryptionKeyInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        G(0x420037, "G", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        HASHING_ALGORITHM(0x420038, "HashingAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INITIAL_DATE(0x420039, "InitialDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INITIALIZATION_VECTOR(0x42003A, "InitializationVector", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ITERATION_COUNT(0x42003C, "IterationCount", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        IV_COUNTER_NONCE(0x42003D, "IvCounterNonce", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        J(0x42003E, "J", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY(0x42003F, "Key", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_BLOCK(0x420040, "KeyBlock", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_COMPRESSION_TYPE(0x420041, "KeyCompressionType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_FORMAT_TYPE(0x420042, "KeyFormatType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_MATERIAL(0x420043, "KeyMaterial", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_PART_IDENTIFIER(0x420044, "KeyPartIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_VALUE(0x420045, "KeyValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_WRAPPING_DATA(0x420046, "KeyWrappingData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_WRAPPING_SPECIFICATION(0x420047, "KeyWrappingSpecification", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        LAST_CHANGE_DATE(0x420048, "LastChangeDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        LEASE_TIME(0x420049, "LeaseTime", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        LINK(0x42004A, "Link", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        LINK_TYPE(0x42004B, "LinkType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        LINKED_OBJECT_IDENTIFIER(0x42004C, "LinkedObjectIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        MAC_SIGNATURE(0x42004D, "MacSignature", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAC_SIGNATURE_KEY_INFORMATION(0x42004E, "MacSignatureKeyInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAXIMUM_ITEMS(0x42004F, "MaximumItems", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAXIMUM_RESPONSE_SIZE(0x420050, "MaximumResponseSize", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MESSAGE_EXTENSION(0x420051, "MessageExtension", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MODULUS(0x420052, "Modulus", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NAME(0x420053, "Name", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NAME_TYPE(0x420054, "NameType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        NAME_VALUE(0x420055, "NameValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        OBJECT_GROUP(0x420056, "ObjectGroup", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        OBJECT_TYPE(0x420057, "ObjectType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OFFSET(0x420058, "Offset", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPAQUE_DATA_TYPE(0x420059, "OpaqueDataType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPAQUE_DATA_VALUE(0x42005A, "OpaqueDataValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPAQUE_OBJECT(0x42005B, "OpaqueObject", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPERATION(0x42005C, "Operation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OPERATION_POLICY_NAME(0x42005D, "OperationPolicyName", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        P(0x42005E, "P", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PADDING_METHOD(0x42005F, "PaddingMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIME_EXPONENT_P(0x420060, "PrimeExponentP", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIME_EXPONENT_Q(0x420061, "PrimeExponentQ", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIME_FIELD_SIZE(0x420062, "PrimeFieldSize", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_EXPONENT(0x420063, "PrivateExponent", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_KEY(0x420064, "PrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_KEY_TEMPLATE_ATTRIBUTE(0x420065, "PrivateKeyTemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PRIVATE_KEY_UNIQUE_IDENTIFIER(0x420066, "PrivateKeyUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PROCESS_START_DATE(0x420067, "ProcessStartDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTECT_STOP_DATE(0x420068, "ProtectStopDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTOCOL_VERSION(0x420069, "ProtocolVersion", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTOCOL_VERSION_MAJOR(0x42006A, "ProtocolVersionMajor", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTOCOL_VERSION_MINOR(0x42006B, "ProtocolVersionMinor", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_EXPONENT(0x42006C, "PublicExponent", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_KEY(0x42006D, "PublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_KEY_TEMPLATE_ATTRIBUTE(0x42006E, "PublicKeyTemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        PUBLIC_KEY_UNIQUE_IDENTIFIER(0x42006F, "PublicKeyUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PUT_FUNCTION(0x420070, "PutFunction", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        Q(0x420071, "Q", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        Q_STRING(0x420072, "QString", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QLENGTH(0x420073, "Qlength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        QUERY_FUNCTION(0x420074, "QueryFunction", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RECOMMENDED_CURVE(0x420075, "RecommendedCurve", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REPLACED_UNIQUE_IDENTIFIER(0x420076, "ReplacedUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REQUEST_HEADER(0x420077, "RequestHeader", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REQUEST_MESSAGE(0x420078, "RequestMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REQUEST_PAYLOAD(0x420079, "RequestPayload", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESPONSE_HEADER(0x42007A, "ResponseHeader", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESPONSE_MESSAGE(0x42007B, "ResponseMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESPONSE_PAYLOAD(0x42007C, "ResponsePayload", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESULT_MESSAGE(0x42007D, "ResultMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESULT_REASON(0x42007E, "ResultReason", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RESULT_STATUS(0x42007F, "ResultStatus", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REVOCATION_MESSAGE(0x420080, "RevocationMessage", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REVOCATION_REASON(0x420081, "RevocationReason", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        REVOCATION_REASON_CODE(0x420082, "RevocationReasonCode", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_ROLE_TYPE(0x420083, "KeyRoleType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SALT(0x420084, "Salt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SECRET_DATA(0x420085, "SecretData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SECRET_DATA_TYPE(0x420086, "SecretDataType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_INFORMATION(0x420088, "ServerInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SPLIT_KEY(0x420089, "SplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SPLIT_KEY_METHOD(0x42008A, "SplitKeyMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SPLIT_KEY_PARTS(0x42008B, "SplitKeyParts", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SPLIT_KEY_THRESHOLD(0x42008C, "SplitKeyThreshold", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        STATE(0x42008D, "State", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        STORAGE_STATUS_MASK(0x42008E, "StorageStatusMask", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SYMMETRIC_KEY(0x42008F, "SymmetricKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TEMPLATE(0x420090, "Template", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        TEMPLATE_ATTRIBUTE(0x420091, "TemplateAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2),
        TIME_STAMP(0x420092, "TimeStamp", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        UNIQUE_BATCH_ITEM_ID(0x420093, "UniqueBatchItemId", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        UNIQUE_IDENTIFIER(0x420094, "UniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        USAGE_LIMITS(0x420095, "UsageLimits", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        USAGE_LIMITS_COUNT(0x420096, "UsageLimitsCount", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        USAGE_LIMITS_TOTAL(0x420097, "UsageLimitsTotal", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        USAGE_LIMITS_UNIT(0x420098, "UsageLimitsUnit", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        USERNAME(0x420099, "Username", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDITY_DATE(0x42009A, "ValidityDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDITY_INDICATOR(0x42009B, "ValidityIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        VENDOR_EXTENSION(0x42009C, "VendorExtension", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        VENDOR_IDENTIFICATION(0x42009D, "VendorIdentification", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        WRAPPING_METHOD(0x42009E, "WrappingMethod", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        X(0x42009F, "X", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        Y(0x4200A0, "Y", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PASSWORD(0x4200A1, "Password", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DEVICE_IDENTIFIER(0x4200A2, "DeviceIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ENCODING_OPTION(0x4200A3, "EncodingOption", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_INFORMATION(0x4200A4, "ExtensionInformation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_NAME(0x4200A5, "ExtensionName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_TAG(0x4200A6, "ExtensionTag", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_TYPE(0x4200A7, "ExtensionType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        FRESH(0x4200A8, "Fresh", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MACHINE_IDENTIFIER(0x4200A9, "MachineIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MEDIA_IDENTIFIER(0x4200AA, "MediaIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NETWORK_IDENTIFIER(0x4200AB, "NetworkIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_GROUP_MEMBER(0x4200AC, "ObjectGroupMember", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        CERTIFICATE_LENGTH(0x4200AD, "CertificateLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DIGITAL_SIGNATURE_ALGORITHM(0x4200AE, "DigitalSignatureAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SERIAL_NUMBER(0x4200AF, "CertificateSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DEVICE_SERIAL_NUMBER(0x4200B0, "DeviceSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ISSUER_ALTERNATIVE_NAME(0x4200B1, "IssuerAlternativeName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ISSUER_DISTINGUISHED_NAME(0x4200B2, "IssuerDistinguishedName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SUBJECT_ALTERNATIVE_NAME(0x4200B3, "SubjectAlternativeName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SUBJECT_DISTINGUISHED_NAME(0x4200B4, "SubjectDistinguishedName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        X_509_CERTIFICATE_IDENTIFIER(0x4200B5, "X509CertificateIdentifier", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        X_509_CERTIFICATE_ISSUER(0x4200B6, "X509CertificateIssuer", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        X_509_CERTIFICATE_SUBJECT(0x4200B7, "X509CertificateSubject", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_VALUE_LOCATION(0x4200B8, "KeyValueLocation", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_VALUE_LOCATION_VALUE(0x4200B9, "KeyValueLocationValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_VALUE_LOCATION_TYPE(0x4200BA, "KeyValueLocationType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_VALUE_PRESENT(0x4200BB, "KeyValuePresent", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ORIGINAL_CREATION_DATE(0x4200BC, "OriginalCreationDate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PGP_KEY(0x4200BD, "PgpKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PGP_KEY_VERSION(0x4200BE, "PgpKeyVersion", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ALTERNATIVE_NAME(0x4200BF, "AlternativeName", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ALTERNATIVE_NAME_VALUE(0x4200C0, "AlternativeNameValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ALTERNATIVE_NAME_TYPE(0x4200C1, "AlternativeNameType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DATA(0x4200C2, "Data", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SIGNATURE_DATA(0x4200C3, "SignatureData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        DATA_LENGTH(0x4200C4, "DataLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        RANDOM_IV(0x4200C5, "RandomIv", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAC_DATA(0x4200C6, "MacData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_TYPE(0x4200C7, "AttestationType", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NONCE(0x4200C8, "Nonce", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NONCE_ID(0x4200C9, "NonceId", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        NONCE_VALUE(0x4200CA, "NonceValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_MEASUREMENT(0x4200CB, "AttestationMeasurement", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_ASSERTION(0x4200CC, "AttestationAssertion", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        IV_LENGTH(0x4200CD, "IvLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TAG_LENGTH(0x4200CE, "TagLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        FIXED_FIELD_LENGTH(0x4200CF, "FixedFieldLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        COUNTER_LENGTH(0x4200D0, "CounterLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INITIAL_COUNTER_VALUE(0x4200D1, "InitialCounterValue", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        INVOCATION_FIELD_LENGTH(0x4200D2, "InvocationFieldLength", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_CAPABLE_INDICATOR(0x4200D3, "AttestationCapableIndicator", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OFFSET_ITEMS(0x4200D4, "OffsetItems", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LOCATED_ITEMS(0x4200D5, "LocatedItems", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CORRELATION_VALUE(0x4200D6, "CorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INIT_INDICATOR(0x4200D7, "InitIndicator", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        FINAL_INDICATOR(0x4200D8, "FinalIndicator", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_PARAMETERS(0x4200D9, "RngParameters", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_ALGORITHM(0x4200DA, "RngAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DRBG_ALGORITHM(0x4200DB, "DrbgAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        FIPS186_VARIATION(0x4200DC, "Fips186Variation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PREDICTION_RESISTANCE(0x4200DD, "PredictionResistance", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RANDOM_NUMBER_GENERATOR(0x4200DE, "RandomNumberGenerator", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_INFORMATION(0x4200DF, "ValidationInformation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_AUTHORITY_TYPE(0x4200E0, "ValidationAuthorityType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_AUTHORITY_COUNTRY(0x4200E1, "ValidationAuthorityCountry", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_AUTHORITY_URI(0x4200E2, "ValidationAuthorityUri", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_VERSION_MAJOR(0x4200E3, "ValidationVersionMajor", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_VERSION_MINOR(0x4200E4, "ValidationVersionMinor", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_TYPE(0x4200E5, "ValidationType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_LEVEL(0x4200E6, "ValidationLevel", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_CERTIFICATE_IDENTIFIER(0x4200E7, "ValidationCertificateIdentifier", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_CERTIFICATE_URI(0x4200E8, "ValidationCertificateUri", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_VENDOR_URI(0x4200E9, "ValidationVendorUri", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        VALIDATION_PROFILE(0x4200EA, "ValidationProfile", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROFILE_INFORMATION(0x4200EB, "ProfileInformation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROFILE_NAME(0x4200EC, "ProfileName", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_URI(0x4200ED, "ServerUri", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_PORT(0x4200EE, "ServerPort", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        STREAMING_CAPABILITY(0x4200EF, "StreamingCapability", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYNCHRONOUS_CAPABILITY(0x4200F0, "AsynchronousCapability", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTESTATION_CAPABILITY(0x4200F1, "AttestationCapability", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        UNWRAP_MODE(0x4200F2, "UnwrapMode", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DESTROY_ACTION(0x4200F3, "DestroyAction", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHREDDING_ALGORITHM(0x4200F4, "ShreddingAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RNG_MODE(0x4200F5, "RngMode", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CLIENT_REGISTRATION_METHOD(0x4200F6, "ClientRegistrationMethod", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CAPABILITY_INFORMATION(0x4200F7, "CapabilityInformation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        KEY_WRAP_TYPE(0x4200F8, "KeyWrapType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BATCH_UNDO_CAPABILITY(0x4200F9, "BatchUndoCapability", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BATCH_CONTINUE_CAPABILITY(0x4200FA, "BatchContinueCapability", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_12_FRIENDLY_NAME(0x4200FB, "Pkcs12FriendlyName", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DESCRIPTION(0x4200FC, "Description", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        COMMENT(0x4200FD, "Comment", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        AUTHENTICATED_ENCRYPTION_ADDITIONAL_DATA(0x4200FE, "AuthenticatedEncryptionAdditionalData", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        AUTHENTICATED_ENCRYPTION_TAG(0x4200FF, "AuthenticatedEncryptionTag", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SALT_LENGTH(0x420100, "SaltLength", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MASK_GENERATOR(0x420101, "MaskGenerator", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        MASK_GENERATOR_HASHING_ALGORITHM(0x420102, "MaskGeneratorHashingAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        P_SOURCE(0x420103, "PSource", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TRAILER_FIELD(0x420104, "TrailerField", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CLIENT_CORRELATION_VALUE(0x420105, "ClientCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_CORRELATION_VALUE(0x420106, "ServerCorrelationValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DIGESTED_DATA(0x420107, "DigestedData", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_CN(0x420108, "CertificateSubjectCn", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_O(0x420109, "CertificateSubjectO", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_OU(0x42010A, "CertificateSubjectOu", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_EMAIL(0x42010B, "CertificateSubjectEmail", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_C(0x42010C, "CertificateSubjectC", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_ST(0x42010D, "CertificateSubjectSt", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_L(0x42010E, "CertificateSubjectL", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_UID(0x42010F, "CertificateSubjectUid", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_SERIAL_NUMBER(0x420110, "CertificateSubjectSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_TITLE(0x420111, "CertificateSubjectTitle", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_DC(0x420112, "CertificateSubjectDc", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_DN_QUALIFIER(0x420113, "CertificateSubjectDnQualifier", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_CN(0x420114, "CertificateIssuerCn", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_O(0x420115, "CertificateIssuerO", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_OU(0x420116, "CertificateIssuerOu", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_EMAIL(0x420117, "CertificateIssuerEmail", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_C(0x420118, "CertificateIssuerC", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_ST(0x420119, "CertificateIssuerSt", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_L(0x42011A, "CertificateIssuerL", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_UID(0x42011B, "CertificateIssuerUid", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_SERIAL_NUMBER(0x42011C, "CertificateIssuerSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_TITLE(0x42011D, "CertificateIssuerTitle", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_DC(0x42011E, "CertificateIssuerDc", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_DN_QUALIFIER(0x42011F, "CertificateIssuerDnQualifier", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SENSITIVE(0x420120, "Sensitive", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ALWAYS_SENSITIVE(0x420121, "AlwaysSensitive", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTRACTABLE(0x420122, "Extractable", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NEVER_EXTRACTABLE(0x420123, "NeverExtractable", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        REPLACE_EXISTING(0x420124, "ReplaceExisting", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTES(0x420125, "Attributes", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        COMMON_ATTRIBUTES(0x420126, "CommonAttributes", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_KEY_ATTRIBUTES(0x420127, "PrivateKeyAttributes", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_KEY_ATTRIBUTES(0x420128, "PublicKeyAttributes", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_ENUMERATION(0x420129, "ExtensionEnumeration", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_ATTRIBUTE(0x42012A, "ExtensionAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_PARENT_STRUCTURE_TAG(0x42012B, "ExtensionParentStructureTag", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EXTENSION_DESCRIPTION(0x42012C, "ExtensionDescription", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_NAME(0x42012D, "ServerName", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_SERIAL_NUMBER(0x42012E, "ServerSerialNumber", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_VERSION(0x42012F, "ServerVersion", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_LOAD(0x420130, "ServerLoad", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRODUCT_NAME(0x420131, "ProductName", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BUILD_LEVEL(0x420132, "BuildLevel", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BUILD_DATE(0x420133, "BuildDate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CLUSTER_INFO(0x420134, "ClusterInfo", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ALTERNATE_FAILOVER_ENDPOINTS(0x420135, "AlternateFailoverEndpoints", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SHORT_UNIQUE_IDENTIFIER(0x420136, "ShortUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TAG(0x420138, "Tag", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_REQUEST_UNIQUE_IDENTIFIER(0x420139, "CertificateRequestUniqueIdentifier", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NIST_KEY_TYPE(0x42013A, "NistKeyType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ATTRIBUTE_REFERENCE(0x42013B, "AttributeReference", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CURRENT_ATTRIBUTE(0x42013C, "CurrentAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NEW_ATTRIBUTE(0x42013D, "NewAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_REQUEST_VALUE(0x420140, "CertificateRequestValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LOG_MESSAGE(0x420141, "LogMessage", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROFILE_VERSION(0x420142, "ProfileVersion", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROFILE_VERSION_MAJOR(0x420143, "ProfileVersionMajor", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROFILE_VERSION_MINOR(0x420144, "ProfileVersionMinor", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTECTION_LEVEL(0x420145, "ProtectionLevel", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTECTION_PERIOD(0x420146, "ProtectionPeriod", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUANTUM_SAFE(0x420147, "QuantumSafe", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        QUANTUM_SAFE_CAPABILITY(0x420148, "QuantumSafeCapability", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TICKET(0x420149, "Ticket", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TICKET_TYPE(0x42014A, "TicketType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TICKET_VALUE(0x42014B, "TicketValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        REQUEST_COUNT(0x42014C, "RequestCount", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RIGHTS(0x42014D, "Rights", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECTS(0x42014E, "Objects", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OPERATIONS(0x42014F, "Operations", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        RIGHT(0x420150, "Right", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ENDPOINT_ROLE(0x420151, "EndpointRole", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DEFAULTS_INFORMATION(0x420152, "DefaultsInformation", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_DEFAULTS(0x420153, "ObjectDefaults", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        EPHEMERAL(0x420154, "Ephemeral", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SERVER_HASHED_PASSWORD(0x420155, "ServerHashedPassword", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ONE_TIME_PASSWORD(0x420156, "OneTimePassword", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        HASHED_PASSWORD(0x420157, "HashedPassword", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ADJUSTMENT_TYPE(0x420158, "AdjustmentType", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_INTERFACE(0x420159, "Pkcs11Interface", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_FUNCTION(0x42015A, "Pkcs11Function", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_INPUT_PARAMETERS(0x42015B, "Pkcs11InputParameters", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_OUTPUT_PARAMETERS(0x42015C, "Pkcs11OutputParameters", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS_11_RETURN_CODE(0x42015D, "Pkcs11ReturnCode", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTECTION_STORAGE_MASK(0x42015E, "ProtectionStorageMask", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROTECTION_STORAGE_MASKS(0x42015F, "ProtectionStorageMasks", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTEROP_FUNCTION(0x420160, "InteropFunction", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTEROP_IDENTIFIER(0x420161, "InteropIdentifier", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ADJUSTMENT_VALUE(0x420162, "AdjustmentValue", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        COMMON_PROTECTION_STORAGE_MASKS(0x420163, "CommonProtectionStorageMasks", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PRIVATE_PROTECTION_STORAGE_MASKS(0x420164, "PrivateProtectionStorageMasks", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PUBLIC_PROTECTION_STORAGE_MASKS(0x420165, "PublicProtectionStorageMasks", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_GROUPS(0x420166, "ObjectGroups", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        OBJECT_TYPES(0x420167, "ObjectTypes", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CONSTRAINTS(0x420168, "Constraints", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CONSTRAINT(0x420169, "Constraint", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_INTERVAL(0x42016A, "RotateInterval", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_AUTOMATIC(0x42016B, "RotateAutomatic", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_OFFSET(0x42016C, "RotateOffset", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_DATE(0x42016D, "RotateDate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_GENERATION(0x42016E, "RotateGeneration", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_NAME(0x42016F, "RotateName", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ROTATE_NAME_VALUE(0x420170, "RotateNameValue", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        ROTATE_NAME_TYPE(0x420171, "RotateNameType", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        ROTATE_LATEST(0x420172, "RotateLatest", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYNCHRONOUS_REQUEST(0x420173, "AsynchronousRequest", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        SUBMISSION_DATE(0x420174, "SubmissionDate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        PROCESSING_STAGE(0x420175, "ProcessingStage", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ASYNCHRONOUS_CORRELATION_VALUES(0x420176, "AsynchronousCorrelationValues", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        CERTIFICATE_LINK(0x420190, "CertificateLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CHILD_LINK(0x420191, "ChildLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DERIVATION_OBJECT_LINK(0x420192, "DerivationObjectLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DERIVED_OBJECT_LINK(0x420193, "DerivedObjectLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        NEXT_LINK(0x420194, "NextLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PARENT_LINK(0x420195, "ParentLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PKCS_12_CERTIFICATE_LINK(0x420196, "Pkcs12CertificateLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PKCS_12_PASSWORD_LINK(0x420197, "Pkcs12PasswordLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PREVIOUS_LINK(0x420198, "PreviousLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PRIVATE_KEY_LINK(0x420199, "PrivateKeyLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PUBLIC_KEY_LINK(0x42019A, "PublicKeyLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        REPLACED_OBJECT_LINK(0x42019B, "ReplacedObjectLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        REPLACEMENT_OBJECT_LINK(0x42019C, "ReplacementObjectLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        WRAPPING_KEY_LINK(0x42019D, "WrappingKeyLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OBJECT_CLASS(0x42019E, "ObjectClass", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OBJECT_CLASS_MASK(0x42019F, "ObjectClassMask", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREDENTIAL_LINK(0x4201A0, "CredentialLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PASSWORD_CREDENTIAL(0x4201A1, "PasswordCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PASSWORD_SALT(0x4201A2, "PasswordSalt", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PASSWORD_SALT_ALGORITHM(0x4201A3, "PasswordSaltAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SALTED_PASSWORD(0x4201A4, "SaltedPassword", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PASSWORD_LINK(0x4201A5, "PasswordLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DEVICE_CREDENTIAL(0x4201A6, "DeviceCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_CREDENTIAL(0x4201A7, "OtpCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_ALGORITHM(0x4201A8, "OtpAlgorithm", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_DIGEST(0x4201A9, "OtpDigest", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_SERIAL(0x4201AA, "OtpSerial", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_SEED(0x4201AB, "OtpSeed", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_INTERVAL(0x4201AC, "OtpInterval", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_DIGITS(0x4201AD, "OtpDigits", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        OTP_COUNTER(0x4201AE, "OtpCounter", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        HASHED_PASSWORD_CREDENTIAL(0x4201AF, "HashedPasswordCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        HASHED_USERNAME_PASSWORD(0x4201B0, "HashedUsernamePassword", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        HASHED_PASSWORD_USERNAME(0x4201B1, "HashedPasswordUsername", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CREDENTIAL_INFORMATION(0x4201B2, "CredentialInformation", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        GROUP_LINK(0x4201B3, "GroupLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SPLIT_KEY_BASE_LINK(0x4201B4, "SplitKeyBaseLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        JOINED_SPLIT_KEY_PARTS_LINK(0x4201B5, "JoinedSplitKeyPartsLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SPLIT_KEY_POLYNOMIAL(0x4201B6, "SplitKeyPolynomial", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DEACTIVATION_MESSAGE(0x4201B7, "DeactivationMessage", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DEACTIVATION_REASON(0x4201B8, "DeactivationReason", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DEACTIVATION_REASON_CODE(0x4201B9, "DeactivationReasonCode", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CERTIFICATE_SUBJECT_DN(0x4201BA, "CertificateSubjectDn", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CERTIFICATE_ISSUER_DN(0x4201BB, "CertificateIssuerDn", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CERTIFICATE_REQUEST_LINK(0x4201BC, "CertificateRequestLink", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        CERTIFY_COUNTER(0x4201BD, "CertifyCounter", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DECRYPT_COUNTER(0x4201BE, "DecryptCounter", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ENCRYPT_COUNTER(0x4201BF, "EncryptCounter", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SIGN_COUNTER(0x4201C0, "SignCounter", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SIGNATURE_VERIFY_COUNTER(0x4201C1, "SignatureVerifyCounter", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        NIST_SECURITY_CATEGORY(0x4201C2, "NistSecurityCategory", KmipSpec.UnknownVersion, KmipSpec.V3_0);


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
