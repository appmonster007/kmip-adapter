package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ValidationAuthorityType TTLV Serialization")
class ValidationAuthorityTypeTtlvTest extends AbstractTtlvSerializationSuite<ValidationAuthorityType> {
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
