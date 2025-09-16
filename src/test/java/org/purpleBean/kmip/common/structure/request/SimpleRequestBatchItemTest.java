package org.purpleBean.kmip.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

@DisplayName("SimpleRequestBatchItem Structure Semantics")
class SimpleRequestBatchItemTest extends AbstractKmipStructureSuite<SimpleRequestBatchItem> {

    @Override
    protected Class<SimpleRequestBatchItem> type() {
        return SimpleRequestBatchItem.class;
    }

    @Override
    protected SimpleRequestBatchItem createDefault() {
        return SimpleRequestBatchItem.builder().build();
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
