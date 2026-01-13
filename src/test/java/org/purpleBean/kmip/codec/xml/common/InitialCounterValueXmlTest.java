package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InitialCounterValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InitialCounterValue XML Serialization Tests")
class InitialCounterValueXmlTest extends AbstractXmlSerializationTestSuite<InitialCounterValue> {

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