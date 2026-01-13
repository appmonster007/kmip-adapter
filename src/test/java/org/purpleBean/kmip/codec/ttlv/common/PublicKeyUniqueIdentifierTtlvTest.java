package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PublicKeyUniqueIdentifier TTLV Serialization Tests")
class PublicKeyUniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<PublicKeyUniqueIdentifier> {

    @Override
    protected Class<PublicKeyUniqueIdentifier> type() {
        return PublicKeyUniqueIdentifier.class;
    }

    @Override
    protected PublicKeyUniqueIdentifier createDefault() {
        return PublicKeyUniqueIdentifier.builder().value("test-key-id").build();
    }

    @Override
    protected PublicKeyUniqueIdentifier createVariant() {
        return PublicKeyUniqueIdentifier.builder().value("another-key-id").build();
    }
}