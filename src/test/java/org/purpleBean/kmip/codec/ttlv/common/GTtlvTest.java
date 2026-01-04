package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.math.BigInteger;

@DisplayName("G TTLV Serialization Tests")
class GTtlvTest extends AbstractTtlvSerializationSuite<G> {

    @Override
    protected Class<G> type() {
        return G.class;
    }

    @Override
    protected G createDefault() {
        return G.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected G createVariant() {
        return G.builder().value(BigInteger.TEN).build();
    }
}