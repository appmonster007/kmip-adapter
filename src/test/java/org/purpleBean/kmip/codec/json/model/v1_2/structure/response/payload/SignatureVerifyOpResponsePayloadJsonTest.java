package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.SignatureVerifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SignatureVerifyOpResponsePayload Json Serialization Tests")
class SignatureVerifyOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<SignatureVerifyOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<SignatureVerifyOpResponsePayload> type() {
        return SignatureVerifyOpResponsePayload.class;
    }

    @Override
    public SignatureVerifyOpResponsePayload createDefault() {
        return SignatureVerifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    public SignatureVerifyOpResponsePayload createVariant() {
        return SignatureVerifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.INVALID))
                .data(DataByteString.of(new byte[]{4, 5, 6}))
                .build();
    }
}
