package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RandomIv;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RandomIv XML Serialization Tests")
class RandomIvXmlTest extends AbstractXmlSerializationTestSuite<RandomIv> {

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