package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ValidityIndicator JSON Serialization")
class ValidityIndicatorJsonTest extends AbstractJsonSerializationSuite<ValidityIndicator> {
    @Override
    protected Class<ValidityIndicator> type() {
        return ValidityIndicator.class;
    }

    @Override
    protected ValidityIndicator createDefault() {
        return new ValidityIndicator(ValidityIndicator.Standard.VALID);
    }

    @Override
    protected ValidityIndicator createVariant() {
        return new ValidityIndicator(ValidityIndicator.Standard.INVALID);
    }
}
