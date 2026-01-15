package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidationType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationType XML Serialization")
class ValidationTypeXmlTest extends AbstractXmlSerializationTestSuite<ValidationType> {
    @Override
    protected Class<ValidationType> type() {
        return ValidationType.class;
    }

    @Override
    protected ValidationType createDefault() {
        return ValidationType.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected ValidationType createVariant() {
        return ValidationType.Standard.HARDWARE.inst();
    }
}
