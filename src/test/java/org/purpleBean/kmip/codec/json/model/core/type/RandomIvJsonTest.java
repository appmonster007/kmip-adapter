package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.RandomIv;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RandomIv JSON Serialization Tests")
class RandomIvJsonTest extends AbstractJsonSerializationTestSuite<RandomIv> {

    @Override
    public Class<RandomIv> type() {
        return RandomIv.class;
    }

    @Override
    public RandomIv createDefault() {
        return RandomIv.of(true);
    }

    @Override
    public RandomIv createVariant() {
        return RandomIv.of(false);
    }
}