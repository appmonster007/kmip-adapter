package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("DeactivationReasonCode TTLV Serialization")
class DeactivationReasonCodeTtlvTest extends AbstractTtlvSerializationSuite<DeactivationReasonCode> {
    @Override
    protected Class<DeactivationReasonCode> type() {
        return DeactivationReasonCode.class;
    }

    @Override
    protected DeactivationReasonCode createDefault() {
        return new DeactivationReasonCode(DeactivationReasonCode.Standard.UNSPECIFIED);
    }

    @Override
    protected DeactivationReasonCode createVariant() {
        return new DeactivationReasonCode(DeactivationReasonCode.Standard.DEACTIVATION_DATE);
    }
}
