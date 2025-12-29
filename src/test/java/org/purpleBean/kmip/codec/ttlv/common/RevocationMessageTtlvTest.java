package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("RevocationMessage TTLV Serialization Tests")
class RevocationMessageTtlvTest extends AbstractTtlvSerializationSuite<RevocationMessage> {

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