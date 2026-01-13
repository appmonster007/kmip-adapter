package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RevocationMessage XML Serialization Tests")
class RevocationMessageXmlTest extends AbstractXmlSerializationTestSuite<RevocationMessage> {

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