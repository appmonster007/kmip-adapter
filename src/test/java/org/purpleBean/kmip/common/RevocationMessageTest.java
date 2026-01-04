package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("RevocationMessage Domain Tests")
class RevocationMessageTest extends AbstractKmipDataTypeSuite<RevocationMessage> {

    @Override
    protected Class<RevocationMessage> type() {
        return RevocationMessage.class;
    }

    @Override
    protected RevocationMessage createDefault() {
        return RevocationMessage.builder().value("test-revocation-message").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}