package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InitialCounterValue TTLV Serialization Tests")
class InitialCounterValueTtlvTest extends AbstractTtlvSerializationTestSuite<InitialCounterValue> {

    @Override
    public Class<InitialCounterValue> type() {
        return InitialCounterValue.class;
    }

    @Override
    public InitialCounterValue createDefault() {
        return InitialCounterValue.of(1);
    }

    @Override
    public InitialCounterValue createVariant() {
        return InitialCounterValue.of(2);
    }
}