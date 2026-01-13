package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidationType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationType JSON Serialization")
class ValidationTypeJsonTest extends AbstractJsonSerializationTestSuite<ValidationType> {
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
