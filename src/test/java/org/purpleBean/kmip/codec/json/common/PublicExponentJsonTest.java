package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;

@DisplayName("PublicExponent JSON Serialization Tests")
class PublicExponentJsonTest extends AbstractJsonSerializationSuite<PublicExponent> {

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