package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyUniqueIdentifier JSON Serialization Tests")
class PublicKeyUniqueIdentifierJsonTest extends AbstractJsonSerializationTestSuite<PublicKeyUniqueIdentifier> {

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