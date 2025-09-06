package org.purpleBean;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Enumeration representing KMIP encoding types for TTLV (Tag-Type-Length-Value) format.
 * Each encoding type has a specific byte value, description, and fixed byte size.
 * Variable-length encodings use -1 to indicate variable size.
 */
@ToString
@Getter
public enum EncodingType {
    /**
     * Structure type - variable size, multiples of 8
     */
    STRUCTURE((byte) 0x01, "Structure", -1),
    /**
     * Integer type - 4 bytes fixed
     */
    INTEGER((byte) 0x02, "Integer", 4),
    /**
     * Long Integer type - 8 bytes fixed
     */
    LONG_INTEGER((byte) 0x03, "Long Integer", 8),
    /**
     * Big Integer type - variable size
     */
    BIG_INTEGER((byte) 0x04, "Big Integer", -1),
    /**
     * Enumeration type - 4 bytes fixed
     */
    ENUMERATION((byte) 0x05, "Enumeration", 4),
    /**
     * Boolean type - 8 bytes fixed
     */
    BOOLEAN((byte) 0x06, "Boolean", 8),
    /**
     * Text String type - variable size
     */
    TEXT_STRING((byte) 0x07, "Text String", -1),
    /**
     * Byte String type - variable size
     */
    BYTE_STRING((byte) 0x08, "Byte String", -1),
    /**
     * Date-Time type - 8 bytes fixed
     */
    DATE_TIME((byte) 0x09, "Date-Time", 8),
    /**
     * Interval type - 4 bytes fixed
     */
    INTERVAL((byte) 0x0A, "Interval", 4);

    /**
     * Constant indicating variable length encoding
     */
    private static final int VARIABLE_LENGTH = -1;

    /**
     * Map for fast lookup by type value
     */
    private static final Map<Byte, EncodingType> VALUE_MAP = new HashMap<>();
    /**
     * Map for fast lookup by enum name
     */
    private static final Map<String, EncodingType> NAME_MAP = new HashMap<>();

    static {
        for (EncodingType encodingType : values()) {
            VALUE_MAP.put(encodingType.typeValue, encodingType);
            NAME_MAP.put(encodingType.name(), encodingType);
        }
    }

    /**
     * The byte value representing this encoding type
     */
    private final byte typeValue;
    /**
     * Human-readable description of this encoding type
     */
    private final String description;
    /**
     * The raw byte size (excluding padding). -1 indicates variable-length encoding
     */
    private final int rawByteSize;

    /**
     * Constructs an EncodingType with the specified parameters.
     *
     * @param typeValue   the byte value representing this encoding type
     * @param description human-readable description
     * @param rawByteSize the raw byte size (-1 for variable-length)
     */
    EncodingType(byte typeValue, String description, int rawByteSize) {
        this.typeValue = typeValue;
        this.description = description;
        this.rawByteSize = rawByteSize;
    }

    /**
     * Gets the encoding type by its byte value.
     *
     * @param typeValue the byte value to look up
     * @return an Optional containing the EncodingType if found, empty otherwise
     */
    public static Optional<EncodingType> fromTypeValue(byte typeValue) {
        return Optional.ofNullable(VALUE_MAP.get(typeValue));
    }

    /**
     * Gets the encoding type by its enum name.
     *
     * @param name the enum name to look up
     * @return an Optional containing the EncodingType if found, empty otherwise
     */
    public static Optional<EncodingType> fromName(String name) {
        return Optional.ofNullable(NAME_MAP.get(name));
    }

    /**
     * Checks if a given byte value represents a valid encoding type.
     *
     * @param typeValue the byte value to check
     * @return true if the byte value represents a valid encoding type
     */
    public static boolean isValidTypeValue(byte typeValue) {
        return VALUE_MAP.containsKey(typeValue);
    }

    /**
     * Determines if this encoding type has a fixed length.
     *
     * @return true if this encoding type has a fixed length, false if variable-length
     */
    public boolean isFixedLength() {
        return rawByteSize != VARIABLE_LENGTH;
    }
}
