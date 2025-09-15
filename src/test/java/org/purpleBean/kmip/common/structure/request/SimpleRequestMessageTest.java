package org.purpleBean.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SimpleRequestMessage Tests")
class SimpleRequestMessageTest extends BaseKmipTest {

    private SimpleRequestMessage requestMessage;
    private SimpleRequestHeader requestHeader;
    private SimpleRequestBatchItem batchItem1;
    private SimpleRequestBatchItem batchItem2;

    @BeforeEach
    void setUp() {
        // Set up test data
        ProtocolVersion protocolVersion = ProtocolVersion.of(1, 4);
        requestHeader = SimpleRequestHeader.builder().protocolVersion(protocolVersion).build();

        batchItem1 = SimpleRequestBatchItem.builder().build();
        batchItem2 = SimpleRequestBatchItem.builder().build();

        requestMessage =
                SimpleRequestMessage.builder()
                        .requestHeader(requestHeader)
                        .requestBatchItem(batchItem1)
                        .requestBatchItem(batchItem2)
                        .build();
    }

    // Header and BatchItem have dedicated tests; keep message-focused tests here

    @Nested
    @DisplayName("SimpleRequestMessage Tests")
    class SimpleRequestMessageTests {
        @Test
        @DisplayName("Should have correct structure")
        void shouldHaveCorrectStructure() {
            // Verify the message structure
            assertThat(requestMessage.getKmipTag())
                    .isEqualTo(new KmipTag(KmipTag.Standard.REQUEST_MESSAGE));
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
            assertThatThrownBy(() -> SimpleRequestMessage.builder().requestHeader(null).build())
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
            SimpleRequestMessage message =
                    SimpleRequestMessage.builder()
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

        @ParameterizedTest
        @CsvSource({"1,0,0", "1,2,1", "1,4,2", "2,0,3"})
        @DisplayName("Should build message with various protocol versions and batch item counts")
        void shouldBuildMessageWithVariousInputs(int major, int minor, int batchCount) {
            // Given
            SimpleRequestHeader header =
                    SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(major, minor)).build();

            SimpleRequestMessage.SimpleRequestMessageBuilder builder =
                    SimpleRequestMessage.builder().requestHeader(header);
            for (int i = 0; i < batchCount; i++) {
                builder.requestBatchItem(SimpleRequestBatchItem.builder().build());
            }

            // When
            SimpleRequestMessage msg = builder.build();

            // Then
            assertThat(msg.getRequestHeader().getProtocolVersion().getMajor()).isEqualTo(major);
            assertThat(msg.getRequestHeader().getProtocolVersion().getMinor()).isEqualTo(minor);
            assertThat(msg.getRequestBatchItems()).hasSize(batchCount);
            // Values should include header + batch items
            assertThat(msg.getValues()).hasSize(1 + batchCount);
        }

        @ParameterizedTest
        @CsvSource({"0", "1", "2"})
        @DisplayName("Should accumulate batch item errors of various counts")
        void shouldAccumulateBatchItemErrors(int errorCount) {
            // Given
            SimpleRequestMessage.SimpleRequestMessageBuilder builder =
                    SimpleRequestMessage.builder().requestHeader(requestHeader);
            for (int i = 0; i < errorCount; i++) {
                builder.requestBatchItemError(new RuntimeException("Err-" + i));
            }

            // When
            SimpleRequestMessage msg = builder.build();

            // Then
            assertThat(msg.getRequestBatchItemErrors()).hasSize(errorCount);
            for (int i = 0; i < errorCount; i++) {
                assertThat(msg.getRequestBatchItemErrors().get(i).getMessage()).isEqualTo("Err-" + i);
            }
        }

        @Test
        @DisplayName("UnsupportedVersion context: JSON serialization should succeed (message supports all specs)")
        void unsupportedVersion_jsonSerializationShouldSucceed() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> {
                        ObjectMapper jsonMapper = new ObjectMapper();
                        jsonMapper.registerModule(new KmipJsonModule());
                        try {
                            String json = jsonMapper.writeValueAsString(requestMessage);
                            assertThat(json).contains("\"RequestMessage\"");
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        @Test
        @DisplayName("UnsupportedVersion context: XML serialization should succeed (message supports all specs)")
        void unsupportedVersion_xmlSerializationShouldSucceed() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> {
                        XmlMapper xmlMapper = new XmlMapper();
                        xmlMapper.registerModule(new KmipXmlModule());
                        try {
                            String xml = xmlMapper.writeValueAsString(requestMessage);
                            assertThat(xml).contains("<RequestMessage>");
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    });
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
            assertThat(xml).contains("<RequestMessage>");
            assertThat(xml).contains("<RequestHeader>");
            assertThat(xml).contains("<ProtocolVersion>");
            assertThat(xml).contains("<ProtocolVersionMajor type=\"Integer\" value=\"1\"/>");
            assertThat(xml).contains("<ProtocolVersionMinor type=\"Integer\" value=\"4\"/>");
            assertThat(xml).contains("</ProtocolVersion>");
            assertThat(xml).contains("</RequestHeader>");
            assertThat(xml).contains("<BatchItem/>"); // Empty batch items
        }

        @ParameterizedTest
        @CsvSource({"0", "1", "2", "3"})
        @DisplayName("Should serialize to JSON with varying batch item counts")
        void shouldSerializeJson_withVaryingBatchItemCounts(int batchCount) throws JsonProcessingException {
            // Given
            SimpleRequestMessage.SimpleRequestMessageBuilder builder =
                    SimpleRequestMessage.builder().requestHeader(requestHeader);
            for (int i = 0; i < batchCount; i++) {
                builder.requestBatchItem(SimpleRequestBatchItem.builder().build());
            }
            SimpleRequestMessage msg = builder.build();

            // When
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(new KmipJsonModule());
            String json = jsonMapper.writeValueAsString(msg);

            // Then
            assertThat(json).contains("\"RequestMessage\"");
            if (batchCount == 0) {
                // No batch items
                assertThat(json).contains("\"RequestHeader\"");
            } else {
                assertThat(json.split("\\\"BatchItem\\\"")).hasSizeGreaterThan(1);
            }
        }

        @ParameterizedTest
        @CsvSource({"0", "1", "2", "3"})
        @DisplayName("Should serialize to XML with varying batch item counts")
        void shouldSerializeXml_withVaryingBatchItemCounts(int batchCount) throws JsonProcessingException {
            // Given
            SimpleRequestMessage.SimpleRequestMessageBuilder builder =
                    SimpleRequestMessage.builder().requestHeader(requestHeader);
            for (int i = 0; i < batchCount; i++) {
                builder.requestBatchItem(SimpleRequestBatchItem.builder().build());
            }
            SimpleRequestMessage msg = builder.build();

            // When
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new KmipXmlModule());
            String xml = xmlMapper.writeValueAsString(msg);

            // Then
            assertThat(xml).contains("<RequestMessage>");
            if (batchCount == 0) {
                assertThat(xml).contains("<RequestHeader>");
            } else {
                assertThat(xml).contains("<BatchItem/");
            }
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {
        @Test
        @DisplayName("Should handle empty batch items")
        void shouldHandleEmptyBatchItems() {
            // When
            SimpleRequestMessage message =
                    SimpleRequestMessage.builder().requestHeader(requestHeader).build();

            // Then
            assertThat(message.getRequestBatchItems()).isEmpty();
            assertThat(message.getValues()).hasSize(1); // Only header, no batch items
        }

        @Test
        @DisplayName("Should handle null batch items in builder")
        void shouldHandleNullBatchItemsInBuilder() {
            // When
            SimpleRequestMessage message =
                    SimpleRequestMessage.builder()
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
            SimpleRequestMessage message =
                    SimpleRequestMessage.builder()
                            .requestHeader(requestHeader)
                            .clearRequestBatchItems()
                            .build();

            // Then
            assertThat(message.getRequestBatchItems()).isEmpty();
            assertThat(message.getValues()).hasSize(1); // Only header
        }
    }
}
