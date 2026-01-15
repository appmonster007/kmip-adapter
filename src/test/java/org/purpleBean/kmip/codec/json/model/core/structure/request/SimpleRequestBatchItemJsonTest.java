package org.purpleBean.kmip.codec.json.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SimpleRequestBatchItem JSON Serialization")
class SimpleRequestBatchItemJsonTest extends AbstractJsonSerializationTestSuite<SimpleRequestBatchItem> {

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
