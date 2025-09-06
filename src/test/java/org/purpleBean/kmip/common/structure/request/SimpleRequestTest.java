package org.purpleBean.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SimpleRequest Tests")
class SimpleRequestTest {

    private SimpleRequestMessage requestMessage;
    private SimpleRequestHeader requestHeader;
    private SimpleRequestBatchItem batchItem1;
    private SimpleRequestBatchItem batchItem2;

    @BeforeEach
    void setUp() {
        // Set up test data
        ProtocolVersion protocolVersion = ProtocolVersion.of(1, 4);
        requestHeader = SimpleRequestHeader.builder()
                .protocolVersion(protocolVersion)
                .build();

        batchItem1 = SimpleRequestBatchItem.builder().build();
        batchItem2 = SimpleRequestBatchItem.builder().build();

        requestMessage = SimpleRequestMessage.builder()
                .requestHeader(requestHeader)
                .requestBatchItem(batchItem1)
                .requestBatchItem(batchItem2)
                .build();
    }

    @Nested
    @DisplayName("SimpleRequestHeader Tests")
    class SimpleRequestHeaderTests {
        @Test
        @DisplayName("Should have correct values")
        void shouldHaveCorrectValues() {
            // Verify the request header structure
            assertThat(requestHeader.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.REQUEST_HEADER));
            assertThat(requestHeader.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(requestHeader.getProtocolVersion().getMajor()).isEqualTo(1);
            assertThat(requestHeader.getProtocolVersion().getMinor()).isEqualTo(4);
            assertThat(requestHeader.getValues()).hasSize(1);
            assertThat(requestHeader.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("SimpleRequestBatchItem Tests")
    class SimpleRequestBatchItemTests {
        @Test
        @DisplayName("Should have correct values")
        void shouldHaveCorrectValues() {
            // Verify the batch item structure
            assertThat(batchItem1.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.BATCH_ITEM));
            assertThat(batchItem1.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(batchItem1.getValues()).isEmpty();
            assertThat(batchItem1.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("SimpleRequestMessage Tests")
    class SimpleRequestMessageTests {
        @Test
        @DisplayName("Should have correct structure")
        void shouldHaveCorrectStructure() {
            // Verify the message structure
            assertThat(requestMessage.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.REQUEST_MESSAGE));
            assertThat(requestMessage.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(requestMessage.getRequestHeader()).isEqualTo(requestHeader);
            assertThat(requestMessage.getRequestBatchItems()).containsExactly(batchItem1, batchItem2);
            assertThat(requestMessage.isSupportedFor(KmipSpec.V1_2)).isTrue();
            
            // Verify values include header and batch items
            List<KmipDataType> values = requestMessage.getValues();
            assertThat(values).hasSize(3);
            assertThat(values.get(0)).isEqualTo(requestHeader);
            assertThat(values.subList(1, 3)).containsExactly(batchItem1, batchItem2);
        }

        @Test
        @DisplayName("Should reject null request header")
        void shouldRejectNullRequestHeader() {
            assertThatThrownBy(() -> SimpleRequestMessage.builder()
                    .requestHeader(null)
                    .build())
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("requestHeader");
        }

        @Test
        @DisplayName("Should handle batch item errors")
        void shouldHandleBatchItemErrors() {
            // Given
            Exception error1 = new RuntimeException("Test error 1");
            Exception error2 = new RuntimeException("Test error 2");

            // When
            SimpleRequestMessage message = SimpleRequestMessage.builder()
                    .requestHeader(requestHeader)
                    .requestBatchItemError(error1)
                    .requestBatchItemError(error2)
                    .build();

            // Then
            assertThat(message.getRequestBatchItemErrors())
                    .hasSize(2)
                    .extracting(Throwable::getMessage)
                    .containsExactly("Test error 1", "Test error 2");
        }

        @Test
        @DisplayName("Should support all KMIP versions")
        void shouldSupportAllKmipVersions() {
            // Test with available versions
            assertThat(requestMessage.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("Serialization Tests")
    class SerializationTests {
        @Test
        @DisplayName("Should serialize to JSON")
        void shouldSerializeToJson() throws JsonProcessingException {
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(new KmipJsonModule());

            String json = jsonMapper.writeValueAsString(requestMessage);

            // Verify the JSON structure
            assertThat(json).contains("\"tag\":\"RequestMessage\"");
            assertThat(json).contains("\"type\":\"Structure\"");
            assertThat(json).contains("\"RequestHeader\"");
            assertThat(json).contains("\"BatchItem\"");
            assertThat(json).contains("\"BatchItem\"");
        }

        @Test
        @DisplayName("Should serialize to XML")
        void shouldSerializeToXml() throws JsonProcessingException {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new KmipXmlModule());

            String xml = xmlMapper.writeValueAsString(requestMessage);

            // Verify the XML structure
            assertThat(xml).contains("<SimpleRequestMessage>");
            assertThat(xml).contains("<RequestHeader>");
            assertThat(xml).contains("<ProtocolVersion>");
            assertThat(xml).contains("<ProtocolVersionMajor type=\"Integer\" value=\"1\"/>");
            assertThat(xml).contains("<ProtocolVersionMinor type=\"Integer\" value=\"4\"/>");
            assertThat(xml).contains("</ProtocolVersion>");
            assertThat(xml).contains("</RequestHeader>");
            assertThat(xml).contains("<BatchItem/>"); // Empty batch items
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {
        @Test
        @DisplayName("Should handle empty batch items")
        void shouldHandleEmptyBatchItems() {
            // When
            SimpleRequestMessage message = SimpleRequestMessage.builder()
                    .requestHeader(requestHeader)
                    .build();
            
            // Then
            assertThat(message.getRequestBatchItems()).isEmpty();
            assertThat(message.getValues()).hasSize(1); // Only header, no batch items
        }

        @Test
        @DisplayName("Should handle null batch items in builder")
        void shouldHandleNullBatchItemsInBuilder() {
            // When
            SimpleRequestMessage message = SimpleRequestMessage.builder()
                    .requestHeader(requestHeader)
                    .requestBatchItem(null)
                    .build();

            // Then - verify the builder includes the null batch item
            assertThat(message.getRequestBatchItems()).hasSize(1);
            assertThat(message.getRequestBatchItems().get(0)).isNull();
            assertThat(message.getValues()).hasSize(2); // Header + null batch item
        }

        @Test
        @DisplayName("Should handle empty batch items in builder")
        void shouldHandleEmptyBatchItemsInBuilder() {
            // When
            SimpleRequestMessage message = SimpleRequestMessage.builder()
                    .requestHeader(requestHeader)
                    .clearRequestBatchItems()
                    .build();

            // Then
            assertThat(message.getRequestBatchItems()).isEmpty();
            assertThat(message.getValues()).hasSize(1); // Only header
        }
    }
}
