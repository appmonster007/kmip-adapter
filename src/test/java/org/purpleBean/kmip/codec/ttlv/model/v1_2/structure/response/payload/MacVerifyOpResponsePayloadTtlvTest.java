package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacVerifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacVerifyOpResponsePayload Ttlv Serialization Tests")
class MacVerifyOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<MacVerifyOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<MacVerifyOpResponsePayload> type() {
        return MacVerifyOpResponsePayload.class;
    }

    @Override
    protected MacVerifyOpResponsePayload createDefault() {
        return MacVerifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
                .build();
    }

    @Override
    protected MacVerifyOpResponsePayload createVariant() {
        return MacVerifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.INVALID))
                .build();
    }
}
