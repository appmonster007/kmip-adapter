package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;

@DisplayName("G Domain Tests")
class GTest extends AbstractKmipDataTypeSuite<G> {

    @Override
    protected Class<G> type() {
        return G.class;
    }

    @Override
    protected G createDefault() {
        return G.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}