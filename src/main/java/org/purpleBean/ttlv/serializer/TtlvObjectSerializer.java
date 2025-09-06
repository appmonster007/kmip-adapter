package org.purpleBean.ttlv.serializer;

import org.purpleBean.ttlv.TtlvConstants;
import org.purpleBean.ttlv.TtlvObject;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

/**
 * Thread-safe serializer for TTLVObject that converts it to byte array with padding
 * to ensure the length is always a multiple of 8.
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
public final class TtlvObjectSerializer {

    /**
     * Utility method to get the padding size used by this serializer.
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
     * Serializes a TTLVObject to byte array with padding.
     *
     * @param ttlvObject the TTLVObject to serialize
     * @return byte array with padding to ensure length is multiple of 8
     * @throws IllegalArgumentException if ttlvObject is null or invalid
     * @throws NullPointerException     if ttlvObject is null
     */
    public byte[] serialize(TtlvObject ttlvObject) {
        Objects.requireNonNull(ttlvObject, TtlvConstants.ERROR_NULL_TTLV_OBJECT);
        validateInput(ttlvObject);

        byte[] value = ttlvObject.getValue();
        int valueLength = value.length;

        // Calculate total length including padding
        int totalLength = calculateTotalLength(valueLength);

        // Create byte buffer with big-endian order
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        buffer.order(TtlvConstants.BYTE_ORDER);

        // Write header: tag (3 bytes) + type (1 byte) + length (4 bytes)
        writeHeader(buffer, ttlvObject);

        // Write value
        if (valueLength > 0) {
            buffer.put(value);
        }

        // Add padding to make total length multiple of 8
        addPadding(buffer, valueLength);

        return buffer.array();
    }

    /**
     * Serializes multiple TTLVObjects to a single byte array.
     *
     * @param ttlvObjects array of TTLVObjects to serialize
     * @return concatenated byte array with padding
     * @throws IllegalArgumentException if ttlvObjects is null or contains null elements
     * @throws NullPointerException     if ttlvObjects is null
     */
    public byte[] serializeMultiple(TtlvObject... ttlvObjects) {
        Objects.requireNonNull(ttlvObjects, "TTLVObjects array cannot be null");

        // Calculate total length needed
        int totalLength = 0;
        for (TtlvObject ttlvObject : ttlvObjects) {
            Objects.requireNonNull(ttlvObject, "TTLVObject cannot be null");
            validateInput(ttlvObject);
            int valueLength = ttlvObject.getValue().length;
            totalLength += calculateTotalLength(valueLength);
        }

        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        buffer.order(TtlvConstants.BYTE_ORDER);

        // Serialize each object
        for (TtlvObject ttlvObject : ttlvObjects) {
            writeHeader(buffer, ttlvObject);

            byte[] value = ttlvObject.getValue();
            if (value.length > 0) {
                buffer.put(value);
            }

            addPadding(buffer, value.length);
        }

        return buffer.array();
    }

    /**
     * Calculates the total length including padding for a given value length.
     *
     * @param valueLength the length of the value
     * @return total length including padding
     */
    private int calculateTotalLength(int valueLength) {
        int contentLength = TtlvConstants.HEADER_SIZE + valueLength;
        return TtlvConstants.calculatePaddedLength(contentLength);
    }

    /**
     * Writes the TTLV header (tag + type + length) to the buffer.
     *
     * @param buffer     the byte buffer to write to
     * @param ttlvObject the TTLVObject to extract header from
     */
    private void writeHeader(ByteBuffer buffer, TtlvObject ttlvObject) {
        // Write tag (3 bytes)
        byte[] tag = ttlvObject.getTag();
        if (tag.length != TtlvConstants.TAG_SIZE) {
            throw new IllegalArgumentException(
                    String.format("Tag must be exactly %d bytes, got %d", TtlvConstants.TAG_SIZE, tag.length));
        }
        buffer.put(tag);

        // Write type (1 byte)
        buffer.put(ttlvObject.getType());

        // Write length (4 bytes, big-endian)
        int valueLength = ttlvObject.getValue() != null ? ttlvObject.getValue().length : 0;
        buffer.putInt(valueLength);
    }

    /**
     * Adds padding bytes to make the total length a multiple of 8.
     *
     * @param buffer      the byte buffer to add padding to
     * @param valueLength the length of the value that was written
     */
    private void addPadding(ByteBuffer buffer, int valueLength) {
        int contentLength = TtlvConstants.HEADER_SIZE + valueLength;
        int paddingNeeded = TtlvConstants.calculatePaddedLength(contentLength) - contentLength;

        if (paddingNeeded > 0) {
            byte[] padding = new byte[paddingNeeded];
            Arrays.fill(padding, TtlvConstants.PADDING_BYTE); // Zero padding
            buffer.put(padding);

            // Debug: Added paddingNeeded padding bytes
        }
    }

    /**
     * Validates the input TTLVObject.
     *
     * @param ttlvObject the object to validate
     * @throws IllegalArgumentException if the object is invalid
     */
    private void validateInput(TtlvObject ttlvObject) {
        TtlvConstants.validateTag(ttlvObject.getTag());

        // Additional validation is handled by TtlvObject constructor
        // This method is kept for potential future validation needs
    }
}
