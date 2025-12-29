package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CRTCoefficient;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.math.BigInteger;

@DisplayName("CRTCoefficient TTLV Serialization Tests")
class CRTCoefficientTtlvTest extends AbstractTtlvSerializationSuite<CRTCoefficient> {

    @Override
    protected Class<CRTCoefficient> type() {
        return CRTCoefficient.class;
    }

    @Override
    protected CRTCoefficient createDefault() {
        return CRTCoefficient.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    protected CRTCoefficient createVariant() {
        return CRTCoefficient.builder().value(BigInteger.valueOf(54321)).build();
    }
}