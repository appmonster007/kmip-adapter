package org.purpleBean.kmip.codec.ttlv;

import java.nio.ByteOrder;

/**
 * Common constants used across TTLV serialization and deserialization.
 * This class centralizes all TTLV format-related constants to ensure consistency
 * and maintainability across the codebase.
 */
public final class TtlvConstants {

    /**
     * Size of the tag field in bytes
     */
    public static final int TAG_SIZE = 3;

    // TTLV Format Constants
    /**
     * Size of the type field in bytes
     */
    public static final int TYPE_SIZE = 1;
    /**
     * Size of the length field in bytes
     */
    public static final int LENGTH_SIZE = 4;
    /**
     * Total size of the TTLV header (tag + type + length)
     */
    public static final int HEADER_SIZE = TAG_SIZE + TYPE_SIZE + LENGTH_SIZE;
    /**
     * Minimum size for a valid TTLV object (header only)
     */
    public static final int MINIMUM_TTLV_SIZE = HEADER_SIZE;
    /**
     * Error message for data too short
     */
    public static final String ERROR_DATA_TOO_SHORT = "Data too short for TTLV format. Minimum required: " + MINIMUM_TTLV_SIZE + " bytes, got: %d bytes";

    // Byte Order
    /**
     * Padding size requirement - all TTLV data must be padded to multiples of this value
     */
    public static final int PADDING_SIZE = 8;

    // Validation Constants
    /**
     * Byte order used for TTLV format (big-endian)
     */
    public static final ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;
    /**
     * Maximum value length (2^32 - 1, as length is stored in 4 bytes)
     */
    public static final long MAX_VALUE_LENGTH = 0xFFFFFFFFL;

    // Padding Constants
    /**
     * Padding byte value (zero padding)
     */
    public static final byte PADDING_BYTE = 0x00;

    // Error Messages
    /**
     * Error message for null TTLV object
     */
    public static final String ERROR_NULL_TTLV_OBJECT = "TTLVObject cannot be null";

    /**
     * Error message for null tag
     */
    public static final String ERROR_NULL_TAG = "Tag cannot be null";

    /**
     * Error message for invalid tag size
     */
    public static final String ERROR_INVALID_TAG_SIZE = "Tag must be " + TAG_SIZE + " bytes long";

    /**
     * Error message for negative length
     */
    public static final String ERROR_NEGATIVE_LENGTH = "Length cannot be negative";

    /**
     * Error message for length mismatch
     */
    public static final String ERROR_LENGTH_MISMATCH = "Value length does not match specified length";

    /**
     * Error message for null data
     */
    public static final String ERROR_NULL_DATA = "Data cannot be null";

    /**
     * Error message for empty data
     */
    public static final String ERROR_EMPTY_DATA = "Data cannot be empty";
    /**
     * Error message for invalid padding
     */
    public static final String ERROR_INVALID_PADDING = "Data length must be multiple of " + PADDING_SIZE + ". Got: %d bytes";
    /**
     * Error message for insufficient data for value
     */
    public static final String ERROR_INSUFFICIENT_VALUE_DATA = "Insufficient data for value. Required: %d bytes, available: %d bytes";
    /**
     * Error message for insufficient data for padding
     */
    public static final String ERROR_INSUFFICIENT_PADDING_DATA = "Insufficient data for padding. Required: %d bytes, available: %d bytes";
    /**
     * Error message for invalid value length
     */
    public static final String ERROR_INVALID_VALUE_LENGTH = "Invalid value length: %d";

    // Private constructor to prevent instantiation
    private TtlvConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // Utility Methods

    /**
     * Calculates the padded length to make it a multiple of PADDING_SIZE.
     *
     * @param length the original length
     * @return length padded to multiple of PADDING_SIZE
     */
    public static int calculatePaddedLength(int length) {
        return ((length + PADDING_SIZE - 1) / PADDING_SIZE) * PADDING_SIZE;
    }

    /**
     * Checks if a given length is properly padded (multiple of PADDING_SIZE).
     *
     * @param length the length to check
     * @return true if the length is a multiple of PADDING_SIZE, false otherwise
     */
    public static boolean isProperlyPadded(int length) {
        return length % PADDING_SIZE == 0;
    }

    /**
     * Calculates the minimum size needed for a TTLV object with the given value length.
     *
     * @param valueLength the length of the value
     * @return minimum size including padding
     */
    public static int calculateMinimumSize(int valueLength) {
        int contentLength = HEADER_SIZE + valueLength;
        return calculatePaddedLength(contentLength);
    }

    /**
     * Validates that a length is within valid bounds.
     *
     * @param length the length to validate
     * @throws IllegalArgumentException if the length is invalid
     */
    public static void validateLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_LENGTH);
        }
        if (length > MAX_VALUE_LENGTH) {
            throw new IllegalArgumentException("Value length too large: " + length + " (max: " + MAX_VALUE_LENGTH + ")");
        }
    }

    /**
     * Validates that a tag array has the correct size.
     *
     * @param tag the tag array to validate
     * @throws IllegalArgumentException if the tag is invalid
     */
    public static void validateTag(byte[] tag) {
        if (tag == null) {
            throw new IllegalArgumentException(ERROR_NULL_TAG);
        }
        if (tag.length != TAG_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_TAG_SIZE);
        }
    }

    /**
     * Validates that data length is a multiple of PADDING_SIZE.
     *
     * @param dataLength the data length to validate
     * @throws IllegalArgumentException if the data length is invalid
     */
    public static void validateDataLength(int dataLength) {
        if (dataLength % PADDING_SIZE != 0) {
            throw new IllegalArgumentException(String.format(ERROR_INVALID_PADDING, dataLength));
        }
    }

    /**
     * Validates that data has minimum required size.
     *
     * @param dataLength the data length to validate
     * @throws IllegalArgumentException if the data length is too short
     */
    public static void validateMinimumDataLength(int dataLength) {
        if (dataLength < MINIMUM_TTLV_SIZE) {
            throw new IllegalArgumentException(String.format(ERROR_DATA_TOO_SHORT, dataLength));
        }
    }
}
