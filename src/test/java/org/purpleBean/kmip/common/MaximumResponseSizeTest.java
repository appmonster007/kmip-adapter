package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("MaximumResponseSize Domain Tests")
class MaximumResponseSizeTest extends AbstractKmipDataTypeSuite<MaximumResponseSize> {

    @Override
    protected Class<MaximumResponseSize> type() {
        return MaximumResponseSize.class;
    }

    @Override
    protected MaximumResponseSize createDefault() {
        return MaximumResponseSize.builder().value(1024).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}