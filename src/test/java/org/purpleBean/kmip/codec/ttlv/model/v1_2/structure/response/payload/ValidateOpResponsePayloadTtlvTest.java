package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ValidateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidateOpResponsePayload Ttlv Serialization Tests")
class ValidateOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ValidateOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<ValidateOpResponsePayload> type() {
        return ValidateOpResponsePayload.class;
    }

    @Override
    public ValidateOpResponsePayload createDefault() {
        return ValidateOpResponsePayload.builder()
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
                .build();
    }

    @Override
    public ValidateOpResponsePayload createVariant() {
        return ValidateOpResponsePayload.builder()
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.INVALID))
                .build();
    }
}
