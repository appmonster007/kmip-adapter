package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AsynchronousIndicator Domain Tests")
class AsynchronousIndicatorTest extends AbstractKmipDataTypeSuite<AsynchronousIndicator> {

    @Override
    protected Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    protected AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.builder().value(true).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}