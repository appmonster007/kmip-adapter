package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CounterLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CounterLength XML Serialization Tests")
class CounterLengthXmlTest extends AbstractXmlSerializationSuite<CounterLength> {

    @Override
    protected Class<CounterLength> type() {
        return CounterLength.class;
    }

    @Override
    protected CounterLength createDefault() {
        return CounterLength.of(128);
    }

    @Override
    protected CounterLength createVariant() {
        return CounterLength.of(256);
    }
}