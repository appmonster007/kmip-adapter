package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;

@DisplayName("Y JSON Serialization Tests")
class YJsonTest extends AbstractJsonSerializationSuite<Y> {

    @Override
    protected Class<Y> type() {
        return Y.class;
    }

    @Override
    protected Y createDefault() {
        return Y.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected Y createVariant() {
        return Y.builder().value(BigInteger.TEN).build();
    }
}