package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("InitialCounterValue Domain Tests")
class InitialCounterValueTest extends AbstractKmipDataTypeTestSuite<InitialCounterValue> {

    @Override
    protected Class<InitialCounterValue> type() {
        return InitialCounterValue.class;
    }

    @Override
    protected InitialCounterValue createDefault() {
        return InitialCounterValue.of(1);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}