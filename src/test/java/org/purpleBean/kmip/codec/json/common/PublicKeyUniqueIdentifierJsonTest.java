package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("PublicKeyUniqueIdentifier JSON Serialization Tests")
class PublicKeyUniqueIdentifierJsonTest extends AbstractJsonSerializationSuite<PublicKeyUniqueIdentifier> {

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