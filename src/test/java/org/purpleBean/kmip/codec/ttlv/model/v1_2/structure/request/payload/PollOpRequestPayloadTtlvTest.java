package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PollOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PollOpRequestPayload Ttlv Serialization Tests")
class PollOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<PollOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<PollOpRequestPayload> type() {
        return PollOpRequestPayload.class;
    }

    @Override
    protected PollOpRequestPayload createDefault() {
        return PollOpRequestPayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    protected PollOpRequestPayload createVariant() {
        return PollOpRequestPayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{4, 5, 6}))
                .build();
    }
}
