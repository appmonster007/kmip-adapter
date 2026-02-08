package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrivateKeyUniqueIdentifier TTLV Serialization Tests")
class PrivateKeyUniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<PrivateKeyUniqueIdentifier> {

    @Override
    public Class<PrivateKeyUniqueIdentifier> type() {
        return PrivateKeyUniqueIdentifier.class;
    }

    @Override
    public PrivateKeyUniqueIdentifier createDefault() {
        return PrivateKeyUniqueIdentifier.builder().value("test-private-key-id").build();
    }

    @Override
    public PrivateKeyUniqueIdentifier createVariant() {
        return PrivateKeyUniqueIdentifier.builder().value("another-private-key-id").build();
    }
}