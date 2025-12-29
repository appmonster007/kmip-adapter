package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.math.BigInteger;

@DisplayName("PublicExponent TTLV Serialization Tests")
class PublicExponentTtlvTest extends AbstractTtlvSerializationSuite<PublicExponent> {

    @Override
    protected Class<PublicExponent> type() {
        return PublicExponent.class;
    }

    @Override
    protected PublicExponent createDefault() {
        return PublicExponent.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected PublicExponent createVariant() {
        return PublicExponent.builder().value(BigInteger.valueOf(3)).build();
    }
}