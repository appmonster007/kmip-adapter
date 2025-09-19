package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("RevocationReasonCode TTLV Serialization")
class RevocationReasonCodeTtlvTest extends AbstractTtlvSerializationSuite<RevocationReasonCode> {
    @Override
    protected Class<RevocationReasonCode> type() {
        return RevocationReasonCode.class;
    }

    @Override
    protected RevocationReasonCode createDefault() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.CERTIFICATE_SUPERSEDED);
    }

    @Override
    protected RevocationReasonCode createVariant() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE);
    }
}
