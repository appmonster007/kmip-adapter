package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.BatchOrderOption;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BatchOrderOption JSON Serialization Tests")
class BatchOrderOptionJsonTest extends AbstractJsonSerializationTestSuite<BatchOrderOption> {

    @Override
    protected Class<BatchOrderOption> type() {
        return BatchOrderOption.class;
    }

    @Override
    protected BatchOrderOption createDefault() {
        return BatchOrderOption.builder().value(true).build();
    }

    @Override
    protected BatchOrderOption createVariant() {
        return BatchOrderOption.builder().value(false).build();
    }
}