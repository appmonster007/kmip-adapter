package org.purpleBean.ttlv;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a TTLV (Tag-Type-Length-Value) object used in KMIP protocol.
 * This is an immutable data class that encapsulates the TTLV format structure.
 *
 */
@Getter
@Setter
public class TtlvObject {
    private byte[] tag;
    private byte type;
    private int length;
    private byte[] value;

    /**
     * Constructs a TTLV object with the specified parameters.
     *
     * @param tag    the tag bytes (must be exactly 3 bytes)
     * @param type   the type byte
     * @param length the length of the value
     * @param value  the value bytes (can be null or empty)
     * @throws IllegalArgumentException if tag is null, not 3 bytes, length is negative, or length is not equal to value array size
     */
    public TtlvObject(byte[] tag, byte type, int length, byte[] value) {
        TtlvConstants.validateTag(tag);
        TtlvConstants.validateLength(length);

        this.tag = Arrays.copyOf(tag, tag.length); // Defensive copy
        this.type = type;
        this.length = length;
        this.value = value != null ? Arrays.copyOf(value, value.length) : new byte[0]; // Defensive copy

        // Validate that length matches actual value length
        if (this.value.length != length) {
            throw new IllegalArgumentException(
                    String.format(TtlvConstants.ERROR_LENGTH_MISMATCH + " (expected: %d, actual: %d)",
                            length, this.value.length));
        }
    }

    /**
     * Constructs a TTLV object with the specified tag, type, and value.
     * The length is automatically calculated from the value array.
     *
     * @param tag   the tag bytes (must be exactly 3 bytes)
     * @param type  the type byte
     * @param value the value bytes (can be null or empty)
     * @throws IllegalArgumentException if tag is null or not 3 bytes
     */
    public TtlvObject(byte[] tag, byte type, byte[] value) {
        this(tag, type, value != null ? value.length : 0, value);
    }

    /**
     * Checks if this TTLV object has an empty value.
     *
     * @return true if the value is empty, false otherwise
     */
    public boolean hasEmptyValue() {
        return value.length == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TtlvObject that = (TtlvObject) obj;
        return type == that.type &&
                length == that.length &&
                Arrays.equals(tag, that.tag) &&
                Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type, length);
        result = 31 * result + Arrays.hashCode(tag);
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }
}
