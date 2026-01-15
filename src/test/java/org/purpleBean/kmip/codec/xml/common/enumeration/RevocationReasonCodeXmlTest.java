package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RevocationReasonCode XML Serialization")
class RevocationReasonCodeXmlTest extends AbstractXmlSerializationTestSuite<RevocationReasonCode> {
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
