package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidityIndicator JSON Serialization")
class ValidityIndicatorJsonTest extends AbstractJsonSerializationTestSuite<ValidityIndicator> {
    @Override
    protected Class<ValidityIndicator> type() {
        return ValidityIndicator.class;
    }

    @Override
    protected ValidityIndicator createDefault() {
        return ValidityIndicator.Standard.VALID.inst();
    }

    @Override
    protected ValidityIndicator createVariant() {
        return ValidityIndicator.Standard.INVALID.inst();
    }
}
