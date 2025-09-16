package org.purpleBean.kmip.codec.ttlv.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("SimpleRequestBatchItem TTLV Serialization")
class SimpleRequestBatchItemTtlvTest extends AbstractTtlvSerializationSuite<SimpleRequestBatchItem> {

    @Override
    protected Class<SimpleRequestBatchItem> type() {
        return SimpleRequestBatchItem.class;
    }

    @Override
    protected SimpleRequestBatchItem createDefault() {
        return SimpleRequestBatchItem.builder().build();
    }

    @Override
    protected SimpleRequestBatchItem createVariant() {
        return SimpleRequestBatchItem.builder().build();
    }

    @Override
    protected boolean unsupportedSpecShouldFailSerialize() {
        return false; // model supports UnsupportedVersion
    }
}
