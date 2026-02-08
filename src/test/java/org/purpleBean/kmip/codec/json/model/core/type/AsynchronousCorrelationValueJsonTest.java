package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousCorrelationValue JSON Serialization Tests")
class AsynchronousCorrelationValueJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousCorrelationValue> {

    @Override
    public Class<AsynchronousCorrelationValue> type() {
        return AsynchronousCorrelationValue.class;
    }

    @Override
    public AsynchronousCorrelationValue createDefault() {
        return AsynchronousCorrelationValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public AsynchronousCorrelationValue createVariant() {
        return AsynchronousCorrelationValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}