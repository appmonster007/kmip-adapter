package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.PutOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PutOpResponsePayload Ttlv Serialization Tests")
class PutOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<PutOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<PutOpResponsePayload> type() {
        return PutOpResponsePayload.class;
    }

    @Override
    protected PutOpResponsePayload createDefault() {
        return PutOpResponsePayload.builder()
                .build();
    }

    @Override
    protected PutOpResponsePayload createVariant() {
        return PutOpResponsePayload.builder()
                .build();
    }
}
