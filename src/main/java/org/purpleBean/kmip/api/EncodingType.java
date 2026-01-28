package org.purpleBean.kmip.api;

import lombok.Getter;
import org.purpleBean.kmip.model.core.type.Offset;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * An enumeration representing the data types used in the KMIP (Key Management
 * Interoperability Protocol) TTLV (Tag-Type-Length-Value) encoding scheme.
 * <p>
 * Each constant in this enum corresponds to a specific data type defined in the
 * KMIP specification, along with its byte representation and size characteristics.
 *
 * @see KmipDataType
 * @see <a href="https://docs.oasis-open.org/kmip/spec/v1.2/os/kmip-spec-v1.2-os.html#_Toc398110633">KMIP 1.2 Specification - TTLV Encoding</a>
 */
@Getter
public enum EncodingType {
    /**
     * A container for other TTLV items. Its length is the total size of the contained items.
     */
    STRUCTURE((byte) 0x01, "Structure", -1, KmipDataType[].class),
    /**
     * A 32-bit signed integer.
     */
    INTEGER((byte) 0x02, "Integer", 4, Integer.class),
    /**
     * A 64-bit signed integer.
     */
    LONG_INTEGER((byte) 0x03, "LongInteger", 8, Long.class),
    /**
     * A variable-length signed integer.
     */
    BIG_INTEGER((byte) 0x04, "BigInteger", -1, BigInteger.class),
    /**
     * A 32-bit signed integer representing an enumerated value.
     */
    ENUMERATION((byte) 0x05, "Enumeration", 4, KmipEnumeration.Value.class),
    /**
     * An 8-byte value where the least significant bit of the last byte is 1 for true and 0 for false.
     */
    BOOLEAN((byte) 0x06, "Boolean", 8, Boolean.class),
    /**
     * A variable-length string of UTF-8 characters.
     */
    TEXT_STRING((byte) 0x07, "TextString", -1, String.class),
    /**
     * A variable-length string of bytes.
     */
    BYTE_STRING((byte) 0x08, "ByteString", -1, ByteBuffer.class),
    /**
     * A 64-bit integer representing the number of microseconds since the Unix epoch.
     */
    DATE_TIME((byte) 0x09, "DateTime", 8, Offset.class),
    /**
     * A 32-bit unsigned integer representing a duration in seconds.
     */
    INTERVAL((byte) 0x0A, "Interval", 4, Integer.class),
    ;

    /**
     * Constant indicating a variable-length encoding.
     */
    private static final int VARIABLE_LENGTH = -1;

    /**
     * Map for fast lookup of an {@link EncodingType} by its byte value.
     */
    private static final Map<Byte, EncodingType> VALUE_MAP = new HashMap<>();
    /**
     * Map for fast lookup of an {@link EncodingType} by its description string.
     */
    private static final Map<String, EncodingType> DESCRIPTION_MAP = new HashMap<>();

    static {
        for (EncodingType encodingType : values()) {
            VALUE_MAP.put(encodingType.typeValue, encodingType);
            DESCRIPTION_MAP.put(encodingType.description, encodingType);
        }
    }

    /**
     * The 1-byte value that represents this encoding type in a TTLV structure.
     */
    private final byte typeValue;
    /**
     * A human-readable description of this encoding type.
     */
    private final String description;
    /**
     * The raw byte size of the value field for fixed-size types. For variable-size types, this is -1.
     */
    private final int rawByteSize;
    /**
     * The corresponding expected class for encoded type
     */
    private final Class<?> clazz;

    /**
     * Constructs an EncodingType with the specified parameters.
     *
     * @param typeValue   the byte value representing this encoding type.
     * @param description a human-readable description of the type.
     * @param rawByteSize the raw byte size of the value, or -1 for variable-length types.
     * @param clazz       the corresponding expected class for encoded type
     */
    EncodingType(byte typeValue, String description, int rawByteSize, Class<?> clazz) {
        this.typeValue = typeValue;
        this.description = description;
        this.rawByteSize = rawByteSize;
        this.clazz = clazz;
    }

    /**
     * Gets the {@link EncodingType} corresponding to a given byte value.
     *
     * @param typeValue the byte value to look up.
     * @return an {@link Optional} containing the {@link EncodingType} if found, otherwise an empty Optional.
     */
    public static Optional<EncodingType> fromTypeValue(byte typeValue) {
        return Optional.ofNullable(VALUE_MAP.get(typeValue));
    }

    /**
     * Gets the {@link EncodingType} corresponding to a given name or description.
     *
     * @param name the enum name or description string to look up.
     * @return an {@link Optional} containing the {@link EncodingType} if found, otherwise an empty Optional.
     */
    public static Optional<EncodingType> fromName(String name) {
        // First try to find by enum name
        try {
            return Optional.of(EncodingType.valueOf(name));
        } catch (IllegalArgumentException e) {
            // If not found by enum name, try by description
            return Optional.ofNullable(DESCRIPTION_MAP.get(name));
        }
    }

    /**
     * Checks if a given byte value represents a valid {@link EncodingType}.
     *
     * @param typeValue the byte value to check.
     * @return {@code true} if the byte value corresponds to a valid encoding type, {@code false} otherwise.
     */
    public static boolean isValidTypeValue(byte typeValue) {
        return VALUE_MAP.containsKey(typeValue);
    }

    /**
     * Determines if this encoding type has a fixed length.
     *
     * @return {@code true} if this encoding type has a fixed length, {@code false} if it is variable-length.
     */
    public boolean isFixedLength() {
        return rawByteSize != VARIABLE_LENGTH;
    }

    /**
     * Returns the description of the encoding type for JSON serialization.
     *
     * @return The human-readable description string.
     */
    public String toString() {
        return this.description;
    }
}
