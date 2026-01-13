package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Issuer TTLV Serialization Tests")
class IssuerTtlvTest extends AbstractTtlvSerializationTestSuite<Issuer> {

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