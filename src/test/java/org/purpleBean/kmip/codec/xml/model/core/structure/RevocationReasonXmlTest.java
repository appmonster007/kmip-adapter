package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.RevocationMessage;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RevocationReason Xml Serialization Tests")
class RevocationReasonXmlTest extends AbstractXmlSerializationTestSuite<RevocationReason> {

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
