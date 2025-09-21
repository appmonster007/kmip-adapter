package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidationType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ValidationType XML Serialization")
class ValidationTypeXmlTest extends AbstractXmlSerializationSuite<ValidationType> {
    @Override
    protected Class<ValidationType> type() {
        return ValidationType.class;
    }

    @Override
    protected ValidationType createDefault() {
        return new ValidationType(ValidationType.Standard.UNSPECIFIED);
    }

    @Override
    protected ValidationType createVariant() {
        return new ValidationType(ValidationType.Standard.HARDWARE);
    }
}
