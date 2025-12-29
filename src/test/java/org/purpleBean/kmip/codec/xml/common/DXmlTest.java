package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("D XML Serialization Tests")
class DXmlTest extends AbstractXmlSerializationSuite<D> {

    @Override
    protected Class<D> type() {
        return D.class;
    }

    @Override
    protected D createDefault() {
        return D.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected D createVariant() {
        return D.builder().value(BigInteger.TEN).build();
    }
}