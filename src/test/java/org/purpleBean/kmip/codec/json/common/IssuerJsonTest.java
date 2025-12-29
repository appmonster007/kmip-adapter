package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("Issuer JSON Serialization Tests")
class IssuerJsonTest extends AbstractJsonSerializationSuite<Issuer> {

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