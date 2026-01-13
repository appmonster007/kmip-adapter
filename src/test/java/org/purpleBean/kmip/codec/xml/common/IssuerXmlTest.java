package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Issuer XML Serialization Tests")
class IssuerXmlTest extends AbstractXmlSerializationTestSuite<Issuer> {

    @Override
    protected Class<Issuer> type() {
        return Issuer.class;
    }

    @Override
    protected Issuer createDefault() {
        return Issuer.builder().value("test-issuer").build();
    }

    @Override
    protected Issuer createVariant() {
        return Issuer.builder().value("another-issuer").build();
    }
}