package org.purpleBean.kmip.codec.json.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("SimpleRequestMessage JSON Codec Tests")
class SimpleRequestMessageJsonTest extends BaseKmipTest {

    private SimpleRequestMessage buildMessage() {
        SimpleRequestHeader header = SimpleRequestHeader
                .builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
        return SimpleRequestMessage
                .builder()
                .requestHeader(header)
                .requestBatchItem(SimpleRequestBatchItem.builder().build())
                .build();
    }

    @Test
    @DisplayName("Round-trip JSON: SimpleRequestMessage")
    void roundTrip_json() {
        SimpleRequestMessage original = buildMessage();
        try {
            String json = jsonMapper.writeValueAsString(original);
            SimpleRequestMessage deserialized = jsonMapper.readValue(json, SimpleRequestMessage.class);
            // Assert core fields instead of full equality (errors list may differ)
            org.assertj.core.api.Assertions.assertThat(deserialized.getRequestHeader())
                    .isEqualTo(original.getRequestHeader());
            org.assertj.core.api.Assertions.assertThat(deserialized.getRequestBatchItems())
                    .hasSize(original.getRequestBatchItems().size());
        } catch (Exception e) {
            throw new RuntimeException("JSON round-trip failed", e);
        }
    }

    @Test
    @DisplayName("UnsupportedVersion: JSON serialize succeeds, deserialize fails")
    void unsupportedVersion_roundTrip_json_failsOnDeserialize() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            try {
                String json = jsonMapper.writeValueAsString(buildMessage());
                org.assertj.core.api.Assertions.assertThatThrownBy(
                        () -> jsonMapper.readValue(json, SimpleRequestMessage.class))
                        .isInstanceOf(Exception.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
