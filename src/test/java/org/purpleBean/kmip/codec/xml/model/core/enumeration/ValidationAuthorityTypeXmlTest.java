package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationAuthorityType XML Serialization")
class ValidationAuthorityTypeXmlTest extends AbstractXmlSerializationTestSuite<ValidationAuthorityType> {
    @Override
    protected Class<ValidationAuthorityType> type() {
        return ValidationAuthorityType.class;
    }

    @Override
    protected ValidationAuthorityType createDefault() {
        return ValidationAuthorityType.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected ValidationAuthorityType createVariant() {
        return ValidationAuthorityType.Standard.NIST_CMVP.inst();
    }
}
