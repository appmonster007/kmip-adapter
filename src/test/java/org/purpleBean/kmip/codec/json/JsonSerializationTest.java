package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("JSON Serialization Tests")
class JsonSerializationTest extends BaseKmipTest {

    @Nested
    @DisplayName("Protocol Version JSON Serialization")
    class ProtocolVersionJsonSerialization {

        @Test
        @DisplayName("Should serialize and deserialize ProtocolVersion correctly")
        void shouldSerializeAndDeserializeProtocolVersionCorrectly() {
            // Given
            ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, ProtocolVersion.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"0,0", "1,0", "1,2", "2,1", "99,99"})
        @DisplayName("Should handle various protocol versions")
        void shouldHandleVariousProtocolVersions(String versionPair) {
            // Given
            String[] parts = versionPair.split(",");
            ProtocolVersion version =
                    ProtocolVersion.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
        }

        @Test
        @DisplayName("Should produce expected JSON structure for ProtocolVersion")
        void shouldProduceExpectedJsonStructureForProtocolVersion() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    version,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                        assertThat(json).contains("\"ProtocolVersion\"");
                        assertThat(json).contains("\"ProtocolVersionMajor\"");
                        assertThat(json).contains("\"ProtocolVersionMinor\"");
                        assertThat(json).contains("\"value\":1");
                        assertThat(json).contains("\"value\":2");
                    });
        }
    }

    @Nested
    @DisplayName("State JSON Serialization")
    class StateJsonSerialization {

        @Test
        @DisplayName("Should serialize and deserialize standard State correctly")
        void shouldSerializeAndDeserializeStandardStateCorrectly() {
            // Given
            State original = KmipTestDataFactory.createState();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, State.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize custom State correctly")
        void shouldSerializeAndDeserializeCustomStateCorrectly() {
            // Given
            State original = KmipTestDataFactory.createCustomState();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, State.class);
        }

        @Test
        @DisplayName("Should handle all standard states")
        void shouldHandleAllStandardStates() {
            // Given
            List<State> states = KmipTestDataFactory.createStates();

            // When & Then
            for (State state : states) {
                SerializationTestUtils.performJsonRoundTrip(jsonMapper, state, State.class);
            }
        }

        @Test
        @DisplayName("Should produce expected JSON structure for State")
        void shouldProduceExpectedJsonStructureForState() {
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
    @DisplayName("ActivationDateAttribute JSON Serialization")
    class ActivationDateAttributeJsonSerialization {

        @Test
        @DisplayName("Should serialize and deserialize ActivationDateAttribute correctly")
        void shouldSerializeAndDeserializeActivationDateAttributeCorrectly() {
            // Given
            ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(
                    jsonMapper, original, ActivationDateAttribute.class);
        }

        @Test
        @DisplayName("Should handle various date formats")
        void shouldHandleVariousDateFormats() {
            // Given
            List<ActivationDateAttribute> dates =
                    List.of(
                            KmipTestDataFactory.createActivationDateAttribute(
                                    KmipTestDataFactory.BoundaryData.epochDateTime()),
                            KmipTestDataFactory.createActivationDateAttribute(OffsetDateTime.now()),
                            KmipTestDataFactory.createRandomActivationDateAttribute());

            // When & Then
            for (ActivationDateAttribute date : dates) {
                SerializationTestUtils.performJsonRoundTrip(
                        jsonMapper, date, ActivationDateAttribute.class);
            }
        }

        @Test
        @DisplayName("Should produce expected JSON structure for ActivationDateAttribute")
        void shouldProduceExpectedJsonStructureForActivationDateAttribute() {
            // Given
            ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    attribute,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                        assertThat(json).contains("\"ActivationDate\"");
                    });
        }
    }

    @Nested
    @DisplayName("SampleStructure JSON Serialization")
    class SampleStructureJsonSerialization {

        @Test
        @DisplayName("Should serialize and deserialize SampleStructure correctly")
        void shouldSerializeAndDeserializeSampleStructureCorrectly() {
            // Given
            SampleStructure original = KmipTestDataFactory.createSampleStructure();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, SampleStructure.class);
        }

        @Test
        @DisplayName("Should handle complex nested structures")
        void shouldHandleComplexNestedStructures() {
            // Given
            List<SampleStructure> structures = KmipTestDataFactory.createSampleStructures(5);

            // When & Then
            for (SampleStructure structure : structures) {
                SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
            }
        }

        @Test
        @DisplayName("Should produce expected JSON structure for SampleStructure")
        void shouldProduceExpectedJsonStructureForSampleStructure() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    structure,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                        assertThat(json).contains("\"SecretData\"");
                    });
        }
    }

    @Nested
    @DisplayName("JSON Error Handling")
    class JsonErrorHandling {

        @Test
        @DisplayName("Should handle malformed JSON gracefully")
        void shouldHandleMalformedJsonGracefully() {
            // Given
            String malformedJson = "{\"protocolVersionMajor\": {\"value\": \"not_a_number\"}}";

            // When & Then
            assertThatThrownBy(
                    () ->
                            SerializationTestUtils.testJsonDeserialization(
                                    jsonMapper, malformedJson, ProtocolVersion.class))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        @DisplayName("Should handle missing required fields")
        void shouldHandleMissingRequiredFields() {
            // Given
            String incompleteJson = "{\"protocolVersionMajor\": {\"value\": 1}}"; // Missing minor

            // When & Then
            assertThatThrownBy(
                    () ->
                            SerializationTestUtils.testJsonDeserialization(
                                    jsonMapper, incompleteJson, ProtocolVersion.class))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        @DisplayName("Should handle large datasets efficiently")
        void shouldHandleLargeDatasetsEfficiently() {
            // Given
            List<SampleStructure> largeDataset =
                    KmipTestDataFactory.PerformanceData.largeSampleStructureList();

            // When
            long startTime = System.currentTimeMillis();

            for (SampleStructure structure :
                    largeDataset.subList(0, Math.min(100, largeDataset.size()))) {
                SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
            }

            long endTime = System.currentTimeMillis();

            // Then
            assertThat(endTime - startTime).isLessThan(5000); // Should complete within 5 seconds
        }
    }
}
