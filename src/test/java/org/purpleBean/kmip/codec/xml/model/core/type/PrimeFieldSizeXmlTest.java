package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrimeFieldSize XML Serialization Tests")
class PrimeFieldSizeXmlTest extends AbstractXmlSerializationTestSuite<PrimeFieldSize> {

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