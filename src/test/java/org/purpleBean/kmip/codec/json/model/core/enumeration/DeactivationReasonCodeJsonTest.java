package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeactivationReasonCode JSON Serialization")
class DeactivationReasonCodeJsonTest extends AbstractJsonSerializationTestSuite<DeactivationReasonCode> {
    @Override
    protected Class<DeactivationReasonCode> type() {
        return DeactivationReasonCode.class;
    }

    @Override
    protected DeactivationReasonCode createDefault() {
        return DeactivationReasonCode.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected DeactivationReasonCode createVariant() {
        return DeactivationReasonCode.Standard.DEACTIVATION_DATE.inst();
    }
}
