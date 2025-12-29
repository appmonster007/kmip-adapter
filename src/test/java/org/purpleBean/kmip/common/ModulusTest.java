package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;
import java.util.List;

@DisplayName("Modulus Domain Tests")
class ModulusTest extends AbstractKmipDataTypeSuite<Modulus> {

    @Override
    protected Class<Modulus> type() {
        return Modulus.class;
    }

    @Override
    protected Modulus createDefault() {
        return Modulus.builder().value(BigInteger.TEN).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}