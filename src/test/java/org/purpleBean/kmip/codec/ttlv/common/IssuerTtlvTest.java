package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("Issuer TTLV Serialization Tests")
class IssuerTtlvTest extends AbstractTtlvSerializationSuite<Issuer> {

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