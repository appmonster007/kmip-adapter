package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InitialCounterValue JSON Serialization Tests")
class InitialCounterValueJsonTest extends AbstractJsonSerializationTestSuite<InitialCounterValue> {

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