package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipAssertions;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("KmipTag Tests")
class KmipTagTest extends BaseKmipTest {

    // Helper method to clean up test tags
    private void cleanupTestTags() {
        KmipTag.registeredValues().stream()
                .filter(KmipTag.Value::isCustom)
                .filter(v -> v.getDescription() != null && v.getDescription().startsWith("TestCustomTag"))
                .toList() // Create a copy to avoid concurrent modification
                .forEach(
                        v -> {
                            try {
                                // Use reflection to clean up test tags from registries
                                cleanUpRegistry("VALUE_REGISTRY", v.getValue());
                                cleanUpRegistry("DESCRIPTION_REGISTRY", v.getDescription());
                                cleanUpRegistry("EXTENSION_DESCRIPTION_REGISTRY", v.getDescription());
                            } catch (Exception e) {
                                throw new RuntimeException("Failed to clean up test tags", e);
                            }
                        });
    }

    @SuppressWarnings("unchecked")
    private <T> void cleanUpRegistry(String fieldName, T key) throws Exception {
        Field field = KmipTag.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        Map<T, ?> registry = (Map<T, ?>) field.get(null);
        if (registry != null) {
            ((Map<T, ?>) field.get(null)).remove(key);
        }
    }

    @Nested
    @DisplayName("Standard Tag Operations")
    class StandardTagOperations {

        @Test
        @DisplayName("Should create KmipTag with standard value")
        void shouldCreateKmipTagWithStandardValue() {
            // Given
            KmipTag.Standard standardTag = KmipTag.Standard.PROTOCOL_VERSION;

            // When
            KmipTag tag = new KmipTag(standardTag);

            // Then
            KmipAssertions.assertThat(tag)
                    .hasValue(standardTag.getValue())
                    .hasDescription(standardTag.getDescription())
                    .isStandard()
                    .hasValidTagBytes();
        }

        @ParameterizedTest
        @EnumSource(KmipTag.Standard.class)
        @DisplayName("Should handle all standard tags correctly")
        void shouldHandleAllStandardTagsCorrectly(KmipTag.Standard standardTag) {
            // When
            KmipTag tag = new KmipTag(standardTag);

            // Then
            assertThat(tag.getValue()).isEqualTo(standardTag);
            assertThat(tag.getDescription()).isEqualTo(standardTag.getDescription());
            assertThat(tag.getTagBytes()).hasSize(3);
            assertThat(tag.getTagHexString()).startsWith("0x");
        }

        @Test
        @DisplayName("Should support version compatibility")
        void shouldSupportVersionCompatibility() {
            // Given
            KmipTag tag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION);

            // When & Then
            assertThat(tag.getValue().isSupportedFor(KmipSpec.V1_2)).isTrue();
            assertThat(tag.getValue().isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        }

        @Test
        @DisplayName("Standard tags should not be supported for UnsupportedVersion")
        void standardTagsShouldNotBeSupportedForUnsupportedVersion() {
            // Given
            KmipSpec unsupported = KmipSpec.UnsupportedVersion;

            // When & Then - pick representative standard tags
            assertThat(KmipTag.Standard.PROTOCOL_VERSION.isSupportedFor(unsupported)).isFalse();
            assertThat(KmipTag.Standard.STATE.isSupportedFor(unsupported)).isFalse();
            assertThat(KmipTag.Standard.SECRET_DATA.isSupportedFor(unsupported)).isFalse();
        }
    }

    @Nested
    @DisplayName("Custom Tag Operations")
    class CustomTagOperations {

        @Test
        @DisplayName("Should register custom tag successfully")
        void shouldRegisterCustomTagSuccessfully() {
            try {
                // Clean up any leftover test tags first
                cleanupTestTags();

                // Given
                int customValue = 0x540001;
                String customDescription =
                        "TestCustomTag" + System.currentTimeMillis(); // Ensure unique name
                Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

                // When
                KmipTag.Value customTag =
                        KmipTag.register(customValue, customDescription, supportedVersions);
                KmipTag tag = new KmipTag(customTag);

                // Then
                KmipAssertions.assertThat(tag)
                        .hasValue(customValue)
                        .hasDescription(customDescription)
                        .isCustom()
                        .hasValidTagBytes();

                // Verify registration in the registry
                assertThat(KmipTag.fromValue(KmipSpec.V1_2, customValue)).isEqualTo(customTag);
                assertThat(KmipTag.fromName(KmipSpec.V1_2, customDescription)).isEqualTo(customTag);
            } finally {
                cleanupTestTags();
            }
        }

        @ParameterizedTest
        @ValueSource(
                ints = {
                        0x420001, // Standard range, not extension
                        0x550000, // Above extension range
                        -1, // Negative value
                        0xFFFFFFFF // Max int, way above extension range
                })
        @DisplayName("Should reject invalid extension values")
        void shouldRejectInvalidExtensionValues(int invalidValue) {
            // When & Then
            assertThatThrownBy(
                    () ->
                            KmipTag.register(
                                    invalidValue,
                                    "Invalid" + invalidValue,
                                    Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(
                            String.format(
                                    "Extension value %d must be between 0x540000 and 0x54FFFF", invalidValue));
        }

        @Test
        @DisplayName("Should handle null parameters gracefully")
        void shouldHandleNullParametersGracefully() {
            // Given
            int validValue = 0x540002;
            String validDescription = "TestTag";
            Set<KmipSpec> validVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

            // When & Then - Verify null description throws NullPointerException
            assertThatThrownBy(() -> KmipTag.register(validValue, null, validVersions))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("description is marked non-null but is null");

            // When & Then - Verify empty description throws IllegalArgumentException
            assertThatThrownBy(() -> KmipTag.register(validValue, "", validVersions))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Description cannot be empty");

            // When & Then - Verify empty versions set throws IllegalArgumentException
            assertThatThrownBy(() -> KmipTag.register(validValue, validDescription, Set.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("At least one supported version must be specified");

            // When & Then - Verify null versions throws NullPointerException
            assertThatThrownBy(() -> KmipTag.register(validValue, validDescription, null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("Lookup Operations")
    class LookupOperations {

        @Test
        @DisplayName("Should find tag by value")
        void shouldFindTagByValue() {
            // Given
            KmipTag.Standard expected = KmipTag.Standard.PROTOCOL_VERSION;

            // When
            KmipTag.Value found = KmipTag.fromValue(KmipSpec.V1_2, expected.getValue());

            // Then
            assertThat(found).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should reject lookups under UnsupportedVersion")
        void shouldRejectLookupsUnderUnsupportedVersion() {
            // Given
            KmipSpec unsupported = KmipSpec.UnsupportedVersion;
            KmipTag.Standard standard = KmipTag.Standard.PROTOCOL_VERSION;

            // When & Then
            assertThatThrownBy(() -> KmipTag.fromValue(unsupported, standard.getValue()))
                    .isInstanceOf(RuntimeException.class);
            assertThatThrownBy(() -> KmipTag.fromName(unsupported, standard.getDescription()))
                    .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("Should find tag by name")
        void shouldFindTagByName() {
            // Given
            KmipTag.Standard expected = KmipTag.Standard.PROTOCOL_VERSION;

            // When
            KmipTag.Value found = KmipTag.fromName(KmipSpec.V1_2, expected.getDescription());

            // Then
            assertThat(found).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should throw exception for unknown value")
        void shouldThrowExceptionForUnknownValue() {
            // Given
            int unknownValue = 0x999999;

            // When & Then
            assertThatThrownBy(() -> KmipTag.fromValue(KmipSpec.V1_2, unknownValue))
                    .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("Should throw exception for unknown name")
        void shouldThrowExceptionForUnknownName() {
            // Given
            String unknownName = "NonExistentTag";

            // When & Then
            assertThatThrownBy(() -> KmipTag.fromName(KmipSpec.V1_2, unknownName))
                    .isInstanceOf(RuntimeException.class);
        }
    }

    @Nested
    @DisplayName("Byte Operations")
    class ByteOperations {

        @Test
        @DisplayName("Should create tag from valid bytes")
        void shouldCreateTagFromValidBytes() {
            // Given
            byte[] validBytes = {0x42, 0x00, 0x69}; // PROTOCOL_VERSION tag

            // When
            KmipTag.Value tag = KmipTag.fromBytes(KmipSpec.V1_2, validBytes);

            // Then
            assertThat(tag).isEqualTo(KmipTag.Standard.PROTOCOL_VERSION);
        }

        @Test
        @DisplayName("Should reject invalid byte array length")
        void shouldRejectInvalidByteArrayLength() {
            // Given
            byte[] invalidBytes = {0x42, 0x00}; // Only 2 bytes

            // When & Then
            assertThatThrownBy(() -> KmipTag.fromBytes(KmipSpec.V1_2, invalidBytes))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Expected 3 byte array for tag");
        }

        @Test
        @DisplayName("Should reject null byte array")
        void shouldRejectNullByteArray() {
            // When & Then
            assertThatThrownBy(() -> KmipTag.fromBytes(KmipSpec.V1_2, null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Expected 3 byte array for tag");
        }

        @ParameterizedTest
        @ValueSource(ints = {0x420001, 0x420069, 0x42008D})
        @DisplayName("Should generate correct tag bytes for standard values")
        void shouldGenerateCorrectTagBytesForStandardValues(int tagValue) {
            // Given
            KmipTag.Value tag = KmipTag.fromValue(KmipSpec.V1_2, tagValue);

            // When
            byte[] tagBytes = new KmipTag(tag).getTagBytes();

            // Then
            assertThat(tagBytes).hasSize(3);
            int reconstructedValue =
                    ((tagBytes[0] & 0xFF) << 16) | ((tagBytes[1] & 0xFF) << 8) | (tagBytes[2] & 0xFF);
            assertThat(reconstructedValue).isEqualTo(tagValue);
        }
    }

    @Nested
    @DisplayName("Registry Operations")
    class RegistryOperations {

        @Test
        @DisplayName("Should return registered custom values")
        void shouldReturnRegisteredCustomValues() {
            // Given
            int customValue1 = 0x540010;
            int customValue2 = 0x540011;
            KmipTag.register(customValue1, "Custom1", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            KmipTag.register(customValue2, "Custom2", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // When
            var registeredValues = KmipTag.registeredValues();

            // Then
            assertThat(registeredValues)
                    .isNotEmpty()
                    .anyMatch(v -> v.getValue() == customValue1)
                    .anyMatch(v -> v.getValue() == customValue2);
        }

        @Test
        @DisplayName("Should handle duplicate registration")
        void shouldHandleDuplicateRegistration() {
            // Given
            int customValue = 0x540020;
            String description1 = "First";
            String description2 = "Second";

            // When
            KmipTag.Value first =
                    KmipTag.register(
                            customValue, description1, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            KmipTag.Value second =
                    KmipTag.register(
                            customValue, description2, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // Then - Registry creates new instances for each registration
            assertThat(first).isNotSameAs(second);
            assertThat(first.getValue()).isEqualTo(second.getValue());
            // Registry behavior: each registration creates its own instance with its own description
            assertThat(first.getDescription()).isEqualTo(description1);
            assertThat(second.getDescription()).isEqualTo(description2);
            // Registry stores the latest registration
            KmipTag.Value fromRegistry = KmipTag.fromValue(KmipSpec.V1_2, customValue);
            assertThat(fromRegistry.getDescription()).isEqualTo(description2);
        }
    }

    @Nested
    @DisplayName("Hex String Operations")
    class HexStringOperations {

        @Test
        @DisplayName("Should generate correct hex string")
        void shouldGenerateCorrectHexString() {
            // Given
            KmipTag tag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION);

            // When
            String hexString = tag.getTagHexString();

            // Then
            assertThat(hexString).startsWith("0x").hasSize(8); // 0x + 6 hex chars
            KmipAssertions.assertThat(tag).hasHexString("0x420069");
        }

        @Test
        @DisplayName("Should format hex string with proper padding")
        void shouldFormatHexStringWithProperPadding() {
            // Given - Create a tag with value that needs padding
            KmipTag.register(0x540001, "PaddingTest", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            KmipTag.Value customTag = KmipTag.fromValue(KmipSpec.V1_2, 0x540001);
            KmipTag tag = new KmipTag(customTag);

            // When
            String hexString = tag.getTagHexString();

            // Then
            assertThat(hexString).isEqualTo("0x540001");
        }
    }
}
