package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.RevocationMessage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RevocationMessage JSON Serialization Tests")
class RevocationMessageJsonTest extends AbstractJsonSerializationTestSuite<RevocationMessage> {

    @Override
    public Class<RevocationMessage> type() {
        return RevocationMessage.class;
    }

    @Override
    public RevocationMessage createDefault() {
        return RevocationMessage.builder().value("test-revocation-message").build();
    }

    @Override
    public RevocationMessage createVariant() {
        return RevocationMessage.builder().value("another-revocation-message").build();
    }
}