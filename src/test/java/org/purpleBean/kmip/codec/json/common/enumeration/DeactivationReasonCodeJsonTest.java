package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DeactivationReasonCode JSON Serialization")
class DeactivationReasonCodeJsonTest extends AbstractJsonSerializationSuite<DeactivationReasonCode> {
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
