package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("State Tests")
class StateTest extends BaseKmipTest {

    @Nested
    @DisplayName("Standard State Operations")
    class StandardStateOperations {

        @Test
        @DisplayName("Should create State with standard value")
        void shouldCreateStateWithStandardValue() {
            // Given
            State.Standard standardState = State.Standard.ACTIVE;

            // When
            State state = new State(standardState);

            // Then
            assertThat(state.getValue()).isInstanceOf(State.Standard.class);
            assertThat(state.getValue().getValue())
                    .isEqualTo(standardState.getValue());
            assertThat(state.getDescription()).isEqualTo(standardState.getDescription());
            assertThat(state.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.STATE);
            assertThat(state.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }

        @ParameterizedTest
        @EnumSource(State.Standard.class)
        @DisplayName("Should handle all standard states correctly")
        void shouldHandleAllStandardStatesCorrectly(State.Standard standardState) {
            // When
            State state = new State(standardState);

            // Then
            assertThat(state.getValue()).isInstanceOf(State.Standard.class);
            assertThat(state.getValue().getValue())
                    .isEqualTo(standardState.getValue());
            assertThat(state.getDescription()).isEqualTo(standardState.getDescription());
            assertThat(state.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.STATE);
            assertThat(state.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }

        @Test
        @DisplayName("Should support version compatibility for standard states")
        void shouldSupportVersionCompatibilityForStandardStates() {
            // Given
            State state = new State(State.Standard.ACTIVE);

            // When & Then
            assertThat(state.isSupportedFor(KmipSpec.V1_2)).isTrue();
            assertThat(state.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("Custom State Operations")
    class CustomStateOperations {

        @Test
        @DisplayName("Should register and create custom state successfully")
        void shouldRegisterAndCreateCustomStateSuccessfully() {
            // Given
            int customValue = -1000010;
            String customDescription = "TestCustomState";
            Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

            // When
            State.Value customStateValue =
                    State.register(customValue, customDescription, supportedVersions);
            State state = new State(customStateValue);

            // Then
            assertThat(customStateValue.getValue()).isEqualTo(customValue);
            assertThat(customStateValue.getDescription()).isEqualTo(customDescription);
            assertThat(customStateValue.isCustom()).isTrue();
            assertThat(state.getValue()).isSameAs(customStateValue);
            assertThat(customStateValue.toString()).contains(customDescription);
        }

        @Test
        @DisplayName("Should handle custom state lookup by name")
        void shouldHandleCustomStateLookupByName() {
            // Given
            String customName = "CustomTestState";
            State.register(-1000020, customName, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // When
            State.Value foundState = State.fromName(KmipSpec.V1_2, customName);
            State state = new State(foundState);

            // Then
            assertThat(foundState).isNotNull();
            assertThat(foundState.getDescription()).isEqualTo(customName);
            assertThat(foundState.isCustom()).isTrue();
            assertThat(state.getValue()).isSameAs(foundState);
        }

        @Test
        @DisplayName("Should handle custom state lookup by value")
        void shouldHandleCustomStateLookupByValue() {
            // Given
            int customValue = -1000030;
            State.register(
                    customValue, "ValueLookupTest", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // When
            State.Value foundState = State.fromValue(KmipSpec.V1_2, customValue);
            State state = new State(foundState);

            // Then
            assertThat(foundState).isNotNull();
            assertThat(foundState.getValue()).isEqualTo(customValue);
            assertThat(foundState.isCustom()).isTrue();
            assertThat(foundState.getDescription()).isEqualTo("ValueLookupTest");
            assertThat(state.getValue()).isSameAs(foundState);
        }

        @Test
        @DisplayName("Should reject null parameters in registration")
        void shouldRejectNullParametersInRegistration() {
            // Given
            int validValue = -1000040;

            // When & Then
            assertThatThrownBy(
                    () ->
                            State.register(validValue, null, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(NullPointerException.class);

            assertThatThrownBy(() -> State.register(validValue, "Test", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should reject invalid extension values")
        void shouldRejectInvalidExtensionValues() {
            // Given - Standard range values (not extension range)
            int standardValue = 0x00000001;
            int positiveValue = 1000;

            // When & Then
            assertThatThrownBy(
                    () ->
                            State.register(
                                    standardValue,
                                    "InvalidStandard",
                                    Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Extension value");

            assertThatThrownBy(
                    () ->
                            State.register(
                                    positiveValue,
                                    "InvalidPositive",
                                    Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Extension value");
        }

        @Test
        @DisplayName("Should reject empty description")
        void shouldRejectEmptyDescription() {
            // Given
            int validExtensionValue = -1000041;

            // When & Then
            assertThatThrownBy(
                    () ->
                            State.register(
                                    validExtensionValue, "", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Description cannot be empty");

            assertThatThrownBy(
                    () ->
                            State.register(
                                    validExtensionValue, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Description cannot be empty");
        }

        @Test
        @DisplayName("Should reject empty supported versions")
        void shouldRejectEmptySupportedVersions() {
            // Given
            int validExtensionValue = -1000042;

            // When & Then
            assertThatThrownBy(() -> State.register(validExtensionValue, "ValidDescription", Set.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("At least one supported version must be specified");
        }
    }

    @Nested
    @DisplayName("Lookup Operations")
    class LookupOperations {

        @Test
        @DisplayName("Should find standard state by value")
        void shouldFindStandardStateByValue() {
            // Given
            State.Standard expected = State.Standard.ACTIVE;

            // When
            State.Value found = State.fromValue(KmipSpec.V1_2, expected.getValue());

            // Then
            assertThat(found).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should find standard state by name")
        void shouldFindStandardStateByName() {
            // Given
            State.Standard expected = State.Standard.DEACTIVATED;

            // When
            State.Value found = State.fromName(KmipSpec.V1_2, expected.getDescription());

            // Then
            assertThat(found).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should throw exception for unknown value")
        void shouldThrowExceptionForUnknownValue() {
            // Given
            int unknownValue = -9999999;

            // When & Then
            assertThatThrownBy(() -> State.fromValue(KmipSpec.V1_2, unknownValue))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessageContaining("No value found for -9999999");
        }

        @Test
        @DisplayName("Should throw exception for unknown name")
        void shouldThrowExceptionForUnknownName() {
            // Given
            String unknownName = "NonExistentState";

            // When & Then
            assertThatThrownBy(() -> State.fromName(KmipSpec.V1_2, unknownName))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessageContaining("No value found for 'NonExistentState'");
        }

        @Test
        @DisplayName("Should reject null parameters in lookup methods")
        void shouldRejectNullParametersInLookupMethods() {
            // When & Then
            assertThatThrownBy(() -> State.fromValue(null, 1)).isInstanceOf(NullPointerException.class);

            assertThatThrownBy(() -> State.fromName(null, "Active"))
                    .isInstanceOf(NullPointerException.class);

            assertThatThrownBy(() -> State.fromName(KmipSpec.V1_2, null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should filter by KMIP specification")
        void shouldFilterByKmipSpecification() {
            // Given - Register a state only for V1_2
            int customValue = -1000050;
            State.register(customValue, "SpecificVersionState", Set.of(KmipSpec.UnknownVersion));

            // When & Then
            assertThat(State.fromValue(KmipSpec.UnknownVersion, customValue)).isNotNull();
            assertThatThrownBy(() -> State.fromValue(KmipSpec.V1_2, customValue))
                    .isInstanceOf(RuntimeException.class);
        }
    }

    @Nested
    @DisplayName("Registry Operations")
    class RegistryOperations {

        @Test
        @DisplayName("Should return registered custom values")
        void shouldReturnRegisteredCustomValues() {
            // Given
            int customValue1 = -1000060;
            int customValue2 = -1000061;
            State.register(customValue1, "Custom1", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            State.register(customValue2, "Custom2", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // When
            var registeredValues = State.registeredValues();

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
            int customValue = -1000070;
            String description1 = "First";
            String description2 = "Second";

            // When
            State.Value first =
                    State.register(customValue, description1, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            State.Value second =
                    State.register(customValue, description2, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // Then - Registry creates new instances for each registration
            assertThat(first).isNotSameAs(second);
            assertThat(first.getValue()).isEqualTo(second.getValue());
            // Registry behavior: each registration creates its own instance with its own description
            assertThat(first.getDescription()).isEqualTo(description1);
            assertThat(second.getDescription()).isEqualTo(description2);
            // Registry stores the latest registration
            State.Value fromRegistry = State.fromValue(KmipSpec.V1_2, customValue);
            assertThat(fromRegistry.getDescription()).isEqualTo(description2);
        }
    }

    @Nested
    @DisplayName("Equality and Hash Code")
    class EqualityAndHashCode {

        @Test
        @DisplayName("Should be equal when values match")
        void shouldBeEqualWhenValuesMatch() {
            // Given
            State state1 = new State(State.Standard.ACTIVE);
            State state2 = new State(State.Standard.ACTIVE);

            // When & Then
            assertThat(state1).isEqualTo(state2);
            assertThat(state1.hashCode()).isEqualTo(state2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal when values differ")
        void shouldNotBeEqualWhenValuesDiffer() {
            // Given
            State state1 = new State(State.Standard.ACTIVE);
            State state2 = new State(State.Standard.DEACTIVATED);

            // When & Then
            assertThat(state1).isNotEqualTo(state2);
        }

        @Test
        @DisplayName("Should handle equality with custom states")
        void shouldHandleEqualityWithCustomStates() {
            // Given
            int customValue = -1000080;
            State.Value customStateValue =
                    State.register(
                            customValue, "EqualityTest", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            State state1 = new State(customStateValue);
            State state2 = new State(customStateValue);

            // When & Then
            assertThat(state1).isEqualTo(state2);
            assertThat(state1.hashCode()).isEqualTo(state2.hashCode());
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize and deserialize standard state JSON correctly")
        void shouldSerializeAndDeserializeStandardStateJsonCorrectly() {
            // Given
            State original = KmipTestDataFactory.createState();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, State.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize custom state JSON correctly")
        void shouldSerializeAndDeserializeCustomStateJsonCorrectly() {
            // Given
            State original = KmipTestDataFactory.createCustomState();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, State.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize XML correctly")
        void shouldSerializeAndDeserializeXmlCorrectly() {
            // Given
            State original = new State(State.Standard.COMPROMISED);

            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, State.class);
        }

        @Test
        @DisplayName("Should handle all standard states in serialization")
        void shouldHandleAllStandardStatesInSerialization() {
            // Given
            var states = KmipTestDataFactory.createStates();

            // When & Then
            for (State state : states) {
                SerializationTestUtils.performBothRoundTrips(jsonMapper, xmlMapper, state, State.class);
            }
        }

        @Test
        @DisplayName("Should produce expected JSON structure")
        void shouldProduceExpectedJsonStructure() {
            // Given
            State state = new State(State.Standard.ACTIVE);

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    state,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                        assertThat(json).contains("Active");
                    });
        }
    }

    @Nested
    @DisplayName("KMIP Structure Properties")
    class KmipStructureProperties {

        @Test
        @DisplayName("Should have correct KMIP tag")
        void shouldHaveCorrectKmipTag() {
            // Given
            State state = new State(State.Standard.ACTIVE);

            // When & Then
            assertThat(state.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.STATE);
            assertThat(state.getKmipTag().getDescription()).isEqualTo("State");
        }

        @Test
        @DisplayName("Should have correct encoding type")
        void shouldHaveCorrectEncodingType() {
            // Given
            State state = new State(State.Standard.ACTIVE);

            // When & Then
            assertThat(state.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }

        @Test
        @DisplayName("Should support KMIP specification")
        void shouldSupportKmipSpecification() {
            // Given
            State state = new State(State.Standard.ACTIVE);

            // When & Then
            assertThat(state.isSupportedFor(defaultSpec)).isTrue();
        }
    }

    @Nested
    @DisplayName("Edge Cases and Error Conditions")
    class EdgeCasesAndErrorConditions {

        @Test
        @DisplayName("Should handle version filtering correctly")
        void shouldHandleVersionFilteringCorrectly() {
            // Given - Create state with specific version support
            int versionSpecificValue = -1000090;
            State.register(versionSpecificValue, "VersionSpecific", Set.of(KmipSpec.UnknownVersion));

            // When & Then - Should work with supported version
            State.Value found = State.fromValue(KmipSpec.UnknownVersion, versionSpecificValue);
            assertThat(found.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();

            // Should not work with unsupported version
            assertThat(found.isSupportedFor(KmipSpec.V1_2)).isFalse();
        }

        @Test
        @DisplayName("Should maintain thread safety in registration")
        void shouldMaintainThreadSafetyInRegistration() {
            // Given
            int baseValue = -1000100;

            // When - Register multiple states concurrently
            java.util.concurrent.CompletableFuture<Void>[] futures =
                    new java.util.concurrent.CompletableFuture[10];
            for (int i = 0; i < 10; i++) {
                final int index = i;
                futures[i] =
                        java.util.concurrent.CompletableFuture.runAsync(
                                () -> {
                                    State.register(
                                            baseValue - index,
                                            "ThreadTest" + index,
                                            Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
                                });
            }

            // Then - All registrations should complete successfully
            assertThatCode(() -> java.util.concurrent.CompletableFuture.allOf(futures).get())
                    .doesNotThrowAnyException();
        }
    }
}
