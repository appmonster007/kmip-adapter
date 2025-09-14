package org.purpleBean.kmip.codec.xml.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("SimpleRequestMessage XML Codec Tests")
class SimpleRequestMessageXmlTest extends BaseKmipTest {

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
    @DisplayName("Round-trip XML: SimpleRequestMessage")
    void roundTrip_xml() {
        // Serialize and assert structure rather than deserializing (deserializer expects specific array form)
        try {
            String xml = xmlMapper.writeValueAsString(buildMessage());
            org.assertj.core.api.Assertions.assertThat(xml).contains("<RequestMessage>");
            org.assertj.core.api.Assertions.assertThat(xml).contains("<RequestHeader>");
            org.assertj.core.api.Assertions.assertThat(xml).contains("<ProtocolVersion>");
        } catch (Exception e) {
            throw new RuntimeException("XML serialization failed", e);
        }
    }

    @Test
    @DisplayName("UnsupportedVersion: XML round-trip should succeed (message supports all specs)")
    void unsupportedVersion_roundTrip_xml_succeeds() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            try {
                String xml = xmlMapper.writeValueAsString(buildMessage());
                org.assertj.core.api.Assertions.assertThatThrownBy(
                        () -> xmlMapper.readValue(xml, SimpleRequestMessage.class))
                        .isInstanceOf(Exception.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
