package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ValidationAuthorityType JSON Serialization")
class ValidationAuthorityTypeJsonTest extends AbstractJsonSerializationSuite<ValidationAuthorityType> {
    @Override
    protected Class<ValidationAuthorityType> type() {
        return ValidationAuthorityType.class;
    }

    @Override
    protected ValidationAuthorityType createDefault() {
        return new ValidationAuthorityType(ValidationAuthorityType.Standard.UNSPECIFIED);
    }

    @Override
    protected ValidationAuthorityType createVariant() {
        return new ValidationAuthorityType(ValidationAuthorityType.Standard.NIST_CMVP);
    }
}
