package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TTLV Serialization Tests")
class TtlvSerializationTest extends BaseKmipTest {

    @Nested
    @DisplayName("TTLV Tag Encoding")
    class TtlvTagEncoding {

        @Test
        @DisplayName("Should encode standard tags to correct byte arrays")
        void shouldEncodeStandardTagsToCorrectByteArrays() {
            // Given
            KmipTag.Standard protocolVersionTag = KmipTag.Standard.PROTOCOL_VERSION;

            // When
            byte[] tagBytes = new KmipTag(protocolVersionTag).getTagBytes();

            // Then
            assertThat(tagBytes).hasSize(3);
            assertThat(tagBytes[0]).isEqualTo((byte) 0x42);
            assertThat(tagBytes[1]).isEqualTo((byte) 0x00);
            assertThat(tagBytes[2]).isEqualTo((byte) 0x69);
        }

        @Test
        @DisplayName("Should encode custom tags to correct byte arrays")
        void shouldEncodeCustomTagsToCorrectByteArrays() {
            // Given
            int customValue = 0x540001;
            KmipTag.Value customTag = KmipTestDataFactory.createCustomKmipTag();

            // When
            byte[] tagBytes = new KmipTag(customTag).getTagBytes();

            // Then
            assertThat(tagBytes).hasSize(3);
            // Verify the bytes match the expected custom value
            int reconstructedValue = ((tagBytes[0] & 0xFF) << 16) |
                    ((tagBytes[1] & 0xFF) << 8) |
                    (tagBytes[2] & 0xFF);
            assertThat(reconstructedValue).isEqualTo(customValue);
        }

        @ParameterizedTest
        @ValueSource(ints = {0x420001, 0x420069, 0x42008D, 0x420094})
        @DisplayName("Should handle various standard tag values")
        void shouldHandleVariousStandardTagValues(int tagValue) {
            // Given
            KmipTag.Value tag = KmipTag.fromValue(defaultSpec, tagValue);

            // When
            byte[] tagBytes = new KmipTag(tag).getTagBytes();

            // Then
            assertThat(tagBytes).hasSize(3);
            int reconstructedValue = ((tagBytes[0] & 0xFF) << 16) |
                    ((tagBytes[1] & 0xFF) << 8) |
                    (tagBytes[2] & 0xFF);
            assertThat(reconstructedValue).isEqualTo(tagValue);
        }
    }

    @Nested
    @DisplayName("TTLV Type Encoding")
    class TtlvTypeEncoding {

        @Test
        @DisplayName("Should encode structure type correctly")
        void shouldEncodeStructureTypeCorrectly() {
            // Given
            EncodingType structureType = EncodingType.STRUCTURE;

            // When
            byte typeValue = structureType.getTypeValue();

            // Then
            assertThat(typeValue).isEqualTo((byte) 0x01);
        }

        @Test
        @DisplayName("Should encode integer type correctly")
        void shouldEncodeIntegerTypeCorrectly() {
            // Given
            EncodingType integerType = EncodingType.INTEGER;

            // When
            byte typeValue = integerType.getTypeValue();

            // Then
            assertThat(typeValue).isEqualTo((byte) 0x02);
        }

        @ParameterizedTest
        @ValueSource(bytes = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A})
        @DisplayName("Should handle all encoding type values")
        void shouldHandleAllEncodingTypeValues(byte expectedTypeValue) {
            // When
            var encodingType = EncodingType.fromTypeValue(expectedTypeValue);

            // Then
            assertThat(encodingType).isPresent();
            assertThat(encodingType.get().getTypeValue()).isEqualTo(expectedTypeValue);
        }
    }

    @Nested
    @DisplayName("TTLV Length Calculation")
    class TtlvLengthCalculation {

        @Test
        @DisplayName("Should calculate correct length for fixed-size types")
        void shouldCalculateCorrectLengthForFixedSizeTypes() {
            // Given & When & Then
            assertThat(EncodingType.INTEGER.getRawByteSize()).isEqualTo(4);
            assertThat(EncodingType.LONG_INTEGER.getRawByteSize()).isEqualTo(8);
            assertThat(EncodingType.BOOLEAN.getRawByteSize()).isEqualTo(8);
            assertThat(EncodingType.DATE_TIME.getRawByteSize()).isEqualTo(8);
            assertThat(EncodingType.ENUMERATION.getRawByteSize()).isEqualTo(4);
            assertThat(EncodingType.INTERVAL.getRawByteSize()).isEqualTo(4);
        }

        @Test
        @DisplayName("Should identify variable-length types")
        void shouldIdentifyVariableLengthTypes() {
            // Given & When & Then
            assertThat(EncodingType.STRUCTURE.getRawByteSize()).isEqualTo(-1);
            assertThat(EncodingType.BIG_INTEGER.getRawByteSize()).isEqualTo(-1);
            assertThat(EncodingType.TEXT_STRING.getRawByteSize()).isEqualTo(-1);
            assertThat(EncodingType.BYTE_STRING.getRawByteSize()).isEqualTo(-1);
        }
    }

    @Nested
    @DisplayName("TTLV Value Encoding")
    class TtlvValueEncoding {

        @Test
        @DisplayName("Should handle ProtocolVersion structure encoding")
        void shouldHandleProtocolVersionStructureEncoding() {
            // Given
            ProtocolVersion version = KmipTestDataFactory.createProtocolVersion();

            // When & Then
            assertThat(version.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.PROTOCOL_VERSION);
            assertThat(version.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(version.getValues()).hasSize(2);

            // Verify nested components
            assertThat(version.getProtocolVersionMajor().getEncodingType()).isEqualTo(EncodingType.INTEGER);
            assertThat(version.getProtocolVersionMinor().getEncodingType()).isEqualTo(EncodingType.INTEGER);
        }

        @Test
        @DisplayName("Should handle nested structure encoding")
        void shouldHandleNestedStructureEncoding() {
            // Given
            var sampleStructure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            assertThat(sampleStructure.getKmipTag()).isNotNull();
            assertThat(sampleStructure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);

            // Verify nested components have correct encoding types
            assertThat(sampleStructure.getActivationDate().getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
            assertThat(sampleStructure.getState().getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }
    }

    @Nested
    @DisplayName("TTLV Byte Array Operations")
    class TtlvByteArrayOperations {

        @Test
        @DisplayName("Should reconstruct tag from byte array")
        void shouldReconstructTagFromByteArray() {
            // Given
            byte[] tagBytes = {0x42, 0x00, 0x69}; // PROTOCOL_VERSION tag

            // When
            KmipTag.Value reconstructedTag = KmipTag.fromBytes(defaultSpec, tagBytes);

            // Then
            assertThat(reconstructedTag).isEqualTo(KmipTag.Standard.PROTOCOL_VERSION);
        }

        @Test
        @DisplayName("Should handle invalid byte array lengths")
        void shouldHandleInvalidByteArrayLengths() {
            // Given
            byte[] tooShort = {0x42, 0x00};
            byte[] tooLong = {0x42, 0x00, 0x69, 0x00};

            // When & Then
            assertThatThrownBy(() -> KmipTag.fromBytes(defaultSpec, tooShort))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> KmipTag.fromBytes(defaultSpec, tooLong))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should handle null byte arrays")
        void shouldHandleNullByteArrays() {
            // When & Then
            assertThatThrownBy(() -> KmipTag.fromBytes(defaultSpec, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("TTLV Padding and Alignment")
    class TtlvPaddingAndAlignment {

        @Test
        @DisplayName("Should handle 8-byte alignment for structures")
        void shouldHandle8ByteAlignmentForStructures() {
            // Given - TTLV requires 8-byte alignment for structures
            EncodingType structureType = EncodingType.STRUCTURE;

            // When & Then
            assertThat(structureType.isFixedLength()).isFalse();
            assertThat(structureType.getRawByteSize()).isEqualTo(-1);
            // Note: Actual padding calculation would be done by TTLV encoder
        }

        @Test
        @DisplayName("Should handle fixed-length type alignment")
        void shouldHandleFixedLengthTypeAlignment() {
            // Given
            EncodingType booleanType = EncodingType.BOOLEAN;

            // When & Then
            assertThat(booleanType.isFixedLength()).isTrue();
            assertThat(booleanType.getRawByteSize()).isEqualTo(8); // Already 8-byte aligned
        }
    }

    @Nested
    @DisplayName("TTLV Error Conditions")
    class TtlvErrorConditions {

        @Test
        @DisplayName("Should handle unknown tag values")
        void shouldHandleUnknownTagValues() {
            // Given
            int unknownTagValue = 0x999999;

            // When & Then
            assertThatThrownBy(() -> KmipTag.fromValue(defaultSpec, unknownTagValue))
                    .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("Should handle unsupported encoding types")
        void shouldHandleUnsupportedEncodingTypes() {
            // Given
            byte unsupportedType = (byte) 0xFF;

            // When & Then
            assertThat(EncodingType.fromTypeValue(unsupportedType)).isEmpty();
            assertThat(EncodingType.isValidTypeValue(unsupportedType)).isFalse();
        }
    }

    @Nested
    @DisplayName("TTLV Performance")
    class TtlvPerformance {

        @Test
        @DisplayName("Should handle tag lookup efficiently")
        void shouldHandleTagLookupEfficiently() {
            // Given
            int iterations = 10000;
            long startTime = System.currentTimeMillis();

            // When
            for (int i = 0; i < iterations; i++) {
                KmipTag.fromValue(defaultSpec, KmipTag.Standard.PROTOCOL_VERSION.getValue());
            }

            long endTime = System.currentTimeMillis();

            // Then
            assertThat(endTime - startTime).isLessThan(1000); // Should complete within 1 second
        }

        @Test
        @DisplayName("Should handle byte array operations efficiently")
        void shouldHandleByteArrayOperationsEfficiently() {
            // Given
            byte[] tagBytes = {0x42, 0x00, 0x69};
            int iterations = 10000;
            long startTime = System.currentTimeMillis();

            // When
            for (int i = 0; i < iterations; i++) {
                KmipTag.fromBytes(defaultSpec, tagBytes);
            }

            long endTime = System.currentTimeMillis();

            // Then
            assertThat(endTime - startTime).isLessThan(1000); // Should complete within 1 second
        }
    }
}
