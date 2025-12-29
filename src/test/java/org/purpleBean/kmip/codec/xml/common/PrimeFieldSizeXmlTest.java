package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeFieldSize;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("PrimeFieldSize XML Serialization Tests")
class PrimeFieldSizeXmlTest extends AbstractXmlSerializationSuite<PrimeFieldSize> {

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