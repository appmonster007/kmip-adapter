package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;

@DisplayName("G JSON Serialization Tests")
class GJsonTest extends AbstractJsonSerializationSuite<G> {

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