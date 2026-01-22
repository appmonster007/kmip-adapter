package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CancelOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CancelOpRequestPayload Ttlv Serialization Tests")
class CancelOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<CancelOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CancelOpRequestPayload> type() {
        return CancelOpRequestPayload.class;
    }

    @Override
    protected CancelOpRequestPayload createDefault() {
        return CancelOpRequestPayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    protected CancelOpRequestPayload createVariant() {
        return CancelOpRequestPayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{4, 5, 6}))
                .build();
    }
}
