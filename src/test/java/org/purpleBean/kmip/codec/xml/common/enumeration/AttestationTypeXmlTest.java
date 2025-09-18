package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AttestationType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttestationType XML Serialization")
class AttestationTypeXmlTest extends AbstractXmlSerializationSuite<AttestationType> {
    @Override
    protected Class<AttestationType> type() {
        return AttestationType.class;
    }

    @Override
    protected AttestationType createDefault() {
        return new AttestationType(AttestationType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected AttestationType createVariant() {
        return new AttestationType(AttestationType.Standard.PLACEHOLDER_2);
    }
}
