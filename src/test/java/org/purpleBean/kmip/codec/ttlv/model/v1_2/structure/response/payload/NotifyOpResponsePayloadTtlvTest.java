package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.NotifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NotifyOpResponsePayload Ttlv Serialization Tests")
class NotifyOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<NotifyOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<NotifyOpResponsePayload> type() {
        return NotifyOpResponsePayload.class;
    }

    @Override
    protected NotifyOpResponsePayload createDefault() {
        return NotifyOpResponsePayload.builder()
                .build();
    }

    @Override
    protected NotifyOpResponsePayload createVariant() {
        return NotifyOpResponsePayload.builder()
                .build();
    }
}
