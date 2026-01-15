package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.structure.RevocationReason;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RevocationReason Ttlv Serialization Tests")
class RevocationReasonTtlvTest extends AbstractTtlvSerializationTestSuite<RevocationReason> {

    @Override
    protected Class<RevocationReason> type() {
        return RevocationReason.class;
    }

    @Override
    protected RevocationReason createDefault() {
        return RevocationReason.builder()
                .revocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE.inst())
                .revocationMessage(RevocationMessage.of("test-message"))
                .build();
    }
}
