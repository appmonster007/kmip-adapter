package org.purpleBean.kmip.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SimpleRequestBatchItem Structure Semantics")
class SimpleRequestBatchItemTest extends AbstractKmipStructureTestSuite<SimpleRequestBatchItem> {

    @Override
    protected Class<SimpleRequestBatchItem> type() {
        return SimpleRequestBatchItem.class;
    }

    @Override
    protected SimpleRequestBatchItem createDefault() {
        return SimpleRequestBatchItem.builder()
                .requestPayload(SimpleRequestPayload.of())
                .build();
    }

    @Override
    protected int expectedMinComponentCount() {
        return 0; // no inner components for now
    }

    @Override
    protected boolean expectedSupportedForUnsupportedSpec() {
        return true; // current impl supports all specs
    }
}
