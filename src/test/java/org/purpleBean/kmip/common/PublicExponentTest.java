package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;

@DisplayName("PublicExponent Domain Tests")
class PublicExponentTest extends AbstractKmipDataTypeSuite<PublicExponent> {

    @Override
    protected Class<PublicExponent> type() {
        return PublicExponent.class;
    }

    @Override
    protected PublicExponent createDefault() {
        return PublicExponent.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}