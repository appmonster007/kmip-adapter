package org.purpleBean.ttlv.serializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.ttlv.TtlvObject;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for TtlvObjectSerializer.
 * Tests serialization with padding to ensure byte array length is always multiple of 8.
 */
@DisplayName("TTLVObjectSerializer Tests")
class TtlvObjectSerializerTest {

    private TtlvObjectSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new TtlvObjectSerializer();
    }

    @Nested
    @DisplayName("Basic Serialization Tests")
    class BasicSerializationTests {

        @Test
        @DisplayName("Should serialize TTLVObject with exact padding")
        void shouldSerializeWithExactPadding() {
            // Given
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x05; // ENUMERATION
            byte[] value = {0x00, 0x00, 0x00, 0x01}; // 4 bytes
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertEquals(16, result.length); // 8 (header) + 4 (value) + 4 (padding) = 16
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));

            // Verify header
            assertArrayEquals(tag, Arrays.copyOfRange(result, 0, 3));
            assertEquals(type, result[3]);
            assertEquals(4, Byte.toUnsignedInt(result[4]) << 24 |
                    Byte.toUnsignedInt(result[5]) << 16 |
                    Byte.toUnsignedInt(result[6]) << 8 |
                    Byte.toUnsignedInt(result[7]));

            // Verify value
            assertArrayEquals(value, Arrays.copyOfRange(result, 8, 12));

            // Verify padding (should be zeros)
            byte[] padding = Arrays.copyOfRange(result, 12, 16);
            assertArrayEquals(new byte[4], padding);
        }

        @Test
        @DisplayName("Should serialize TTLVObject with no additional padding needed")
        void shouldSerializeWithNoAdditionalPadding() {
            // Given - 8 bytes total (header + value) already multiple of 8
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x02; // INTEGER
            byte[] value = {}; // 0 bytes - empty value
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertEquals(8, result.length); // 8 (header) + 0 (value) + 0 (no padding needed)
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
        }

        @Test
        @DisplayName("Should serialize TTLVObject with empty value")
        void shouldSerializeWithEmptyValue() {
            // Given
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x01; // STRUCTURE
            byte[] value = {}; // empty
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertEquals(8, result.length); // 8 (header) + 0 (value) + 0 (padding)
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
        }

        @Test
        @DisplayName("Should serialize TTLVObject with null value")
        void shouldSerializeWithNullValue() {
            // Given
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x01; // STRUCTURE
            TtlvObject ttlvObject = new TtlvObject(tag, type, 0, null);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertEquals(8, result.length); // 8 (header) + 0 (value) + 0 (padding)
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
        }
    }

    @Nested
    @DisplayName("Padding Tests")
    class PaddingTests {

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15})
        @DisplayName("Should pad to multiple of 8 for various value lengths")
        void shouldPadToMultipleOfEight(int valueLength) {
            // Given
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x07; // TEXT_STRING
            byte[] value = new byte[valueLength];
            Arrays.fill(value, (byte) 0x41); // Fill with 'A'
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
            assertTrue(result.length >= 8 + valueLength); // At least header + value
            assertEquals(0, result.length % 8); // Must be multiple of 8
        }

        @Test
        @DisplayName("Should handle large values with proper padding")
        void shouldHandleLargeValuesWithProperPadding() {
            // Given - 1000 bytes value
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x08; // BYTE_STRING
            byte[] value = new byte[1000];
            Arrays.fill(value, (byte) 0x42); // Fill with 'B'
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertEquals(1008, result.length); // 8 (header) + 1000 (value) + 0 (padding)
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
        }
    }

    @Nested
    @DisplayName("Multiple Object Serialization Tests")
    class MultipleObjectSerializationTests {

        @Test
        @DisplayName("Should serialize multiple TTLVObjects")
        void shouldSerializeMultipleObjects() {
            // Given
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

            // When
            byte[] result = serializer.serializeMultiple(obj1, obj2);

            // Then
            assertNotNull(result);
            assertEquals(32, result.length); // 2 * 16 bytes each
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
        }

        @Test
        @DisplayName("Should serialize empty array of TTLVObjects")
        void shouldSerializeEmptyArray() {
            // When
            byte[] result = serializer.serializeMultiple();

            // Then
            assertNotNull(result);
            assertEquals(0, result.length);
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should throw exception for null TTLVObject")
        void shouldThrowExceptionForNullObject() {
            // When & Then
            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> serializer.serialize(null)
            );
            assertEquals("TTLVObject cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for null tag")
        void shouldThrowExceptionForNullTag() {
            // When & Then - validation now happens in TtlvObject constructor
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new TtlvObject(null, (byte) 0x05, new byte[]{0x01})
            );
            assertEquals("Tag cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for invalid tag length")
        void shouldThrowExceptionForInvalidTagLength() {
            // When & Then - validation now happens in TtlvObject constructor
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new TtlvObject(
                            new byte[]{0x01, 0x02}, // Only 2 bytes, should be 3
                            (byte) 0x05,
                            new byte[]{0x01}
                    )
            );
            assertTrue(exception.getMessage().contains("Tag must be 3 bytes long"));
        }

        @Test
        @DisplayName("Should throw exception for mismatched value length")
        void shouldThrowExceptionForMismatchedValueLength() {
            // When & Then - validation now happens in TtlvObject constructor
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new TtlvObject(
                            new byte[]{0x01, 0x02, 0x03},
                            (byte) 0x05,
                            4, // declared length
                            new byte[]{0x01, 0x02} // actual length is 2
                    )
            );
            assertTrue(exception.getMessage().contains("Value length does not match specified length"));
        }

        @Test
        @DisplayName("Should throw exception for null TTLVObjects array")
        void shouldThrowExceptionForNullArray() {
            // When & Then
            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> serializer.serializeMultiple((TtlvObject[]) null)
            );
            assertEquals("TTLVObjects array cannot be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Utility Method Tests")
    class UtilityMethodTests {

        @Test
        @DisplayName("Should return correct padding size")
        void shouldReturnCorrectPaddingSize() {
            assertEquals(8, TtlvObjectSerializer.getPaddingSize());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80})
        @DisplayName("Should return true for properly padded lengths")
        void shouldReturnTrueForProperlyPaddedLengths(int length) {
            assertTrue(TtlvObjectSerializer.isProperlyPadded(length));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15})
        @DisplayName("Should return false for improperly padded lengths")
        void shouldReturnFalseForImproperlyPaddedLengths(int length) {
            assertFalse(TtlvObjectSerializer.isProperlyPadded(length));
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle maximum value length")
        void shouldHandleMaximumValueLength() {
            // Given - Very large value
            byte[] tag = {0x01, 0x02, 0x03};
            byte type = 0x08; // BYTE_STRING
            byte[] value = new byte[1000000]; // 1MB
            Arrays.fill(value, (byte) 0x43); // Fill with 'C'
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
            assertTrue(result.length >= 1000008); // At least header + value
        }

        @Test
        @DisplayName("Should handle all zero values")
        void shouldHandleAllZeroValues() {
            // Given
            byte[] tag = {0x00, 0x00, 0x00};
            byte type = 0x00;
            byte[] value = {0x00, 0x00, 0x00, 0x00};
            TtlvObject ttlvObject = new TtlvObject(tag, type, value);

            // When
            byte[] result = serializer.serialize(ttlvObject);

            // Then
            assertNotNull(result);
            assertTrue(TtlvObjectSerializer.isProperlyPadded(result.length));
            assertEquals(16, result.length); // 8 (header) + 4 (value) + 4 (padding)
        }
    }
}
