package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousIndicator JSON Serialization")
class AsynchronousIndicatorJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousIndicator> {
    @Override
    public Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    public AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.Standard.MANDATORY.inst();
    }

    @Override
    public AsynchronousIndicator createVariant() {
        return AsynchronousIndicator.Standard.OPTIONAL.inst();
    }
}
