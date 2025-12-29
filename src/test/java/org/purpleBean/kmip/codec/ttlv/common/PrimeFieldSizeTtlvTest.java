package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeFieldSize;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.math.BigInteger;

@DisplayName("PrimeFieldSize TTLV Serialization Tests")
class PrimeFieldSizeTtlvTest extends AbstractTtlvSerializationSuite<PrimeFieldSize> {

    @Override
    protected Class<PrimeFieldSize> type() {
        return PrimeFieldSize.class;
    }

    @Override
    protected PrimeFieldSize createDefault() {
        return PrimeFieldSize.builder().value(BigInteger.valueOf(2048)).build();
    }

    @Override
    protected PrimeFieldSize createVariant() {
        return PrimeFieldSize.builder().value(BigInteger.valueOf(3072)).build();
    }
}