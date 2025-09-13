package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.test.BaseKmipTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EncodingType Tests")
class EncodingTypeTest extends BaseKmipTest {

    @Nested
    @DisplayName("Enum Properties")
    class EnumProperties {

        @ParameterizedTest
        @EnumSource(EncodingType.class)
        @DisplayName("Should have valid properties for all encoding types")
        void shouldHaveValidPropertiesForAllEncodingTypes(EncodingType encodingType) {
            // Then
            assertThat(encodingType.getTypeValue()).isNotNull();
            assertThat(encodingType.getDescription()).isNotNull().isNotEmpty();
            assertThat(encodingType.getRawByteSize()).isGreaterThanOrEqualTo(-1);
        }

        @Test
        @DisplayName("Should have correct structure type properties")
        void shouldHaveCorrectStructureTypeProperties() {
            // When
            EncodingType structure = EncodingType.STRUCTURE;

            // Then
            assertThat(structure.getTypeValue()).isEqualTo((byte) 0x01);
            assertThat(structure.getDescription()).isEqualTo("Structure");
            assertThat(structure.getRawByteSize()).isEqualTo(-1);
            assertThat(structure.isFixedLength()).isFalse();
        }

        @Test
        @DisplayName("Should have correct integer type properties")
        void shouldHaveCorrectIntegerTypeProperties() {
            // When
            EncodingType integer = EncodingType.INTEGER;

            // Then
            assertThat(integer.getTypeValue()).isEqualTo((byte) 0x02);
            assertThat(integer.getDescription()).isEqualTo("Integer");
            assertThat(integer.getRawByteSize()).isEqualTo(4);
            assertThat(integer.isFixedLength()).isTrue();
        }

        @Test
        @DisplayName("Should have correct boolean type properties")
        void shouldHaveCorrectBooleanTypeProperties() {
            // When
            EncodingType bool = EncodingType.BOOLEAN;

            // Then
            assertThat(bool.getTypeValue()).isEqualTo((byte) 0x06);
            assertThat(bool.getDescription()).isEqualTo("Boolean");
            assertThat(bool.getRawByteSize()).isEqualTo(8);
            assertThat(bool.isFixedLength()).isTrue();
        }
    }

    @Nested
    @DisplayName("Lookup Operations")
    class LookupOperations {

        @ParameterizedTest
        @EnumSource(EncodingType.class)
        @DisplayName("Should find encoding type by type value")
        void shouldFindEncodingTypeByTypeValue(EncodingType expected) {
            // When
            var found = EncodingType.fromTypeValue(expected.getTypeValue());

            // Then
            assertThat(found).isPresent().contains(expected);
        }

        @ParameterizedTest
        @EnumSource(EncodingType.class)
        @DisplayName("Should find encoding type by name")
        void shouldFindEncodingTypeByName(EncodingType expected) {
            // When
            var found = EncodingType.fromName(expected.name());

            // Then
            assertThat(found).isPresent().contains(expected);
        }

        @Test
        @DisplayName("Should return empty for invalid type value")
        void shouldReturnEmptyForInvalidTypeValue() {
            // Given
            byte invalidTypeValue = (byte) 0xFF;

            // When
            var found = EncodingType.fromTypeValue(invalidTypeValue);

            // Then
            assertThat(found).isEmpty();
        }

        @Test
        @DisplayName("Should return empty for invalid name")
        void shouldReturnEmptyForInvalidName() {
            // Given
            String invalidName = "INVALID_TYPE";

            // When
            var found = EncodingType.fromName(invalidName);

            // Then
            assertThat(found).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(bytes = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A})
        @DisplayName("Should validate known type values")
        void shouldValidateKnownTypeValues(byte typeValue) {
            // When & Then
            assertThat(EncodingType.isValidTypeValue(typeValue)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(bytes = {0x00, 0x0B, 0x0C, (byte) 0xFF})
        @DisplayName("Should reject unknown type values")
        void shouldRejectUnknownTypeValues(byte typeValue) {
            // When & Then
            assertThat(EncodingType.isValidTypeValue(typeValue)).isFalse();
        }
    }

    @Nested
    @DisplayName("Fixed vs Variable Length")
    class FixedVsVariableLength {

        @Test
        @DisplayName("Should identify fixed-length types correctly")
        void shouldIdentifyFixedLengthTypesCorrectly() {
            // Given
            EncodingType[] fixedLengthTypes = {
                    EncodingType.INTEGER,
                    EncodingType.LONG_INTEGER,
                    EncodingType.ENUMERATION,
                    EncodingType.BOOLEAN,
                    EncodingType.DATE_TIME,
                    EncodingType.INTERVAL
            };

            // When & Then
            for (EncodingType type : fixedLengthTypes) {
                assertThat(type.isFixedLength()).as("Type %s should be fixed length", type.name()).isTrue();
                assertThat(type.getRawByteSize())
                        .as("Type %s should have positive byte size", type.name())
                        .isPositive();
            }
        }

        @Test
        @DisplayName("Should identify variable-length types correctly")
        void shouldIdentifyVariableLengthTypesCorrectly() {
            // Given
            EncodingType[] variableLengthTypes = {
                    EncodingType.STRUCTURE,
                    EncodingType.BIG_INTEGER,
                    EncodingType.TEXT_STRING,
                    EncodingType.BYTE_STRING
            };

            // When & Then
            for (EncodingType type : variableLengthTypes) {
                assertThat(type.isFixedLength())
                        .as("Type %s should be variable length", type.name())
                        .isFalse();
                assertThat(type.getRawByteSize())
                        .as("Type %s should have -1 byte size", type.name())
                        .isEqualTo(-1);
            }
        }
    }

    @Nested
    @DisplayName("String Representation")
    class StringRepresentation {

        @ParameterizedTest
        @EnumSource(EncodingType.class)
        @DisplayName("Should return description as string representation")
        void shouldReturnDescriptionAsStringRepresentation(EncodingType encodingType) {
            // When
            String stringRepresentation = encodingType.toString();

            // Then
            assertThat(stringRepresentation).isEqualTo(encodingType.getDescription());
        }

        @Test
        @DisplayName("Should have consistent string representations")
        void shouldHaveConsistentStringRepresentations() {
            // Given
            EncodingType type = EncodingType.STRUCTURE;

            // When & Then
            assertThat(type.toString()).isEqualTo("Structure");
            assertThat(type.getDescription()).isEqualTo("Structure");
        }
    }

    @Nested
    @DisplayName("Byte Size Validation")
    class ByteSizeValidation {

        @Test
        @DisplayName("Should have correct byte sizes for numeric types")
        void shouldHaveCorrectByteSizesForNumericTypes() {
            // When & Then
            assertThat(EncodingType.INTEGER.getRawByteSize()).isEqualTo(4);
            assertThat(EncodingType.LONG_INTEGER.getRawByteSize()).isEqualTo(8);
            assertThat(EncodingType.ENUMERATION.getRawByteSize()).isEqualTo(4);
            assertThat(EncodingType.INTERVAL.getRawByteSize()).isEqualTo(4);
        }

        @Test
        @DisplayName("Should have correct byte sizes for time types")
        void shouldHaveCorrectByteSizesForTimeTypes() {
            // When & Then
            assertThat(EncodingType.DATE_TIME.getRawByteSize()).isEqualTo(8);
            assertThat(EncodingType.BOOLEAN.getRawByteSize()).isEqualTo(8);
        }

        @Test
        @DisplayName("Should have variable byte sizes for string types")
        void shouldHaveVariableByteSizesForStringTypes() {
            // When & Then
            assertThat(EncodingType.TEXT_STRING.getRawByteSize()).isEqualTo(-1);
            assertThat(EncodingType.BYTE_STRING.getRawByteSize()).isEqualTo(-1);
            assertThat(EncodingType.BIG_INTEGER.getRawByteSize()).isEqualTo(-1);
        }
    }

    @Nested
    @DisplayName("Type Value Uniqueness")
    class TypeValueUniqueness {

        @Test
        @DisplayName("Should have unique type values for all encoding types")
        void shouldHaveUniqueTypeValuesForAllEncodingTypes() {
            // Given
            EncodingType[] allTypes = EncodingType.values();

            // When
            long uniqueTypeValues =
                    java.util.Arrays.stream(allTypes)
                            .mapToInt(type -> type.getTypeValue() & 0xFF)
                            .distinct()
                            .count();

            // Then
            assertThat(uniqueTypeValues).isEqualTo(allTypes.length);
        }

        @Test
        @DisplayName("Should have sequential type values")
        void shouldHaveSequentialTypeValues() {
            // Given
            EncodingType[] allTypes = EncodingType.values();

            // When & Then
            for (int i = 0; i < allTypes.length; i++) {
                int expectedValue = i + 1;
                int actualValue = allTypes[i].getTypeValue() & 0xFF;
                assertThat(actualValue)
                        .as("Type %s should have value %d", allTypes[i].name(), expectedValue)
                        .isEqualTo(expectedValue);
            }
        }
    }
}
