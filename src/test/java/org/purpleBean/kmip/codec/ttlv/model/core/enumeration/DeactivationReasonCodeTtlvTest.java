package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivationReasonCode TTLV Serialization")
class DeactivationReasonCodeTtlvTest extends AbstractTtlvSerializationTestSuite<DeactivationReasonCode> {
    @Override
    public Class<DeactivationReasonCode> type() {
        return DeactivationReasonCode.class;
    }

    @Override
    public DeactivationReasonCode createDefault() {
        return DeactivationReasonCode.Standard.UNSPECIFIED.inst();
    }

    @Override
    public DeactivationReasonCode createVariant() {
        return DeactivationReasonCode.Standard.DEACTIVATION_DATE.inst();
    }
}
