package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("IssuerDistinguishedName Domain Tests")
class IssuerDistinguishedNameTest extends AbstractKmipDataTypeTestSuite<IssuerDistinguishedName> {

    @Override
    protected Class<IssuerDistinguishedName> type() {
        return IssuerDistinguishedName.class;
    }

    @Override
    protected IssuerDistinguishedName createDefault() {
        return IssuerDistinguishedName.of("test-issuer".getBytes());
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}
