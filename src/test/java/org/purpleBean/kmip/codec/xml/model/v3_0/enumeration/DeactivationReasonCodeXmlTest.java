package org.purpleBean.kmip.codec.xml.model.v3_0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeactivationReasonCode XML Serialization")
class DeactivationReasonCodeXmlTest extends AbstractXmlSerializationTestSuite<DeactivationReasonCode> {
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
