package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousIndicator JSON Serialization Tests")
class AsynchronousIndicatorJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousIndicator> {

    @Override
    protected Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    protected AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.builder().value(true).build();
    }

    @Override
    protected AsynchronousIndicator createVariant() {
        return AsynchronousIndicator.builder().value(false).build();
    }
}