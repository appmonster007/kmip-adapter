package org.purpleBean.kmip.codec.json.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("SimpleRequestHeader JSON Codec Tests")
class SimpleRequestHeaderJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip JSON: SimpleRequestHeader")
    void roundTrip_json() {
        SimpleRequestHeader header = SimpleRequestHeader
                .builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, header, SimpleRequestHeader.class);
    }

    @Test
    @DisplayName("UnsupportedVersion: JSON round-trip should succeed (supports all specs)")
    void unsupportedVersion_roundTrip_json_failsOnDeserialize() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            SimpleRequestHeader header = SimpleRequestHeader
                    .builder()
                    .protocolVersion(ProtocolVersion.of(1, 2))
                    .build();
            try {
                String json = jsonMapper.writeValueAsString(header);
                org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> jsonMapper.readValue(json, SimpleRequestHeader.class))
                        .isInstanceOf(Exception.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
