package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InitialCounterValue TTLV Serialization Tests")
class InitialCounterValueTtlvTest extends AbstractTtlvSerializationTestSuite<InitialCounterValue> {

    @Override
    protected Class<InitialCounterValue> type() {
        return InitialCounterValue.class;
    }

    @Override
    protected InitialCounterValue createDefault() {
        return InitialCounterValue.of(1);
    }

    @Override
    protected InitialCounterValue createVariant() {
        return InitialCounterValue.of(2);
    }
}