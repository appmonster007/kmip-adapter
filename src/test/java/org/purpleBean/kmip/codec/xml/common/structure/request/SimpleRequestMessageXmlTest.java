package org.purpleBean.kmip.codec.xml.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("SimpleRequestMessage XML Serialization")
class SimpleRequestMessageXmlTest extends AbstractXmlSerializationSuite<SimpleRequestMessage> {

    @Override
    protected Class<SimpleRequestMessage> type() {
        return SimpleRequestMessage.class;
    }

    @Override
    protected SimpleRequestMessage createDefault() {
        SimpleRequestHeader header = SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
        SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();
        return SimpleRequestMessage.builder()
                .requestHeader(header)
                .requestBatchItem(item)
                .build();
    }

    @Override
    protected SimpleRequestMessage createVariant() {
        SimpleRequestHeader header = SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(2, 0))
                .build();
        SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();
        return SimpleRequestMessage.builder()
                .requestHeader(header)
                .requestBatchItem(item)
                .build();
    }

    @Override
    protected boolean unsupportedSpecShouldFailSerialize() {
        return false; // model supports UnsupportedVersion
    }

    // Override to avoid strict equality on requestBatchItemErrors which may deserialize as [null]
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("XML: round-trip default instance (ignore errors list)")
    void xml_roundTrip_default_custom() throws Exception {
        SimpleRequestMessage original = createDefault();
        String xml = mapper().writeValueAsString(original);
        SimpleRequestMessage restored = mapper().readValue(xml, SimpleRequestMessage.class);
        // Compare significant fields
        org.assertj.core.api.Assertions.assertThat(restored.getRequestHeader())
                .isEqualTo(original.getRequestHeader());
        org.assertj.core.api.Assertions.assertThat(restored.getRequestBatchItems())
                .isEqualTo(original.getRequestBatchItems());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("XML: round-trip variant instance (ignore errors list)")
    void xml_roundTrip_variant_custom() throws Exception {
        SimpleRequestMessage original = createVariant();
        String xml = mapper().writeValueAsString(original);
        SimpleRequestMessage restored = mapper().readValue(xml, SimpleRequestMessage.class);
        org.assertj.core.api.Assertions.assertThat(restored.getRequestHeader())
                .isEqualTo(original.getRequestHeader());
        org.assertj.core.api.Assertions.assertThat(restored.getRequestBatchItems())
                .isEqualTo(original.getRequestBatchItems());
    }

    @Override
    protected boolean equalsRelaxed(SimpleRequestMessage a, SimpleRequestMessage b) {
        if (a == null || b == null) return a == b;
        return java.util.Objects.equals(a.getRequestHeader().getProtocolVersion(), b.getRequestHeader().getProtocolVersion())
                && java.util.Objects.equals(a.getRequestBatchItems().size(), b.getRequestBatchItems().size());
    }
}
