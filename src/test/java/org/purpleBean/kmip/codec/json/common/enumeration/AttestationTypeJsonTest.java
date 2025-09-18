package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AttestationType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttestationType JSON Serialization")
class AttestationTypeJsonTest extends AbstractJsonSerializationSuite<AttestationType> {
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
