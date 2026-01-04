package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;

@DisplayName("D Domain Tests")
class DTest extends AbstractKmipDataTypeSuite<D> {

    @Override
    protected Class<D> type() {
        return D.class;
    }

    @Override
    protected D createDefault() {
        return D.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}