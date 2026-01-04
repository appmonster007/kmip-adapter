package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.math.BigInteger;

@DisplayName("Y TTLV Serialization Tests")
class YTtlvTest extends AbstractTtlvSerializationSuite<Y> {

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