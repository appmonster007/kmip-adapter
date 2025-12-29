package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("IssuerAlternativeName JSON Serialization Tests")
class IssuerAlternativeNameJsonTest extends AbstractJsonSerializationSuite<IssuerAlternativeName> {

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