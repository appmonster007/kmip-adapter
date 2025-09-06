package org.purpleBean.kmip.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.KmipJsonModule;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SimpleRequestBatchItem Tests")
class SimpleRequestBatchItemTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create SimpleRequestBatchItem with default values")
        void shouldCreateWithDefaultValues() {
            // When
            SimpleRequestBatchItem batchItem = SimpleRequestBatchItem.builder().build();

            // Then
            assertThat(batchItem.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.BATCH_ITEM));
            assertThat(batchItem.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(batchItem.getValues()).isEmpty();
        }
    }

    @Nested
    @DisplayName("KMIP Specification Support")
    class KmipSpecSupport {

        @Test
        @DisplayName("Should support all KMIP versions")
        void shouldSupportAllKmipVersions() {
            // Given
            SimpleRequestBatchItem batchItem = SimpleRequestBatchItem.builder().build();

            // Then
            // Test with available versions
            assertThat(batchItem.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("Equals and HashCode")
    class EqualsAndHashCode {

        @Test
        @DisplayName("Should be equal when objects are identical")
        void shouldBeEqualWhenIdentical() {
            // Given
            SimpleRequestBatchItem item1 = SimpleRequestBatchItem.builder().build();
            SimpleRequestBatchItem item2 = SimpleRequestBatchItem.builder().build();

            // Then
            assertThat(item1).isEqualTo(item2);
            assertThat(item1).hasSameHashCodeAs(item2);
        }

        @Test
        @DisplayName("Should not be equal to null")
        void shouldNotBeEqualToNull() {
            // Given
            SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();

            // Then
            assertThat(item).isNotEqualTo(null);
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void shouldNotBeEqualToDifferentType() {
            // Given
            SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();

            // Then
            assertThat(item).isNotEqualTo("not a batch item");
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize to JSON")
        void shouldSerializeToJson() throws JsonProcessingException {
            // Given
            SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();

            // When
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(new KmipJsonModule());
            String json = jsonMapper.writeValueAsString(item);

            // Then
            assertThat(json).contains("\"tag\":\"BatchItem\"");
            assertThat(json).contains("\"type\":\"Structure\"");
        }

        @Test
        @DisplayName("Should deserialize from JSON")
        void shouldDeserializeFromJson() throws JsonMappingException, JsonProcessingException {
            // Given
            String json = "{\"tag\":\"BatchItem\",\"type\":\"Structure\",\"value\":[]}";

            // When
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(new KmipJsonModule());
            SimpleRequestBatchItem item = jsonMapper.readValue(json, SimpleRequestBatchItem.class);

            // Then
            assertThat(item).isNotNull();
            assertThat(item.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.BATCH_ITEM));
            assertThat(item.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
        }
    }
}
