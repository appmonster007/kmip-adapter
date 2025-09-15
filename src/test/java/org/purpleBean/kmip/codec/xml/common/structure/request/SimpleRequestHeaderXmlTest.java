package org.purpleBean.kmip.codec.xml.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("SimpleRequestHeader XML Codec Tests")
class SimpleRequestHeaderXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip XML: SimpleRequestHeader")
    void roundTrip_xml() {
        SimpleRequestHeader header = SimpleRequestHeader
                .builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, header, SimpleRequestHeader.class);
    }

    @Test
    @DisplayName("UnsupportedVersion: XML round-trip should succeed (header supports all specs)")
    void unsupportedVersion_xml_deserializeFails() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            SimpleRequestHeader header = SimpleRequestHeader
                    .builder()
                    .protocolVersion(ProtocolVersion.of(1, 2))
                    .build();
            try {
                String xml = xmlMapper.writeValueAsString(header);
                org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> xmlMapper.readValue(xml, SimpleRequestHeader.class))
                        .isInstanceOf(Exception.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
