package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RevocationReasonCode TTLV Serialization")
class RevocationReasonCodeTtlvTest extends AbstractTtlvSerializationTestSuite<RevocationReasonCode> {
    @Override
    protected Class<RevocationReasonCode> type() {
        return RevocationReasonCode.class;
    }

    @Override
    protected RevocationReasonCode createDefault() {
        return RevocationReasonCode.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected RevocationReasonCode createVariant() {
        return RevocationReasonCode.Standard.KEY_COMPROMISE.inst();
    }
}
