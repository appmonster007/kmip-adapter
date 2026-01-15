package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("IssuerAlternativeName JSON Serialization Tests")
class IssuerAlternativeNameJsonTest extends AbstractJsonSerializationTestSuite<IssuerAlternativeName> {

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