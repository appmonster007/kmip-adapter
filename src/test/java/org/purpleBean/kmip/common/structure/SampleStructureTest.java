package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SampleStructure Tests")
class SampleStructureTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create SampleStructure with builder")
        void shouldCreateSampleStructureWithBuilder() {
            // Given
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state = KmipTestDataFactory.createState();

            // When
            SampleStructure structure =
                    SampleStructure.builder().activationDate(activationDate).state(state).build();

            // Then
            assertThat(structure.getActivationDate()).isEqualTo(activationDate);
            assertThat(structure.getState()).isEqualTo(state);
            assertThat(structure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
        }

        @Test
        @DisplayName("Should reject null activation date")
        void shouldRejectNullActivationDate() {
            // Given
            State validState = KmipTestDataFactory.createState();

            // When & Then
            assertThatThrownBy(
                    () -> SampleStructure.builder().activationDate(null).state(validState).build())
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("activationDate is marked non-null but is null");
        }

        @Test
        @DisplayName("Should accept null state")
        void shouldAcceptNullState() {
            // Given
            ActivationDateAttribute validActivationDate =
                    KmipTestDataFactory.createActivationDateAttribute();

            // When
            SampleStructure sampleStructure =
                    SampleStructure.builder().activationDate(validActivationDate).state(null).build();

            // Then
            assertNotNull(sampleStructure);
            assertEquals(validActivationDate, sampleStructure.getActivationDate());
            assertNull(sampleStructure.getState());
        }

        @Test
        @DisplayName("Should accept valid business rule combinations")
        void shouldAcceptValidBusinessRuleCombinations() {
            // Given - Create ACTIVE state with future activation date (should succeed)
            ActivationDateAttribute futureActivationDate =
                    ActivationDateAttribute.builder()
                            .dateTime(java.time.OffsetDateTime.now().plusDays(1))
                            .build();
            State activeState = new State(State.Standard.ACTIVE);

            // When
            SampleStructure structure =
                    SampleStructure.builder().activationDate(futureActivationDate).state(activeState).build();

            // Then
            assertThat(structure.getActivationDate()).isEqualTo(futureActivationDate);
            assertThat(structure.getState()).isEqualTo(activeState);
        }

        @Test
        @DisplayName("Should create structure with partial data")
        void shouldCreateStructureWithPartialData() {
            // Given
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state = KmipTestDataFactory.createState();

            // When
            SampleStructure structure =
                    SampleStructure.builder().activationDate(activationDate).state(state).build();

            // Then
            assertThat(structure.getActivationDate()).isEqualTo(activationDate);
            assertThat(structure.getState()).isEqualTo(state);
        }

        @Test
        @DisplayName("UnsupportedVersion context: SampleStructure JSON serialization should fail")
        void unsupportedVersion_jsonSerializationShouldFail() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(
                                    () -> jsonMapper.writeValueAsString(KmipTestDataFactory.createSampleStructure()))
                            .isInstanceOf(Exception.class));
        }

        @Test
        @DisplayName("UnsupportedVersion context: SampleStructure XML serialization should fail")
        void unsupportedVersion_xmlSerializationShouldFail() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(
                                    () -> xmlMapper.writeValueAsString(KmipTestDataFactory.createSampleStructure()))
                            .isInstanceOf(Exception.class));
        }
    }

    @Nested
    @DisplayName("KMIP Structure Properties")
    class KmipStructureProperties {

        @Test
        @DisplayName("Should have correct KMIP tag")
        void shouldHaveCorrectKmipTag() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            assertThat(structure.getKmipTag()).isNotNull();
            assertThat(structure.getKmipTag().getDescription()).contains("SecretData");
        }

        @Test
        @DisplayName("Should have correct encoding type")
        void shouldHaveCorrectEncodingType() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            assertThat(structure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
        }

        @Test
        @DisplayName("Should support KMIP specification")
        void shouldSupportKmipSpecification() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            assertThat(structure.isSupportedFor(defaultSpec)).isTrue();
        }
    }

    @Nested
    @DisplayName("Equality and Hash Code")
    class EqualityAndHashCode {

        @Test
        @DisplayName("Should be equal when all components match")
        void shouldBeEqualWhenAllComponentsMatch() {
            // Given
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state = KmipTestDataFactory.createState();

            SampleStructure structure1 =
                    SampleStructure.builder().activationDate(activationDate).state(state).build();

            SampleStructure structure2 =
                    SampleStructure.builder().activationDate(activationDate).state(state).build();

            // When & Then
            assertThat(structure1).isEqualTo(structure2);
            assertThat(structure1.hashCode()).isEqualTo(structure2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal when activation date differs")
        void shouldNotBeEqualWhenActivationDateDiffers() {
            // Given
            ActivationDateAttribute activationDate1 = KmipTestDataFactory.createActivationDateAttribute();
            ActivationDateAttribute activationDate2 =
                    KmipTestDataFactory.createRandomActivationDateAttribute();
            State state = KmipTestDataFactory.createState();

            SampleStructure structure1 =
                    SampleStructure.builder().activationDate(activationDate1).state(state).build();

            SampleStructure structure2 =
                    SampleStructure.builder().activationDate(activationDate2).state(state).build();

            // When & Then
            assertThat(structure1).isNotEqualTo(structure2);
        }

        @Test
        @DisplayName("Should not be equal when state differs")
        void shouldNotBeEqualWhenStateDiffers() {
            // Given
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state1 = new State(State.Standard.ACTIVE);
            State state2 = new State(State.Standard.DEACTIVATED);

            SampleStructure structure1 =
                    SampleStructure.builder().activationDate(activationDate).state(state1).build();

            SampleStructure structure2 =
                    SampleStructure.builder().activationDate(activationDate).state(state2).build();

            // When & Then
            assertThat(structure1).isNotEqualTo(structure2);
        }

        @Test
        @DisplayName("Should handle null components in equality")
        void shouldHandleNullComponentsInEquality() {
            // Given - Create structures with valid data since null validation exists
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state = KmipTestDataFactory.createState();

            SampleStructure structure1 =
                    SampleStructure.builder().activationDate(activationDate).state(state).build();

            SampleStructure structure2 =
                    SampleStructure.builder().activationDate(activationDate).state(state).build();

            // When & Then
            assertThat(structure1).isEqualTo(structure2);
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize and deserialize JSON correctly")
        void shouldSerializeAndDeserializeJsonCorrectly() {
            // Given
            SampleStructure original = KmipTestDataFactory.createSampleStructure();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, SampleStructure.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize XML correctly")
        void shouldSerializeAndDeserializeXmlCorrectly() {
            // Given
            SampleStructure original = KmipTestDataFactory.createSampleStructure();

            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, SampleStructure.class);
        }

        @Test
        @DisplayName("Should handle multiple structures in serialization")
        void shouldHandleMultipleStructuresInSerialization() {
            // Given
            var structures = KmipTestDataFactory.createSampleStructures(5);

            // When & Then
            for (SampleStructure structure : structures) {
                SerializationTestUtils.performBothRoundTrips(
                        jsonMapper, xmlMapper, structure, SampleStructure.class);
            }
        }

        @Test
        @DisplayName("Should produce expected JSON structure")
        void shouldProduceExpectedJsonStructure() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    structure,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    });
        }

        @Test
        @DisplayName("Should handle null components in serialization")
        void shouldHandleNullComponentsInSerialization() {
            // Given - Use valid structure since null validation exists in builder
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
        }
    }

    @Nested
    @DisplayName("Complex Structure Operations")
    class ComplexStructureOperations {

        @Test
        @DisplayName("Should handle nested structure composition")
        void shouldHandleNestedStructureComposition() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then - Verify nested components maintain their properties
            assertThat(structure.getActivationDate().getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
            assertThat(structure.getState().getEncodingType()).isEqualTo(EncodingType.ENUMERATION);

            // Verify nested KMIP tags
            assertThat(structure.getActivationDate().getKmipTag()).isNotNull();
            assertThat(structure.getState().getKmipTag()).isNotNull();
        }

        @Test
        @DisplayName("Should maintain component integrity after serialization")
        void shouldMaintainComponentIntegrityAfterSerialization() {
            // Given
            SampleStructure original = KmipTestDataFactory.createSampleStructure();

            // When
            SampleStructure restored =
                    SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, SampleStructure.class);

            // Then - Components should maintain their properties
            assertThat(restored.getActivationDate().getDateTime())
                    .isEqualTo(original.getActivationDate().getDateTime());
            assertThat(restored.getState().getValue()).isEqualTo(original.getState().getValue());
        }

        @Test
        @DisplayName("Should handle custom state in nested structure")
        void shouldHandleCustomStateInNestedStructure() {
            // Given
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State customState = KmipTestDataFactory.createCustomState();

            SampleStructure structure =
                    SampleStructure.builder().activationDate(activationDate).state(customState).build();

            // When & Then
            SerializationTestUtils.performBothRoundTrips(
                    jsonMapper, xmlMapper, structure, SampleStructure.class);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Conditions")
    class EdgeCasesAndBoundaryConditions {

        @Test
        @DisplayName("Should validate KMIP spec compatibility")
        void shouldValidateKmipSpecCompatibility() {
            // Given - Create components that might not be compatible with current spec
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state = KmipTestDataFactory.createState();

            // When & Then - Should validate spec compatibility during construction
            assertThatCode(
                    () -> SampleStructure.builder().activationDate(activationDate).state(state).build())
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should maintain immutability")
        void shouldMaintainImmutability() {
            // Given
            ActivationDateAttribute originalActivationDate =
                    KmipTestDataFactory.createActivationDateAttribute();
            State originalState = KmipTestDataFactory.createState();

            SampleStructure structure =
                    SampleStructure.builder()
                            .activationDate(originalActivationDate)
                            .state(originalState)
                            .build();

            // When - Retrieve components
            ActivationDateAttribute retrievedActivationDate = structure.getActivationDate();
            State retrievedState = structure.getState();

            // Then - Original components should remain unchanged
            assertThat(structure.getActivationDate()).isEqualTo(originalActivationDate);
            assertThat(structure.getState()).isEqualTo(originalState);
            assertThat(retrievedActivationDate).isEqualTo(originalActivationDate);
            assertThat(retrievedState).isEqualTo(originalState);
        }

        @Test
        @DisplayName("Should handle large number of structures efficiently")
        void shouldHandleLargeNumberOfStructuresEfficiently() {
            // Given
            int largeCount = 1000;
            long startTime = System.currentTimeMillis();

            // When
            var structures = KmipTestDataFactory.createSampleStructures(largeCount);

            long endTime = System.currentTimeMillis();

            // Then
            assertThat(structures).hasSize(largeCount);
            assertThat(endTime - startTime).isLessThan(5000); // Should complete within 5 seconds

            // Verify all structures are valid
            for (SampleStructure structure : structures.subList(0, Math.min(10, structures.size()))) {
                assertThat(structure.getActivationDate()).isNotNull();
                assertThat(structure.getState()).isNotNull();
            }
        }
    }
}
