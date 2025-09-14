package org.purpleBean.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.KmipJsonModule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SimpleRequestHeader Tests")
class SimpleRequestHeaderTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create SimpleRequestHeader with protocol version")
        void shouldCreateWithProtocolVersion() {
            // Given
            ProtocolVersion protocolVersion = ProtocolVersion.of(1, 4);

            // When
            SimpleRequestHeader header =
                    SimpleRequestHeader.builder().protocolVersion(protocolVersion).build();

            // Then
            assertThat(header.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.REQUEST_HEADER));
            assertThat(header.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(header.getProtocolVersion()).isEqualTo(protocolVersion);
            assertThat(header.getValues()).hasSize(1).contains(protocolVersion);
        }

        @ParameterizedTest
        @CsvSource({"1,0","1,2","1,4","2,0","2,1"})
        @DisplayName("Should create headers for multiple protocol versions")
        void shouldCreateHeadersForMultipleProtocolVersions(int major, int minor) {
            // Given
            ProtocolVersion protocolVersion = ProtocolVersion.of(major, minor);

            // When
            SimpleRequestHeader header =
                    SimpleRequestHeader.builder().protocolVersion(protocolVersion).build();

            // Then
            assertThat(header.getProtocolVersion().getMajor()).isEqualTo(major);
            assertThat(header.getProtocolVersion().getMinor()).isEqualTo(minor);
            assertThat(header.getValues()).hasSize(1).contains(protocolVersion);
        }

        @Test
        @DisplayName("Should reject null protocol version")
        void shouldRejectNullProtocolVersion() {
            // When & Then
            assertThatThrownBy(() -> SimpleRequestHeader.builder().protocolVersion(null).build())
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("KMIP Specification Support")
    class KmipSpecSupport {

        @Test
        @DisplayName("Should support all KMIP versions")
        void shouldSupportAllKmipVersions() {
            // Given
            SimpleRequestHeader header =
                    SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(1, 4)).build();

            // Then
            // Test with available versions
            assertThat(header.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("Equals and HashCode")
    class EqualsAndHashCode {

        @Test
        @DisplayName("Should be equal when protocol versions are equal")
        void shouldBeEqualWhenProtocolVersionsAreEqual() {
            // Given
            ProtocolVersion version1 = ProtocolVersion.of(1, 4);
            ProtocolVersion version2 = ProtocolVersion.of(1, 4);
            SimpleRequestHeader header1 = SimpleRequestHeader.builder().protocolVersion(version1).build();
            SimpleRequestHeader header2 = SimpleRequestHeader.builder().protocolVersion(version2).build();

            // Then
            assertThat(header1).isEqualTo(header2);
            assertThat(header1).hasSameHashCodeAs(header2);
        }

        @Test
        @DisplayName("Should not be equal when protocol versions differ")
        void shouldNotBeEqualWhenProtocolVersionsDiffer() {
            // Given
            SimpleRequestHeader header1 =
                    SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(1, 4)).build();
            SimpleRequestHeader header2 =
                    SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(2, 0)).build();

            // Then
            assertThat(header1).isNotEqualTo(header2);
            assertThat(header1.hashCode()).isNotEqualTo(header2.hashCode());
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize to JSON")
        void shouldSerializeToJson() throws JsonProcessingException {
            // Given
            SimpleRequestHeader header =
                    SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(1, 4)).build();

            // When
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(new KmipJsonModule());
            String json = jsonMapper.writeValueAsString(header);

            // Then
            assertThat(json).contains("\"tag\":\"RequestHeader\"");
            assertThat(json).contains("\"type\":\"Structure\"");
            assertThat(json).contains("\"ProtocolVersion\"");
            assertThat(json)
                    .contains("\"tag\":\"ProtocolVersionMajor\",\"type\":\"Integer\",\"value\":1");
            assertThat(json)
                    .contains("\"tag\":\"ProtocolVersionMinor\",\"type\":\"Integer\",\"value\":4");
        }

        @Test
        @DisplayName("Should deserialize from JSON")
        void shouldDeserializeFromJson() throws JsonProcessingException {
            // Given
            String json =
                    "{\"tag\":\"RequestHeader\",\"type\":\"Structure\",\"value\":[{\"tag\":\"ProtocolVersion\",\"type\":\"Structure\",\"value\":[{\"tag\":\"ProtocolVersionMajor\",\"type\":\"Integer\",\"value\":1},{\"tag\":\"ProtocolVersionMinor\",\"type\":\"Integer\",\"value\":4}]}]}";

            // When
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(new KmipJsonModule());
            SimpleRequestHeader header = jsonMapper.readValue(json, SimpleRequestHeader.class);

            // Then
            assertThat(header).isNotNull();
            assertThat(header.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.REQUEST_HEADER));
            assertThat(header.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(header.getProtocolVersion().getMajor()).isEqualTo(1);
            assertThat(header.getProtocolVersion().getMinor()).isEqualTo(4);
        }
    }
}
