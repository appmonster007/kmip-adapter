package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;

import java.nio.ByteBuffer;
import java.util.HexFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TtlvObject Tests")
class TtlvObjectTest {

    private static byte[] tag(int v) {
        // 3-byte tag big-endian (KMIP tag encoding)
        return new byte[]{(byte) ((v >> 16) & 0xFF), (byte) ((v >> 8) & 0xFF), (byte) (v & 0xFF)};
    }

    private static int paddedLength(int valueLen) {
        int header = 3 + 1 + 4; // tag(3) + type(1) + length(4)
        int content = header + valueLen;
        int rem = content % 8;
        return rem == 0 ? content : content + (8 - rem);
    }

    @Nested
    @DisplayName("Primitive TTLV")
    class PrimitiveTtlv {
        @Test
        @DisplayName("Serialize/deserialize INTEGER with padding")
        void integer_roundTrip() {
            byte[] tag = tag(0x420009); // arbitrary
            byte type = EncodingType.INTEGER.getTypeValue();
            byte[] value = new byte[]{0, 0, 0, 1};

            TtlvObject obj = TtlvObject.builder().tag(tag).type(type).value(value).build();
            byte[] bytes = obj.toBytes();

            assertThat(bytes.length).isEqualTo(paddedLength(value.length));

            TtlvObject restored = TtlvObject.fromBytes(bytes);
            assertThat(restored).isEqualTo(obj);
            assertThat(restored.getPrimitiveValue()).containsExactly(value);
            assertThat(restored.isStructure()).isFalse();
            assertThat(restored.hasEmptyValue()).isFalse();
        }

        @Test
        @DisplayName("Empty value should serialize with header-only padding")
        void emptyValue_serialization() {
            byte[] tag = tag(0x420000);
            byte type = EncodingType.BOOLEAN.getTypeValue();
            byte[] value = new byte[]{};

            TtlvObject obj = TtlvObject.builder().tag(tag).type(type).value(value).build();
            byte[] bytes = obj.toBytes();

            assertThat(bytes.length).isEqualTo(paddedLength(0));
            TtlvObject restored = TtlvObject.fromBytes(bytes);
            assertThat(restored).isEqualTo(obj);
            assertThat(restored.hasEmptyValue()).isTrue();
        }

        @Test
        @DisplayName("getPrimitiveValue should throw for structure")
        void primitiveGetter_throwsOnStructure() {
            byte[] child1 = TtlvObject.builder().tag(tag(0x420001)).type(EncodingType.INTEGER.getTypeValue()).value(new byte[]{0, 0, 0, 1}).build().toBytes();
            byte[] value = child1; // single child
            TtlvObject structure = TtlvObject.builder().tag(tag(0x420002)).type(EncodingType.STRUCTURE.getTypeValue()).value(value).build();

            assertThatThrownBy(structure::getPrimitiveValue).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("Structured TTLV")
    class StructuredTtlv {
        @Test
        @DisplayName("Structure with two nested children: serialize/deserialize and navigate")
        void structure_twoChildren_roundTrip() {
            TtlvObject child1 = TtlvObject.builder()
                    .tag(tag(0x420001))
                    .type(EncodingType.INTEGER.getTypeValue())
                    .value(new byte[]{0, 0, 0, 1})
                    .build();
            TtlvObject child2 = TtlvObject.builder()
                    .tag(tag(0x420002))
                    .type(EncodingType.BOOLEAN.getTypeValue())
                    .value(new byte[]{0, 0, 0, 0, 0, 0, 0, 1}) // KMIP boolean occupies 8 bytes
                    .build();

            byte[] nestedBytes = TtlvObject.toBytesMultiple(child1, child2);
            TtlvObject structure = TtlvObject.builder()
                    .tag(tag(0x420003))
                    .type(EncodingType.STRUCTURE.getTypeValue())
                    .value(nestedBytes)
                    .build();

            byte[] out = structure.toBytes();
            TtlvObject restored = TtlvObject.fromBytes(out);
            assertThat(restored.isStructure()).isTrue();

            List<TtlvObject> nested = restored.getNestedValue();
            assertThat(nested).hasSize(2);
            assertThat(nested.get(0)).isEqualTo(child1);
            assertThat(nested.get(1)).isEqualTo(child2);
        }

        @Test
        @DisplayName("getNestedValue should throw for primitive")
        void nestedGetter_throwsOnPrimitive() {
            TtlvObject primitive = TtlvObject.builder()
                    .tag(tag(0x420004))
                    .type(EncodingType.INTEGER.getTypeValue())
                    .value(new byte[]{0, 0, 0, 2})
                    .build();
            assertThatThrownBy(primitive::getNestedValue).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("Multiple objects")
    class Multiple {
        @Test
        @DisplayName("Serialize and parse multiple consecutive objects")
        void multiple_toFromBytes() {
            TtlvObject a = TtlvObject.builder().tag(tag(0x420010)).type(EncodingType.INTEGER.getTypeValue()).value(new byte[]{0, 0, 0, 3}).build();
            TtlvObject b = TtlvObject.builder().tag(tag(0x420011)).type(EncodingType.LONG_INTEGER.getTypeValue()).value(new byte[]{0, 0, 0, 0, 0, 0, 0, 5}).build();

            byte[] both = TtlvObject.toBytesMultiple(a, b);
            List<TtlvObject> list = TtlvObject.fromBytesMultiple(both);
            assertThat(list).containsExactly(a, b);
        }
    }

    @Nested
    @DisplayName("Error handling")
    class Errors {
        @Test
        @DisplayName("Builder should validate tag length")
        void builder_invalidTagLength() {
            byte[] badTag = new byte[]{0x42, 0x00}; // only 2 bytes
            assertThatThrownBy(() -> TtlvObject.builder().tag(badTag).type(EncodingType.INTEGER.getTypeValue()).value(new byte[]{0, 0, 0, 1}).build())
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("fromBytes should reject null/empty input")
        void fromBytes_nullOrEmpty() {
            assertThatThrownBy(() -> TtlvObject.fromBytes(null)).isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> TtlvObject.fromBytes(new byte[]{})).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("fromBytes should reject insufficient header data")
        void fromBytes_insufficientHeader() {
            // header requires 8 bytes total; provide less
            byte[] tooShort = new byte[7];
            assertThatThrownBy(() -> TtlvObject.fromBytes(tooShort)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("fromBytes should reject insufficient value data")
        void fromBytes_insufficientValue() {
            // Construct header declaring value length 4 but provide only header bytes
            byte[] t = tag(0x420020);
            byte type = EncodingType.INTEGER.getTypeValue();
            ByteBuffer buf = ByteBuffer.allocate(8).order(TtlvConstants.BYTE_ORDER);
            buf.put(t);
            buf.put(type);
            buf.putInt(4); // value length 4, but missing bytes
            byte[] headerOnly = buf.array();
            assertThatThrownBy(() -> TtlvObject.fromBytes(headerOnly)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("fromBytesMultiple should reject null/empty input")
        void fromBytesMultiple_nullOrEmpty() {
            assertThatThrownBy(() -> TtlvObject.fromBytesMultiple(null)).isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> TtlvObject.fromBytesMultiple(new byte[]{})).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("String formatting")
    class Formatting {
        @Test
        @DisplayName("getByteString should include header and value (unpadded) in hex")
        void byteString_containsHeaderAndValue() {
            TtlvObject obj = TtlvObject.builder()
                    .tag(tag(0x420030))
                    .type(EncodingType.INTEGER.getTypeValue())
                    .value(new byte[]{0, 0, 0, 7})
                    .build();
            String hex = obj.getByteString();

            HexFormat hexFormat = HexFormat.of();
            String expectedTag = hexFormat.formatHex(tag(0x420030));
            assertThat(hex).contains(expectedTag);
            assertThat(hex).contains("00000004"); // length
            assertThat(hex).contains("00000007"); // value
        }

        @Test
        @DisplayName("Structured strings should contain nested lines")
        void structuredStrings_nested() {
            TtlvObject child = TtlvObject.builder()
                    .tag(tag(0x420001))
                    .type(EncodingType.INTEGER.getTypeValue())
                    .value(new byte[]{0, 0, 0, 1})
                    .build();
            TtlvObject parent = TtlvObject.builder()
                    .tag(tag(0x420040))
                    .type(EncodingType.STRUCTURE.getTypeValue())
                    .value(TtlvObject.toBytesMultiple(child))
                    .build();

            String bs = parent.getStructuredByteString();
            String ss = parent.getStructuredString();
            assertThat(bs.split("\n")).hasSizeGreaterThan(1);
            assertThat(ss).contains("Tag : (0x");
            assertThat(ss).contains("Type : Structure");
            assertThat(ss).contains("Length : ");
        }
    }
}
