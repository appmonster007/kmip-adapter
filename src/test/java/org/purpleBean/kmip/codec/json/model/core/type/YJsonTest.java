package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Y;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Y JSON Serialization Tests")
class YJsonTest extends AbstractJsonSerializationTestSuite<Y> {

    @Override
    public Class<Y> type() {
        return Y.class;
    }

    @Override
    public Y createDefault() {
        return Y.builder().value(BigInteger.ONE).build();
    }

    @Override
    public Y createVariant() {
        return Y.builder().value(BigInteger.TEN).build();
    }
}