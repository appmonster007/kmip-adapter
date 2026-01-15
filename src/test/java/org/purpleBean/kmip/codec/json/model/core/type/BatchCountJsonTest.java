package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BatchCount JSON Serialization Tests")
class BatchCountJsonTest extends AbstractJsonSerializationTestSuite<BatchCount> {

    @Override
    protected Class<BatchCount> type() {
        return BatchCount.class;
    }

    @Override
    protected BatchCount createDefault() {
        return BatchCount.builder().value(5).build();
    }

    @Override
    protected BatchCount createVariant() {
        return BatchCount.builder().value(10).build();
    }
}