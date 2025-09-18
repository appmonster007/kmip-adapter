package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ValidityIndicator XML Serialization")
class ValidityIndicatorXmlTest extends AbstractXmlSerializationSuite<ValidityIndicator> {
    @Override
    protected Class<ValidityIndicator> type() {
        return ValidityIndicator.class;
    }

    @Override
    protected ValidityIndicator createDefault() {
        return new ValidityIndicator(ValidityIndicator.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ValidityIndicator createVariant() {
        return new ValidityIndicator(ValidityIndicator.Standard.PLACEHOLDER_2);
    }
}
