package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrivateKeyUniqueIdentifier TTLV Serialization Tests")
class PrivateKeyUniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<PrivateKeyUniqueIdentifier> {

    @Override
    protected Class<PrivateKeyUniqueIdentifier> type() {
        return PrivateKeyUniqueIdentifier.class;
    }

    @Override
    protected PrivateKeyUniqueIdentifier createDefault() {
        return PrivateKeyUniqueIdentifier.builder().value("test-private-key-id").build();
    }

    @Override
    protected PrivateKeyUniqueIdentifier createVariant() {
        return PrivateKeyUniqueIdentifier.builder().value("another-private-key-id").build();
    }
}