package org.purpleBean.kmip.codec.ttlv;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.purpleBean.kmip.EncodingType;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Stream;

/**
 * TTLV object that can be built via a builder and converted to/from bytes.
 */
@Getter
@ToString
public final class TtlvObject {

    private final byte type;
    private final int length;
    private final byte[] value;
    private byte[] tag;

    @Builder
    private TtlvObject(byte[] tag, byte type, byte[] value) {
        TtlvConstants.validateTag(tag);
        this.tag = Arrays.copyOf(tag, tag.length);
        this.type = type;
        this.length = value.length;
        this.value = Arrays.copyOf(value, value.length);
    }

    public static byte[] toBytesMultiple(TtlvObject... ttlvObjects) {
        return serializeMultiple(ttlvObjects);
    }

    private static byte[] serializeMultiple(TtlvObject... ttlvObjects) {
        Objects.requireNonNull(ttlvObjects, "TTLVObjects array cannot be null");

        int totalLength = Stream.of(ttlvObjects).peek(Objects::requireNonNull).mapToInt(o -> calculateTotalLength(o.getValue().length)).sum();

        ByteBuffer buffer = ByteBuffer.allocate(totalLength).order(TtlvConstants.BYTE_ORDER);

        for (TtlvObject obj : ttlvObjects) {
            writeHeader(buffer, obj);
            byte[] v = obj.getValue();
            if (v.length > 0) buffer.put(v);
            addPadding(buffer, v.length);
        }

        return buffer.array();
    }

    public static TtlvObject fromBytes(byte[] data) {
        Objects.requireNonNull(data, TtlvConstants.ERROR_NULL_DATA);
        validateInput(data);
        return fromBuffer(ByteBuffer.wrap(data).order(TtlvConstants.BYTE_ORDER));
    }

    public static List<TtlvObject> fromBytesMultiple(byte[] data) {
        Objects.requireNonNull(data, TtlvConstants.ERROR_NULL_DATA);
        validateInput(data);

        final List<TtlvObject> result = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.wrap(data).order(TtlvConstants.BYTE_ORDER);

        while (buffer.hasRemaining()) {
            result.add(fromBuffer(buffer));
        }
        return Collections.unmodifiableList(result);
    }

    public static TtlvObject fromBuffer(ByteBuffer buffer) {
        if (buffer.remaining() < TtlvConstants.HEADER_SIZE) {
            throw new IllegalArgumentException(String.format("Insufficient data for TTLV header. Required: %d, available: %d", TtlvConstants.HEADER_SIZE, buffer.remaining()));
        }

        byte[] tag = new byte[TtlvConstants.TAG_SIZE];
        buffer.get(tag);
        byte type = buffer.get();
        int valueLength = buffer.getInt();

        if (valueLength < 0) {
            throw new IllegalArgumentException(String.format(TtlvConstants.ERROR_INVALID_VALUE_LENGTH, valueLength));
        }
        if (buffer.remaining() < valueLength) {
            throw new IllegalArgumentException(String.format(TtlvConstants.ERROR_INSUFFICIENT_VALUE_DATA, valueLength, buffer.remaining()));
        }

        byte[] value = new byte[valueLength];
        if (valueLength > 0) buffer.get(value);
        skipPadding(buffer, valueLength);

        return TtlvObject.builder().tag(tag).type(type).value(value).build();
    }

    private static void writeHeader(ByteBuffer buffer, TtlvObject obj) {
        byte[] tag = obj.tag;
        if (tag.length != TtlvConstants.TAG_SIZE) {
            throw new IllegalArgumentException(String.format("Tag must be %d bytes, got %d", TtlvConstants.TAG_SIZE, tag.length));
        }
        buffer.put(tag);
        buffer.put(obj.type);
        buffer.putInt(obj.value != null ? obj.value.length : 0);
    }

    private static void addPadding(ByteBuffer buffer, int valueLength) {
        int contentLength = TtlvConstants.HEADER_SIZE + valueLength;
        int paddingNeeded = TtlvConstants.calculatePaddedLength(contentLength) - contentLength;
        if (paddingNeeded > 0) {
            byte[] padding = new byte[paddingNeeded];
            Arrays.fill(padding, TtlvConstants.PADDING_BYTE);
            buffer.put(padding);
        }
    }

    // Serialization

    private static void skipPadding(ByteBuffer buffer, int valueLength) {
        int contentLength = TtlvConstants.HEADER_SIZE + valueLength;
        int totalPaddedLength = TtlvConstants.calculatePaddedLength(contentLength);
        int paddingToSkip = totalPaddedLength - contentLength;
        if (paddingToSkip > 0) {
            if (buffer.remaining() < paddingToSkip) {
                throw new IllegalArgumentException(String.format(TtlvConstants.ERROR_INSUFFICIENT_PADDING_DATA, paddingToSkip, buffer.remaining()));
            }
            buffer.position(buffer.position() + paddingToSkip);
        }
    }

    private static int calculateTotalLength(int valueLength) {
        int contentLength = TtlvConstants.HEADER_SIZE + valueLength;
        return TtlvConstants.calculatePaddedLength(contentLength);
    }

    private static void validateInput(byte[] data) {
        validateInput(data.length);
    }

    private static void validateInput(int length) {
        if (length == 0) throw new IllegalArgumentException(TtlvConstants.ERROR_EMPTY_DATA);
        TtlvConstants.validateMinimumDataLength(length);
        TtlvConstants.validateDataLength(length);
    }

    // Defensive getters for array/list fields (Lombok won't generate these because we define them)
    public byte[] getTag() {
        return Arrays.copyOf(tag, tag.length);
    }

    public void setTag(byte[] tag) {
        if (tag.length != TtlvConstants.TAG_SIZE) {
            throw new IllegalArgumentException(String.format("Tag must be %d bytes, got %d", TtlvConstants.TAG_SIZE, tag.length));
        }
        this.tag = Arrays.copyOf(tag, tag.length);
    }

    public byte[] getValue() {
        return Arrays.copyOf(value, value.length);
    }

    // Helpers

    public byte[] getPrimitiveValue() {
        if (isStructure()) {
            throw new IllegalStateException("This object contains nested values. Use getNestedValue().");
        }
        return Arrays.copyOf(value, value.length);
    }

    public List<TtlvObject> getNestedValue() {
        if (!isStructure()) {
            throw new IllegalStateException("This object does not contain nested values. Use getPrimitiveValue().");
        }
        return fromBytesMultiple(value);
    }

    public boolean hasEmptyValue() {
        return value.length == 0;
    }

    public boolean isStructure() {
        return type == EncodingType.STRUCTURE.getTypeValue();
    }

    // Deserialization

    public byte[] toBytes() {
        byte[] valueBytes = getValue();
        int totalLength = calculateTotalLength(valueBytes.length);
        ByteBuffer buffer = ByteBuffer.allocate(totalLength).order(TtlvConstants.BYTE_ORDER);

        writeHeader(buffer, this);
        if (valueBytes.length > 0) buffer.put(valueBytes);
        addPadding(buffer, valueBytes.length);

        return buffer.array();
    }

    public ByteBuffer toByteBuffer() {
        return ByteBuffer.wrap(toBytes()).order(TtlvConstants.BYTE_ORDER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TtlvObject that = (TtlvObject) o;
        return type == that.type && length == that.length && Arrays.equals(tag, that.tag) && Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type, length);
        result = 31 * result + Arrays.hashCode(tag);
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    public String getByteString() {
        HexFormat hexFormat = HexFormat.of();
        int paddedSize = TtlvConstants.calculatePaddedLength(TtlvConstants.HEADER_SIZE + length);
        ByteBuffer buffer = ByteBuffer.allocate(paddedSize);
        buffer.put(tag);
        buffer.put(type);
        buffer.putInt(length);
        buffer.put(value);

        return hexFormat.formatHex(buffer.array());
    }

    public String getStructuredByteString() {
        return getStructuredByteStringAtLevel(0);
    }

    private String getStructuredByteStringAtLevel(int level) {
        StringBuilder sb = new StringBuilder();
        String indent = "\t".repeat(level);

        HexFormat hexFormat = HexFormat.of();
        sb.append(indent).append(hexFormat.formatHex(tag))
                .append(" ").append(hexFormat.toHexDigits(type))
                .append(" ").append(hexFormat.toHexDigits(length));

        if (!hasEmptyValue()) {
            if (type == EncodingType.STRUCTURE.getTypeValue()) {
                List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(value);
                for (var ttlvobj : nestedObjects) {
                    sb.append("\n").append(ttlvobj.getStructuredByteStringAtLevel(level + 1));
                }
            } else {
                sb.append("\n").append(indent).append(hexFormat.formatHex(value));
            }
        }
        return sb.toString();
    }

    public String getStructuredString() {
        return getStructuredStringAtLevel(0);
    }

    private String getStructuredStringAtLevel(int level) {
        StringBuilder sb = new StringBuilder();
        String indent = "\t".repeat(level);

        HexFormat hexFormat = HexFormat.of();
        sb.append(indent).append(String.format("Tag : (0x%s)\n", hexFormat.formatHex(tag)))
                .append(indent).append(String.format("Type : %s\n", EncodingType.fromTypeValue(type).get().getDescription()))
                .append(indent).append(String.format("Length : %s\n", length));

        if (hasEmptyValue()) {
            sb.append(indent).append("Value : null\n");
        } else {
            if (type == EncodingType.STRUCTURE.getTypeValue()) {
                List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(value);
                sb.append(indent).append("Value : \n");
                for (var ttlvobj : nestedObjects) {
                    sb.append(String.format("%s", ttlvobj.getStructuredStringAtLevel(level + 1)));
                }
            } else {
                sb.append(indent).append(String.format("Value : %s\n", hexFormat.formatHex(value)));
            }
        }
        return sb.toString();
    }

}
