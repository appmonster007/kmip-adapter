package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("RevocationMessage JSON Serialization Tests")
class RevocationMessageJsonTest extends AbstractJsonSerializationSuite<RevocationMessage> {

    @Override
    protected Class<RevocationMessage> type() {
        return RevocationMessage.class;
    }

    @Override
    protected RevocationMessage createDefault() {
        return RevocationMessage.builder().value("test-revocation-message").build();
    }

    @Override
    protected RevocationMessage createVariant() {
        return RevocationMessage.builder().value("another-revocation-message").build();
    }
}