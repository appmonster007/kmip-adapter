package org.purpleBean.kmip.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.util.List;

@DisplayName("SimpleRequestMessage Structure Semantics")
class SimpleRequestMessageTest extends AbstractKmipStructureSuite<SimpleRequestMessage> {

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
                // leave requestBatchItemErrors empty via @Singular
                .build();
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2; // header + at least one batch item
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // First should be header, subsequent are items
        assert values.get(0) instanceof SimpleRequestHeader;
        for (int i = 1; i < values.size(); i++) {
            assert values.get(i) instanceof SimpleRequestBatchItem;
        }
    }

    @Override
    protected boolean expectedSupportedForUnsupportedSpec() {
        return true; // current impl supports all specs
    }
}
