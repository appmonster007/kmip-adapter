package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Modulus TTLV Serialization Tests")
class ModulusTtlvTest extends AbstractTtlvSerializationTestSuite<Modulus> {

    @Override
    public Class<Modulus> type() {
        return Modulus.class;
    }

    @Override
    public Modulus createDefault() {
        return Modulus.builder().value(BigInteger.TEN).build();
    }

    @Override
    public Modulus createVariant() {
        return Modulus.builder().value(BigInteger.valueOf(20)).build();
    }
}