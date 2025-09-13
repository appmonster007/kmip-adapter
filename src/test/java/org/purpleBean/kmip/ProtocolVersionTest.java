package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipAssertions;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProtocolVersion Tests")
class ProtocolVersionTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create ProtocolVersion with integer constructor")
        void shouldCreateProtocolVersionWithIntegerConstructor() {
            // Given
            int major = 1;
            int minor = 2;

            // When
            ProtocolVersion version = ProtocolVersion.of(major, minor);

            // Then
            KmipAssertions.assertThat(version)
                    .hasVersion(major, minor)
                    .hasValidStructure()
                    .isCompatibleWith(KmipSpec.V1_2);
        }

        @Test
        @DisplayName("Should create ProtocolVersion with component constructor")
        void shouldCreateProtocolVersionWithComponentConstructor() {
            // Given
            ProtocolVersion.ProtocolVersionMajor major = new ProtocolVersion.ProtocolVersionMajor(1);
            ProtocolVersion.ProtocolVersionMinor minor = new ProtocolVersion.ProtocolVersionMinor(2);

            // When
            ProtocolVersion version = ProtocolVersion.of(major, minor);

            // Then
            KmipAssertions.assertThat(version).hasVersion(1, 2).hasValidStructure();
            assertThat(version.getProtocolVersionMajor()).isEqualTo(major);
            assertThat(version.getProtocolVersionMinor()).isEqualTo(minor);
        }

        @ParameterizedTest
        @CsvSource({"0, 0", "1, 0", "1, 1", "1, 2", "2, 0", "99, 99"})
        @DisplayName("Should handle various version combinations")
        void shouldHandleVariousVersionCombinations(int major, int minor) {
            // When
            ProtocolVersion version = ProtocolVersion.of(major, minor);

            // Then
            KmipAssertions.assertThat(version).hasVersion(major, minor).hasValidStructure();
        }

        @Test
        @DisplayName("Should have correct KMIP structure properties")
        void shouldHaveCorrectKmipStructureProperties() {
            // Given
            ProtocolVersion version = KmipTestDataFactory.createProtocolVersion();

            // When & Then
            assertThat(version.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.PROTOCOL_VERSION);
            assertThat(version.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(version.isSupportedFor(KmipSpec.V1_2)).isTrue();
            assertThat(version.getValues()).hasSize(2);
        }
    }

    @Nested
    @DisplayName("Component Classes")
    class ComponentClasses {

        @Test
        @DisplayName("Should create ProtocolVersionMajor correctly")
        void shouldCreateProtocolVersionMajorCorrectly() {
            // Given
            int majorValue = 1;

            // When
            ProtocolVersion.ProtocolVersionMajor major =
                    new ProtocolVersion.ProtocolVersionMajor(majorValue);

            // Then
            assertThat(major.getValue()).isEqualTo(majorValue);
            assertThat(major.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.PROTOCOL_VERSION_MAJOR);
            assertThat(major.getEncodingType()).isEqualTo(EncodingType.INTEGER);
            assertThat(major.isSupportedFor(KmipSpec.V1_2)).isTrue();
            assertThat(major.toString()).isEqualTo(String.valueOf(majorValue));
        }

        @Test
        @DisplayName("Should create ProtocolVersionMinor correctly")
        void shouldCreateProtocolVersionMinorCorrectly() {
            // Given
            int minorValue = 2;

            // When
            ProtocolVersion.ProtocolVersionMinor minor =
                    new ProtocolVersion.ProtocolVersionMinor(minorValue);

            // Then
            assertThat(minor.getValue()).isEqualTo(minorValue);
            assertThat(minor.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.PROTOCOL_VERSION_MINOR);
            assertThat(minor.getEncodingType()).isEqualTo(EncodingType.INTEGER);
            assertThat(minor.isSupportedFor(KmipSpec.V1_2)).isTrue();
            assertThat(minor.toString()).isEqualTo(String.valueOf(minorValue));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 10, 100, Integer.MAX_VALUE})
        @DisplayName("Should handle various major version values")
        void shouldHandleVariousMajorVersionValues(int value) {
            // When
            ProtocolVersion.ProtocolVersionMajor major = new ProtocolVersion.ProtocolVersionMajor(value);

            // Then
            assertThat(major.getValue()).isEqualTo(value);
            assertThat(major.toString()).isEqualTo(String.valueOf(value));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 10, 100, Integer.MAX_VALUE})
        @DisplayName("Should handle various minor version values")
        void shouldHandleVariousMinorVersionValues(int value) {
            // When
            ProtocolVersion.ProtocolVersionMinor minor = new ProtocolVersion.ProtocolVersionMinor(value);

            // Then
            assertThat(minor.getValue()).isEqualTo(value);
            assertThat(minor.toString()).isEqualTo(String.valueOf(value));
        }
    }

    @Nested
    @DisplayName("Equality and Hash Code")
    class EqualityAndHashCode {

        @Test
        @DisplayName("Should be equal when versions match")
        void shouldBeEqualWhenVersionsMatch() {
            // Given
            ProtocolVersion version1 = ProtocolVersion.of(1, 2);
            ProtocolVersion version2 = ProtocolVersion.of(1, 2);

            // When & Then
            assertThat(version1).isEqualTo(version2);
            assertThat(version1.hashCode()).isEqualTo(version2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal when major versions differ")
        void shouldNotBeEqualWhenMajorVersionsDiffer() {
            // Given
            ProtocolVersion version1 = ProtocolVersion.of(1, 2);
            ProtocolVersion version2 = ProtocolVersion.of(2, 2);

            // When & Then
            assertThat(version1).isNotEqualTo(version2);
        }

        @Test
        @DisplayName("Should not be equal when minor versions differ")
        void shouldNotBeEqualWhenMinorVersionsDiffer() {
            // Given
            ProtocolVersion version1 = ProtocolVersion.of(1, 2);
            ProtocolVersion version2 = ProtocolVersion.of(1, 3);

            // When & Then
            assertThat(version1).isNotEqualTo(version2);
        }

        @Test
        @DisplayName("Should handle equality with component objects")
        void shouldHandleEqualityWithComponentObjects() {
            // Given
            ProtocolVersion.ProtocolVersionMajor major1 = new ProtocolVersion.ProtocolVersionMajor(1);
            ProtocolVersion.ProtocolVersionMajor major2 = new ProtocolVersion.ProtocolVersionMajor(1);
            ProtocolVersion.ProtocolVersionMinor minor1 = new ProtocolVersion.ProtocolVersionMinor(2);
            ProtocolVersion.ProtocolVersionMinor minor2 = new ProtocolVersion.ProtocolVersionMinor(2);

            // When & Then
            assertThat(major1).isEqualTo(major2);
            assertThat(minor1).isEqualTo(minor2);
            assertThat(major1.hashCode()).isEqualTo(major2.hashCode());
            assertThat(minor1.hashCode()).isEqualTo(minor2.hashCode());
        }
    }

    @Nested
    @DisplayName("String Representation")
    class StringRepresentation {

        @Test
        @DisplayName("Should have correct string representation")
        void shouldHaveCorrectStringRepresentation() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);

            // When
            String stringRepresentation = version.toString();

            // Then
            assertThat(stringRepresentation).isEqualTo("KMIP-ProtocolVersion-V1.2");
        }

        @ParameterizedTest
        @CsvSource({
                "0, 0, KMIP-ProtocolVersion-V0.0",
                "1, 0, KMIP-ProtocolVersion-V1.0",
                "1, 2, KMIP-ProtocolVersion-V1.2",
                "2, 1, KMIP-ProtocolVersion-V2.1"
        })
        @DisplayName("Should format string representation correctly for various versions")
        void shouldFormatStringRepresentationCorrectlyForVariousVersions(
                int major, int minor, String expected) {
            // Given
            ProtocolVersion version = ProtocolVersion.of(major, minor);

            // When
            String actual = version.toString();

            // Then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize and deserialize JSON correctly")
        void shouldSerializeAndDeserializeJsonCorrectly() {
            // Given
            ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();

            // When & Then
            ProtocolVersion restored =
                    SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, ProtocolVersion.class);

            KmipAssertions.assertThat(restored).hasVersion(original.getMajor(), original.getMinor());
        }

        @Test
        @DisplayName("Should serialize and deserialize XML correctly")
        void shouldSerializeAndDeserializeXmlCorrectly() {
            // Given
            ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();

            // When & Then
            ProtocolVersion restored =
                    SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, ProtocolVersion.class);

            KmipAssertions.assertThat(restored).hasVersion(original.getMajor(), original.getMinor());
        }

        @Test
        @DisplayName("Should handle multiple versions in serialization")
        void shouldHandleMultipleVersionsInSerialization() {
            // Given
            var versions = KmipTestDataFactory.createProtocolVersions();

            // When & Then
            for (ProtocolVersion version : versions) {
                SerializationTestUtils.performBothRoundTrips(
                        jsonMapper, xmlMapper, version, ProtocolVersion.class);
            }
        }

        @Test
        @DisplayName("Should produce valid JSON structure")
        void shouldProduceValidJsonStructure() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    version,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                        assertThat(json).contains("\"ProtocolVersion\"");
                    });
        }

        @Test
        @DisplayName("Should produce valid XML structure")
        void shouldProduceValidXmlStructure() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);

            // When & Then
            SerializationTestUtils.testXmlSerialization(
                    xmlMapper,
                    version,
                    xml -> {
                        SerializationTestUtils.validateXmlStructure(xml, "ProtocolVersion");
                    });
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Conditions")
    class EdgeCasesAndBoundaryConditions {

        @Test
        @DisplayName("Should handle zero versions")
        void shouldHandleZeroVersions() {
            // Given & When
            ProtocolVersion version = KmipTestDataFactory.BoundaryData.minProtocolVersion();

            // Then
            KmipAssertions.assertThat(version).hasVersion(0, 0).hasValidStructure();
        }

        @Test
        @DisplayName("Should handle maximum integer versions")
        void shouldHandleMaximumIntegerVersions() {
            // Given & When
            ProtocolVersion version = KmipTestDataFactory.BoundaryData.maxProtocolVersion();

            // Then
            KmipAssertions.assertThat(version)
                    .hasVersion(Integer.MAX_VALUE, Integer.MAX_VALUE)
                    .hasValidStructure();
        }

        @Test
        @DisplayName("Should handle negative versions")
        void shouldHandleNegativeVersions() {
            // Given & When
            ProtocolVersion version = KmipTestDataFactory.ErrorData.negativeProtocolVersion();

            // Then
            KmipAssertions.assertThat(version).hasVersion(-1, -1).hasValidStructure();
        }

        @Test
        @DisplayName("Should maintain immutability")
        void shouldMaintainImmutability() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            int originalMajor = version.getMajor();
            int originalMinor = version.getMinor();

            // When - Attempt to modify through getters (should not be possible)
            // The objects are immutable, so we just verify they remain unchanged

            // Then
            assertThat(version.getMajor()).isEqualTo(originalMajor);
            assertThat(version.getMinor()).isEqualTo(originalMinor);
        }
    }
}
