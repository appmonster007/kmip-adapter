package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.structure.RevocationReason;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RevocationReason Json Serialization Tests")
class RevocationReasonJsonTest extends AbstractJsonSerializationTestSuite<RevocationReason> {

    @Override
    protected Class<RevocationReason> type() {
        return RevocationReason.class;
    }

    @Override
    protected RevocationReason createDefault() {
        return RevocationReason.builder()
                .revocationReasonCode(new RevocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE))
                .revocationMessage(RevocationMessage.of("test-message"))
                .build();
    }
}
