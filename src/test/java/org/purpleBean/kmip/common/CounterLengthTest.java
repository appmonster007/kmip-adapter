package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("CounterLength Domain Tests")
class CounterLengthTest extends AbstractKmipDataTypeSuite<CounterLength> {

    @Override
    protected Class<CounterLength> type() {
        return CounterLength.class;
    }

    @Override
    protected CounterLength createDefault() {
        return CounterLength.of(128);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}