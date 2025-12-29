package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;
import java.util.List;

@DisplayName("PrivateExponent Domain Tests")
class PrivateExponentTest extends AbstractKmipDataTypeSuite<PrivateExponent> {

    @Override
    protected Class<PrivateExponent> type() {
        return PrivateExponent.class;
    }

    @Override
    protected PrivateExponent createDefault() {
        return PrivateExponent.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}