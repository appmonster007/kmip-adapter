package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("G JSON Serialization Tests")
class GJsonTest extends AbstractJsonSerializationTestSuite<G> {

    @Override
    public Class<G> type() {
        return G.class;
    }

    @Override
    public G createDefault() {
        return G.builder().value(BigInteger.ONE).build();
    }

    @Override
    public G createVariant() {
        return G.builder().value(BigInteger.TEN).build();
    }
}