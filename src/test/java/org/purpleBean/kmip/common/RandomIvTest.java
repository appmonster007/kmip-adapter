package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("RandomIv Domain Tests")
class RandomIvTest extends AbstractKmipDataTypeTestSuite<RandomIv> {

    @Override
    protected Class<RandomIv> type() {
        return RandomIv.class;
    }

    @Override
    protected RandomIv createDefault() {
        return RandomIv.of(true);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}