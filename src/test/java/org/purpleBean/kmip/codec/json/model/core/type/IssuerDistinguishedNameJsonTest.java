package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("IssuerDistinguishedName JSON Serialization Tests")
class IssuerDistinguishedNameJsonTest extends AbstractJsonSerializationTestSuite<IssuerDistinguishedName> {

    @Override
    protected Class<IssuerDistinguishedName> type() {
        return IssuerDistinguishedName.class;
    }

    @Override
    protected IssuerDistinguishedName createDefault() {
        return IssuerDistinguishedName.of("test-issuer".getBytes());
    }

    @Override
    protected IssuerDistinguishedName createVariant() {
        return IssuerDistinguishedName.of("test-issuer-variant".getBytes());
    }
}
