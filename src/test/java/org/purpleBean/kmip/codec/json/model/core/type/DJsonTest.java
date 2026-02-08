package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("D JSON Serialization Tests")
class DJsonTest extends AbstractJsonSerializationTestSuite<D> {

    @Override
    public Class<D> type() {
        return D.class;
    }

    @Override
    public D createDefault() {
        return D.builder().value(BigInteger.ONE).build();
    }

    @Override
    public D createVariant() {
        return D.builder().value(BigInteger.TEN).build();
    }
}