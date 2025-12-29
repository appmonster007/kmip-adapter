package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeExponentQ;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.math.BigInteger;

@DisplayName("PrimeExponentQ TTLV Serialization Tests")
class PrimeExponentQTtlvTest extends AbstractTtlvSerializationSuite<PrimeExponentQ> {

    @Override
    protected Class<PrimeExponentQ> type() {
        return PrimeExponentQ.class;
    }

    @Override
    protected PrimeExponentQ createDefault() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected PrimeExponentQ createVariant() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(3)).build();
    }
}