package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.X;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("X TTLV Serialization Tests")
class XTtlvTest extends AbstractTtlvSerializationTestSuite<X> {

    @Override
    public Class<X> type() {
        return X.class;
    }

    @Override
    public X createDefault() {
        return X.builder().value(BigInteger.ONE).build();
    }

    @Override
    public X createVariant() {
        return X.builder().value(BigInteger.TEN).build();
    }
}