package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("IssuerAlternativeName XML Serialization Tests")
class IssuerAlternativeNameXmlTest extends AbstractXmlSerializationSuite<IssuerAlternativeName> {

    @Override
    protected Class<IssuerAlternativeName> type() {
        return IssuerAlternativeName.class;
    }

    @Override
    protected IssuerAlternativeName createDefault() {
        return IssuerAlternativeName.of("test-issuer-alt-name".getBytes());
    }

    @Override
    protected IssuerAlternativeName createVariant() {
        return IssuerAlternativeName.of("another-issuer-alt-name".getBytes());
    }
}