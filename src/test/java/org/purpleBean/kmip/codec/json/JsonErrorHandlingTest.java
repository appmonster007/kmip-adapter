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

@DisplayName("JSON Error Handling Tests")
class JsonErrorHandlingTest extends BaseKmipTest {

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
