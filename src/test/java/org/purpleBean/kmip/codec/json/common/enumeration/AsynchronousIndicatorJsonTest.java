package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousIndicator JSON Serialization")
class AsynchronousIndicatorJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousIndicator> {
    @Override
    protected Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    protected AsynchronousIndicator createDefault() {
        return new AsynchronousIndicator(AsynchronousIndicator.Standard.MANDATORY);
    }

    @Override
    protected AsynchronousIndicator createVariant() {
        return new AsynchronousIndicator(AsynchronousIndicator.Standard.OPTIONAL);
    }
}
