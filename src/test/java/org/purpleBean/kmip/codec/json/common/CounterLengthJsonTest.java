package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CounterLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CounterLength JSON Serialization Tests")
class CounterLengthJsonTest extends AbstractJsonSerializationTestSuite<CounterLength> {

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