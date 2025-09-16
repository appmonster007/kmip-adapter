package org.purpleBean.kmip.codec.json.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.util.Objects;

@DisplayName("SimpleRequestMessage JSON Serialization")
class SimpleRequestMessageJsonTest extends AbstractJsonSerializationSuite<SimpleRequestMessage> {

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

    @Override
    protected boolean equalsRelaxed(SimpleRequestMessage a, SimpleRequestMessage b) {
        if (a == null || b == null) return a == b;
        return Objects.equals(a.getRequestHeader(), b.getRequestHeader())
                && Objects.equals(a.getRequestBatchItems(), b.getRequestBatchItems());
    }
}
