package org.purpleBean.ttlv.deserializer;

import org.purpleBean.ttlv.TtlvConstants;
import org.purpleBean.ttlv.TtlvObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Thread-safe deserializer for TTLVObject that converts byte array back to TTLVObject instances.
 * Handles padding and validates the TTLV format during deserialization.
 *
 * <p>TTLV Format:
 * <ul>
 *   <li>Tag: 3 bytes</li>
 *   <li>Type: 1 byte</li>
 *   <li>Length: 4 bytes (big-endian)</li>
 *   <li>Value: variable length</li>
 *   <li>Padding: to make total length multiple of 8</li>
 * </ul>
 *
 * <p>This class is thread-safe and immutable.
 */
public final class TtlvObjectDeserializer {

    /**
     * Utility method to get the padding size used by this deserializer.
     *
     * @return the padding size (8)
     */
    public static int getPaddingSize() {
        return TtlvConstants.PADDING_SIZE;
    }

    /**
     * Utility method to check if a length is properly padded.
     *
     * @param length the length to check
     * @return true if the length is a multiple of 8
     */
    public static boolean isProperlyPadded(int length) {
        return TtlvConstants.isProperlyPadded(length);
    }

    /**
     * Utility method to calculate the minimum size needed for a TTLV object.
     *
     * @param valueLength the length of the value
     * @return minimum size including padding
     */
    public static int calculateMinimumSize(int valueLength) {
        return TtlvConstants.calculateMinimumSize(valueLength);
    }

    /**
     * Deserializes a byte array to a single TTLVObject.
     *
     * @param data the byte array to deserialize
     * @return the deserialized TTLVObject
     * @throws IllegalArgumentException if data is null, empty, invalid format, or data cannot be properly deserialized
     * @throws NullPointerException     if data is null
     */
    public TtlvObject deserialize(byte[] data) {
        Objects.requireNonNull(data, TtlvConstants.ERROR_NULL_DATA);
        validateInput(data);

        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(TtlvConstants.BYTE_ORDER);

        return deserializeFromBuffer(buffer);
    }

    /**
     * Deserializes a byte array to multiple TTLVObjects.
     *
     * @param data the byte array containing multiple TTLVObjects
     * @return unmodifiable list of deserialized TTLVObjects
     * @throws IllegalArgumentException if data is null, empty, invalid format, or data cannot be properly deserialized
     * @throws NullPointerException     if data is null
     */
    public List<TtlvObject> deserializeMultiple(byte[] data) {
        Objects.requireNonNull(data, TtlvConstants.ERROR_NULL_DATA);
        validateInput(data);

        List<TtlvObject> result = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(TtlvConstants.BYTE_ORDER);

        while (buffer.hasRemaining()) {
            TtlvObject ttlvObject = deserializeFromBuffer(buffer);
            result.add(ttlvObject);
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Deserializes a TTLVObject from a ByteBuffer.
     *
     * @param buffer the ByteBuffer containing the TTLV data
     * @return the deserialized TTLVObject
     * @throws IllegalArgumentException if the buffer doesn't contain enough data
     */
    private TtlvObject deserializeFromBuffer(ByteBuffer buffer) {
        if (buffer.remaining() < TtlvConstants.HEADER_SIZE) {
            throw new IllegalArgumentException(
                    String.format("Insufficient data for TTLV header. Required: %d bytes, available: %d bytes",
                            TtlvConstants.HEADER_SIZE, buffer.remaining()));
        }

        // Read header: tag (3 bytes) + type (1 byte) + length (4 bytes)
        byte[] tag = new byte[TtlvConstants.TAG_SIZE];
        buffer.get(tag);

        byte type = buffer.get();

        int valueLength = buffer.getInt();

        // Validate value length
        if (valueLength < 0) {
            throw new IllegalArgumentException(String.format(TtlvConstants.ERROR_INVALID_VALUE_LENGTH, valueLength));
        }

        // Check if we have enough data for the value
        if (buffer.remaining() < valueLength) {
            throw new IllegalArgumentException(
                    String.format(TtlvConstants.ERROR_INSUFFICIENT_VALUE_DATA,
                            valueLength, buffer.remaining()));
        }

        // Read value
        byte[] value = new byte[valueLength];
        if (valueLength > 0) {
            buffer.get(value);
        }

        // Skip padding to align to next TTLV object
        skipPadding(buffer, valueLength);

        return new TtlvObject(tag, type, valueLength, value);
    }

    /**
     * Skips padding bytes to align to the next TTLV object boundary.
     *
     * @param buffer      the ByteBuffer to skip padding in
     * @param valueLength the length of the value that was read
     */
    private void skipPadding(ByteBuffer buffer, int valueLength) {
        int contentLength = TtlvConstants.HEADER_SIZE + valueLength;
        int totalPaddedLength = TtlvConstants.calculatePaddedLength(contentLength);
        int paddingToSkip = totalPaddedLength - contentLength;

        if (paddingToSkip > 0) {
            if (buffer.remaining() < paddingToSkip) {
                throw new IllegalArgumentException(
                        String.format(TtlvConstants.ERROR_INSUFFICIENT_PADDING_DATA,
                                paddingToSkip, buffer.remaining()));
            }

            // Skip padding bytes
            buffer.position(buffer.position() + paddingToSkip);

            // Debug: Skipped paddingToSkip padding bytes
        }
    }

    /**
     * Validates the input byte array.
     *
     * @param data the byte array to validate
     * @throws IllegalArgumentException if the data is invalid
     */
    private void validateInput(byte[] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException(TtlvConstants.ERROR_EMPTY_DATA);
        }

        TtlvConstants.validateMinimumDataLength(data.length);
        TtlvConstants.validateDataLength(data.length);
    }
}
