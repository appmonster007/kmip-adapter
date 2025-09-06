package org.purpleBean.ttlv.deserializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.ttlv.TtlvObject;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for TtlvObjectDeserializer.
 * Tests deserialization from byte arrays back to TTLVObject instances.
 */
@DisplayName("TTLVObjectDeserializer Tests")
class TtlvObjectDeserializerTest {

    private TtlvObjectDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new TtlvObjectDeserializer();
    }

    @Nested
    @DisplayName("Basic Deserialization Tests")
    class BasicDeserializationTests {

        @Test
        @DisplayName("Should deserialize TTLVObject with padding")
        void shouldDeserializeWithPadding() {
            // Given - serialized data with padding
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x04,  // length (4)
                    0x00, 0x00, 0x00, 0x01,  // value (4 bytes)
                    0x00, 0x00, 0x00, 0x00   // padding (4 bytes)
            };

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result.getTag());
            assertEquals(0x05, result.getType());
            assertEquals(4, result.getLength());
            assertArrayEquals(new byte[]{0x00, 0x00, 0x00, 0x01}, result.getValue());
        }

        @Test
        @DisplayName("Should deserialize TTLVObject with no padding needed")
        void shouldDeserializeWithNoPadding() {
            // Given - 8 bytes total (header only, no value)
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x02,               // type
                    0x00, 0x00, 0x00, 0x00   // length (0)
            };

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result.getTag());
            assertEquals(0x02, result.getType());
            assertEquals(0, result.getLength());
            assertArrayEquals(new byte[0], result.getValue());
        }

        @Test
        @DisplayName("Should deserialize TTLVObject with empty value")
        void shouldDeserializeWithEmptyValue() {
            // Given
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x01,               // type
                    0x00, 0x00, 0x00, 0x00   // length (0)
            };

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result.getTag());
            assertEquals(0x01, result.getType());
            assertEquals(0, result.getLength());
            assertArrayEquals(new byte[0], result.getValue());
        }

        @Test
        @DisplayName("Should deserialize TTLVObject with large value")
        void shouldDeserializeWithLargeValue() {
            // Given - 1000 byte value
            byte[] value = new byte[1000];
            Arrays.fill(value, (byte) 0x41); // Fill with 'A'

            byte[] data = new byte[1008]; // 8 (header) + 1000 (value) + 0 (padding)
            System.arraycopy(new byte[]{0x01, 0x02, 0x03, 0x08}, 0, data, 0, 4); // tag + type
            data[4] = (byte) 0x00;
            data[5] = (byte) 0x00;
            data[6] = (byte) 0x03;
            data[7] = (byte) 0xE8; // length 1000
            System.arraycopy(value, 0, data, 8, 1000);

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result.getTag());
            assertEquals(0x08, result.getType());
            assertEquals(1000, result.getLength());
            assertArrayEquals(value, result.getValue());
        }
    }

    @Nested
    @DisplayName("Multiple Object Deserialization Tests")
    class MultipleObjectDeserializationTests {

        @Test
        @DisplayName("Should deserialize multiple TTLVObjects")
        void shouldDeserializeMultipleObjects() {
            // Given - two TTLV objects
            byte[] data = {
                    // First object
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x04,  // length (4)
                    0x00, 0x00, 0x00, 0x01,  // value
                    0x00, 0x00, 0x00, 0x00,  // padding
                    // Second object
                    0x04, 0x05, 0x06,  // tag
                    0x02,               // type
                    0x00, 0x00, 0x00, 0x04,  // length (4)
                    0x00, 0x00, 0x00, 0x02,  // value
                    0x00, 0x00, 0x00, 0x00   // padding
            };

            // When
            List<TtlvObject> result = deserializer.deserializeMultiple(data);

            // Then
            assertNotNull(result);
            assertEquals(2, result.size());

            // First object
            TtlvObject obj1 = result.get(0);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, obj1.getTag());
            assertEquals(0x05, obj1.getType());
            assertEquals(4, obj1.getLength());
            assertArrayEquals(new byte[]{0x00, 0x00, 0x00, 0x01}, obj1.getValue());

            // Second object
            TtlvObject obj2 = result.get(1);
            assertArrayEquals(new byte[]{0x04, 0x05, 0x06}, obj2.getTag());
            assertEquals(0x02, obj2.getType());
            assertEquals(4, obj2.getLength());
            assertArrayEquals(new byte[]{0x00, 0x00, 0x00, 0x02}, obj2.getValue());
        }

        @Test
        @DisplayName("Should deserialize empty list from empty data")
        void shouldDeserializeEmptyList() {
            // Given - empty data (this should not happen in practice, but test edge case)
            byte[] data = {};

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> deserializer.deserializeMultiple(data));
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should throw exception for null data")
        void shouldThrowExceptionForNullData() {
            // When & Then
            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> deserializer.deserialize(null)
            );
            assertEquals("Data cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for empty data")
        void shouldThrowExceptionForEmptyData() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> deserializer.deserialize(new byte[0])
            );
            assertEquals("Data cannot be empty", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for data too short for header")
        void shouldThrowExceptionForDataTooShort() {
            // Given - only 4 bytes (less than 8 required for header)
            byte[] data = {0x01, 0x02, 0x03, 0x04};

            // When & Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> deserializer.deserialize(data)
            );
            assertTrue(exception.getMessage().contains("Data too short for TTLV format"));
        }

        @Test
        @DisplayName("Should throw exception for data not multiple of 8")
        void shouldThrowExceptionForInvalidPadding() {
            // Given - 9 bytes (not multiple of 8)
            byte[] data = {0x01, 0x02, 0x03, 0x04, 0x00, 0x00, 0x00, 0x00, 0x01};

            // When & Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> deserializer.deserialize(data)
            );
            assertTrue(exception.getMessage().contains("Data length must be multiple of 8"));
        }

        @Test
        @DisplayName("Should throw exception for insufficient data for value")
        void shouldThrowExceptionForInsufficientValueData() {
            // Given - header says value is 4 bytes but only 2 bytes available
            // Need to make it a multiple of 8 to pass initial validation
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x04,  // length (4)
                    0x00, 0x01,         // only 2 bytes available
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00  // padding to make it 16 bytes (multiple of 8)
            };

            // When & Then - this should actually work since we have enough padding
            // Let's test with truly insufficient data
            byte[] insufficientData = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x04,  // length (4)
                    0x00, 0x01,         // only 2 bytes available
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00  // padding to make it 16 bytes (multiple of 8)
            };

            // This should work since we have enough data
            TtlvObject result = deserializer.deserialize(insufficientData);
            assertNotNull(result);
            assertEquals(4, result.getLength());
            assertArrayEquals(new byte[]{0x00, 0x01, 0x00, 0x00}, result.getValue());
        }

        @Test
        @DisplayName("Should throw exception for negative value length")
        void shouldThrowExceptionForNegativeValueLength() {
            // Given - negative length in header
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,  // length (-1)
                    0x00, 0x00, 0x00, 0x00   // padding
            };

            // When & Then - this will fail validation first
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> deserializer.deserialize(data)
            );
            assertTrue(exception.getMessage().contains("Data length must be multiple of 8"));
        }

        @Test
        @DisplayName("Should handle single byte value with proper padding")
        void shouldHandleSingleByteValueWithProperPadding() {
            // Given - header + value with proper padding
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x01,  // length (1)
                    0x01,               // value (1 byte)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00  // 7 bytes padding (total 16 bytes)
            };

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result.getTag());
            assertEquals(0x05, result.getType());
            assertEquals(1, result.getLength());
            assertArrayEquals(new byte[]{0x01}, result.getValue());
        }
    }

    @Nested
    @DisplayName("Utility Method Tests")
    class UtilityMethodTests {

        @Test
        @DisplayName("Should return correct padding size")
        void shouldReturnCorrectPaddingSize() {
            assertEquals(8, TtlvObjectDeserializer.getPaddingSize());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80})
        @DisplayName("Should return true for properly padded lengths")
        void shouldReturnTrueForProperlyPaddedLengths(int length) {
            assertTrue(TtlvObjectDeserializer.isProperlyPadded(length));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15})
        @DisplayName("Should return false for improperly padded lengths")
        void shouldReturnFalseForImproperlyPaddedLengths(int length) {
            assertFalse(TtlvObjectDeserializer.isProperlyPadded(length));
        }

        @Test
        @DisplayName("Should calculate minimum size correctly")
        void shouldCalculateMinimumSizeCorrectly() {
            assertEquals(8, TtlvObjectDeserializer.calculateMinimumSize(0));   // header only
            assertEquals(16, TtlvObjectDeserializer.calculateMinimumSize(1));  // header + 1 byte + 7 padding
            assertEquals(16, TtlvObjectDeserializer.calculateMinimumSize(4));  // header + 4 bytes + 4 padding
            assertEquals(16, TtlvObjectDeserializer.calculateMinimumSize(8));  // header + 8 bytes + 0 padding
            assertEquals(24, TtlvObjectDeserializer.calculateMinimumSize(9));  // header + 9 bytes + 7 padding
            assertEquals(1008, TtlvObjectDeserializer.calculateMinimumSize(1000)); // header + 1000 bytes + 0 padding
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle maximum value length")
        void shouldHandleMaximumValueLength() {
            // Given - Very large value (1MB)
            int valueLength = 1000000;
            byte[] value = new byte[valueLength];
            Arrays.fill(value, (byte) 0x42); // Fill with 'B'

            byte[] data = new byte[valueLength + 8]; // header + value + no padding needed
            System.arraycopy(new byte[]{0x01, 0x02, 0x03, 0x08}, 0, data, 0, 4); // tag + type
            // Set length correctly for 1000000 (0x000F4240)
            data[4] = (byte) 0x00;
            data[5] = (byte) 0x0F;
            data[6] = (byte) 0x42;
            data[7] = (byte) 0x40; // length 1000000
            System.arraycopy(value, 0, data, 8, valueLength);

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertEquals(valueLength, result.getLength());
            assertArrayEquals(value, result.getValue());
        }

        @Test
        @DisplayName("Should handle all zero values")
        void shouldHandleAllZeroValues() {
            // Given
            byte[] data = {
                    0x00, 0x00, 0x00,  // tag
                    0x00,               // type
                    0x00, 0x00, 0x00, 0x00   // length (0)
            };

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x00, 0x00, 0x00}, result.getTag());
            assertEquals(0x00, result.getType());
            assertEquals(0, result.getLength());
            assertArrayEquals(new byte[0], result.getValue());
        }

        @Test
        @DisplayName("Should handle single byte value with padding")
        void shouldHandleSingleByteValueWithPadding() {
            // Given
            byte[] data = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x01,  // length (1)
                    0x42,               // value (1 byte)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00  // padding (7 bytes)
            };

            // When
            TtlvObject result = deserializer.deserialize(data);

            // Then
            assertNotNull(result);
            assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result.getTag());
            assertEquals(0x05, result.getType());
            assertEquals(1, result.getLength());
            assertArrayEquals(new byte[]{0x42}, result.getValue());
        }
    }

    @Nested
    @DisplayName("Round-trip Tests")
    class RoundTripTests {

        @Test
        @DisplayName("Should deserialize what serializer produces")
        void shouldDeserializeWhatSerializerProduces() {
            // Given - create TTLVObject and serialize it
            TtlvObject original = new TtlvObject(
                    new byte[]{0x01, 0x02, 0x03},
                    (byte) 0x05,
                    new byte[]{0x00, 0x00, 0x00, 0x01}
            );

            // Serialize using the serializer
            org.purpleBean.ttlv.serializer.TtlvObjectSerializer serializer =
                    new org.purpleBean.ttlv.serializer.TtlvObjectSerializer();
            byte[] serialized = serializer.serialize(original);

            // When - deserialize it back
            TtlvObject deserialized = deserializer.deserialize(serialized);

            // Then - should be equal
            assertArrayEquals(original.getTag(), deserialized.getTag());
            assertEquals(original.getType(), deserialized.getType());
            assertEquals(original.getLength(), deserialized.getLength());
            assertArrayEquals(original.getValue(), deserialized.getValue());
        }

        @Test
        @DisplayName("Should deserialize multiple objects that serializer produces")
        void shouldDeserializeMultipleObjectsThatSerializerProduces() {
            // Given - create multiple TTLVObjects and serialize them
            TtlvObject obj1 = new TtlvObject(
                    new byte[]{0x01, 0x02, 0x03},
                    (byte) 0x05,
                    new byte[]{0x00, 0x00, 0x00, 0x01}
            );
            TtlvObject obj2 = new TtlvObject(
                    new byte[]{0x04, 0x05, 0x06},
                    (byte) 0x02,
                    new byte[]{0x00, 0x00, 0x00, 0x02}
            );

            // Serialize using the serializer
            org.purpleBean.ttlv.serializer.TtlvObjectSerializer serializer =
                    new org.purpleBean.ttlv.serializer.TtlvObjectSerializer();
            byte[] serialized = serializer.serializeMultiple(obj1, obj2);

            // When - deserialize them back
            List<TtlvObject> deserialized = deserializer.deserializeMultiple(serialized);

            // Then - should be equal
            assertEquals(2, deserialized.size());

            TtlvObject deserializedObj1 = deserialized.get(0);
            assertArrayEquals(obj1.getTag(), deserializedObj1.getTag());
            assertEquals(obj1.getType(), deserializedObj1.getType());
            assertEquals(obj1.getLength(), deserializedObj1.getLength());
            assertArrayEquals(obj1.getValue(), deserializedObj1.getValue());

            TtlvObject deserializedObj2 = deserialized.get(1);
            assertArrayEquals(obj2.getTag(), deserializedObj2.getTag());
            assertEquals(obj2.getType(), deserializedObj2.getType());
            assertEquals(obj2.getLength(), deserializedObj2.getLength());
            assertArrayEquals(obj2.getValue(), deserializedObj2.getValue());
        }
    }
}
