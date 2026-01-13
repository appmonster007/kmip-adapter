package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RandomIv;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RandomIv JSON Serialization Tests")
class RandomIvJsonTest extends AbstractJsonSerializationTestSuite<RandomIv> {

    @Override
    protected Class<RandomIv> type() {
        return RandomIv.class;
    }

    @Override
    protected RandomIv createDefault() {
        return RandomIv.of(true);
    }

    @Override
    protected RandomIv createVariant() {
        return RandomIv.of(false);
    }
}