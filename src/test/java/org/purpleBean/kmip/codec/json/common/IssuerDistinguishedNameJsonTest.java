package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("IssuerDistinguishedName JSON Serialization Tests")
class IssuerDistinguishedNameJsonTest extends AbstractJsonSerializationSuite<IssuerDistinguishedName> {

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
