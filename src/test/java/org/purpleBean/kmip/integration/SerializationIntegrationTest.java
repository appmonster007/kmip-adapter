package org.purpleBean.kmip.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for KMIP serialization/deserialization.
 */
@DisplayName("Serialization Integration Tests")
class SerializationIntegrationTest extends BaseKmipTest {

    private final ProtocolVersion testVersion = ProtocolVersion.of(1, 2);

    @Test
    @DisplayName("JSON round-trip for ProtocolVersion")
    void jsonRoundTrip_protocolVersion() throws JsonProcessingException {
        // Given
        ProtocolVersion version = testVersion;

        // When
        String json = jsonMapper.writeValueAsString(version);
        ProtocolVersion deserialized = jsonMapper.readValue(json, ProtocolVersion.class);

        // Then
        assertThat(deserialized).isEqualTo(version);
    }

    @Test
    @DisplayName("XML round-trip for ProtocolVersion")
    void xmlRoundTrip_protocolVersion() throws Exception {
        // Given
        ProtocolVersion version = testVersion;

        // When
        String xml = xmlMapper.writeValueAsString(version);
        ProtocolVersion deserialized = xmlMapper.readValue(xml, ProtocolVersion.class);

        // Then
        assertThat(deserialized).isEqualTo(version);
    }

    @Test
    @DisplayName("Thread-local context is maintained during serialization")
    void threadLocalContext_maintainedDuringSerialization() throws JsonProcessingException {
        // Given
        KmipSpec originalSpec = KmipContext.getSpec();
        KmipSpec newSpec = KmipSpec.V1_2;
        
        // When/Then
        KmipContext.withSpec(newSpec, () -> {
            try {
                String json = jsonMapper.writeValueAsString(testVersion);
                ProtocolVersion deserialized = jsonMapper.readValue(json, ProtocolVersion.class);
                assertThat(deserialized).isEqualTo(testVersion);
                assertThat(KmipContext.getSpec()).isSameAs(newSpec);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Serialization error", e);
            }
        });
        
        // Original context should be restored
        assertThat(KmipContext.getSpec()).isSameAs(originalSpec);
    }
    
    @Test
    @DisplayName("Handle null values in serialization")
    void handleNullValues() throws JsonProcessingException {
        // Given
        ProtocolVersion nullVersion = null;
        
        // When/Then - Should not throw
        String json = jsonMapper.writeValueAsString(nullVersion);
        ProtocolVersion deserialized = jsonMapper.readValue(json, ProtocolVersion.class);
        assertThat(deserialized).isNull();
    }
    
    @Test
    @DisplayName("Enum serialization")
    void enumSerialization() throws JsonProcessingException {
        // Given
        State testState = new State(State.Standard.ACTIVE);
        
        // When
        String json = jsonMapper.writeValueAsString(testState);
        State deserialized = jsonMapper.readValue(json, State.class);
        
        // Then
        assertThat(deserialized).isEqualTo(testState);
    }
}
