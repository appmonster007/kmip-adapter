package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.purpleBean.kmip.test.BaseKmipTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("KmipSpec Tests")
class KmipSpecTest extends BaseKmipTest {

    @Nested
    @DisplayName("Enum Properties")
    class EnumProperties {

        @ParameterizedTest
        @EnumSource(KmipSpec.class)
        @DisplayName("Should have valid major and minor versions")
        void shouldHaveValidMajorAndMinorVersions(KmipSpec spec) {
            // Then
            assertThat(spec.getMajor()).isNotNull();
            assertThat(spec.getMinor()).isNotNull();
        }

        @Test
        @DisplayName("Should have correct V1_2 properties")
        void shouldHaveCorrectV12Properties() {
            // When
            KmipSpec v12 = KmipSpec.V1_2;

            // Then
            assertThat(v12.getMajor()).isEqualTo(1);
            assertThat(v12.getMinor()).isEqualTo(2);
            assertThat(v12.toString()).isEqualTo("V1.2");
        }

        @Test
        @DisplayName("Should have correct UnknownVersion properties")
        void shouldHaveCorrectUnknownVersionProperties() {
            // When
            KmipSpec unknown = KmipSpec.UnknownVersion;

            // Then
            assertThat(unknown.getMajor()).isEqualTo(-1);
            assertThat(unknown.getMinor()).isEqualTo(-1);
            assertThat(unknown.toString()).isEqualTo("V-1.-1");
        }

        @Test
        @DisplayName("Should have UnsupportedVersion enum available with formatted string")
        void shouldHaveUnsupportedVersionAvailable() {
            // When
            KmipSpec unsupported = KmipSpec.UnsupportedVersion;

            // Then
            // We don't assert on specific major/minor values as they are intentionally implementation-defined.
            // But we do assert that the enum exists and its string representation follows the version format.
            assertThat(unsupported).isNotNull();
            assertThat(unsupported.toString()).matches("V-?\\d+\\.-?\\d+");
        }
    }

    @Nested
    @DisplayName("Version Lookup")
    class VersionLookup {

        @Test
        @DisplayName("Should find V1_2 from ProtocolVersion")
        void shouldFindV12FromProtocolVersion() {
            // Given
            ProtocolVersion protocolVersion = ProtocolVersion.of(1, 2);

            // When
            KmipSpec spec = KmipSpec.fromValue(protocolVersion);

            // Then
            assertThat(spec).isEqualTo(KmipSpec.V1_2);
        }

        @Test
        @DisplayName("Should throw exception for unsupported version")
        void shouldThrowExceptionForUnsupportedVersion() {
            // Given
            ProtocolVersion unsupportedVersion = ProtocolVersion.of(99, 99);

            // When & Then
            assertThatThrownBy(() -> KmipSpec.fromValue(unsupportedVersion))
                    .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("Should never resolve to UnsupportedVersion for known protocol versions")
        void shouldNeverResolveToUnsupportedVersionForKnownVersions() {
            // Given
            ProtocolVersion v12 = ProtocolVersion.of(1, 2);

            // When
            KmipSpec resolved = KmipSpec.fromValue(v12);

            // Then
            assertThat(resolved).isEqualTo(KmipSpec.V1_2);
            assertThat(resolved).isNotEqualTo(KmipSpec.UnsupportedVersion);
        }

        @ParameterizedTest
        @CsvSource({"1, 2, V1_2", "-1, -1, UnknownVersion"})
        @DisplayName("Should map protocol versions correctly")
        void shouldMapProtocolVersionsCorrectly(int major, int minor, String expectedSpecName) {
            // Given
            ProtocolVersion protocolVersion = ProtocolVersion.of(major, minor);
            KmipSpec expectedSpec = KmipSpec.valueOf(expectedSpecName);

            // When
            KmipSpec actualSpec = KmipSpec.fromValue(protocolVersion);

            // Then
            assertThat(actualSpec).isEqualTo(expectedSpec);
            assertThat(actualSpec).isNotEqualTo(KmipSpec.UnsupportedVersion);
        }
    }

    @Nested
    @DisplayName("String Representation")
    class StringRepresentation {

        @Test
        @DisplayName("Should format version string correctly")
        void shouldFormatVersionStringCorrectly() {
            // Given
            KmipSpec v12 = KmipSpec.V1_2;

            // When
            String versionString = v12.toString();

            // Then
            assertThat(versionString).matches("V\\d+\\.\\d+");
            assertThat(versionString).isEqualTo("V1.2");
        }

        @Test
        @DisplayName("Should handle negative versions in string format")
        void shouldHandleNegativeVersionsInStringFormat() {
            // Given
            KmipSpec unknown = KmipSpec.UnknownVersion;

            // When
            String versionString = unknown.toString();

            // Then
            assertThat(versionString).isEqualTo("V-1.-1");
        }
    }

    @Nested
    @DisplayName("Equality and Comparison")
    class EqualityAndComparison {

        @Test
        @DisplayName("Should be equal to itself")
        void shouldBeEqualToItself() {
            // Given
            KmipSpec spec = KmipSpec.V1_2;

            // When & Then
            assertThat(spec).isEqualTo(spec);
            assertThat(spec.hashCode()).isEqualTo(spec.hashCode());
        }

        @Test
        @DisplayName("Should be equal to same version")
        void shouldBeEqualToSameVersion() {
            // Given
            KmipSpec spec1 = KmipSpec.V1_2;
            KmipSpec spec2 = KmipSpec.V1_2;

            // When & Then
            assertThat(spec1).isEqualTo(spec2);
            assertThat(spec1.hashCode()).isEqualTo(spec2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different version")
        void shouldNotBeEqualToDifferentVersion() {
            // Given
            KmipSpec v12 = KmipSpec.V1_2;
            KmipSpec unknown = KmipSpec.UnknownVersion;

            // When & Then
            assertThat(v12).isNotEqualTo(unknown);
        }
    }

    @Nested
    @DisplayName("Version Compatibility")
    class VersionCompatibility {

        @Test
        @DisplayName("Should handle version compatibility checks")
        void shouldHandleVersionCompatibilityChecks() {
            // Given
            KmipSpec v12 = KmipSpec.V1_2;

            // When & Then - These are basic checks, actual compatibility logic
            // would be implemented in the components that use KmipSpec
            assertThat(v12.getMajor()).isPositive();
            assertThat(v12.getMinor()).isNotNegative();
        }

        @Test
        @DisplayName("Should distinguish between known and unknown versions")
        void shouldDistinguishBetweenKnownAndUnknownVersions() {
            // Given
            KmipSpec known = KmipSpec.V1_2;
            KmipSpec unknown = KmipSpec.UnknownVersion;

            // When & Then
            assertThat(known.getMajor()).isPositive();
            assertThat(known.getMinor()).isNotNegative();
            assertThat(unknown.getMajor()).isNegative();
            assertThat(unknown.getMinor()).isNegative();
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("Should handle null ProtocolVersion gracefully")
        void shouldHandleNullProtocolVersionGracefully() {
            // When & Then
            assertThatThrownBy(() -> KmipSpec.fromValue(null)).isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should maintain enum contract")
        void shouldMaintainEnumContract() {
            // Given
            KmipSpec[] allSpecs = KmipSpec.values();

            // When & Then
            assertThat(allSpecs).isNotEmpty();
            assertThat(allSpecs)
                    .contains(KmipSpec.V1_2, KmipSpec.UnknownVersion, KmipSpec.UnsupportedVersion);

            // Verify valueOf works for all enum constants
            for (KmipSpec spec : allSpecs) {
                assertThat(KmipSpec.valueOf(spec.name())).isEqualTo(spec);
            }
        }

        @Test
        @DisplayName("Should handle version boundaries")
        void shouldHandleVersionBoundaries() {
            // Given - Test with extreme values that might be added in future
            ProtocolVersion maxVersion = ProtocolVersion.of(Integer.MAX_VALUE, Integer.MAX_VALUE);
            ProtocolVersion minVersion = ProtocolVersion.of(Integer.MIN_VALUE, Integer.MIN_VALUE);

            // When & Then - These should throw exceptions as they're not registered
            assertThatThrownBy(() -> KmipSpec.fromValue(maxVersion)).isInstanceOf(RuntimeException.class);
            assertThatThrownBy(() -> KmipSpec.fromValue(minVersion)).isInstanceOf(RuntimeException.class);
        }

    }
}
