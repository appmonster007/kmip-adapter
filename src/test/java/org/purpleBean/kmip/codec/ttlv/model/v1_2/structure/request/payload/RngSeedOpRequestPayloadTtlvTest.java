package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngSeedOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RngSeedOpRequestPayload Ttlv Serialization Tests")
class RngSeedOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<RngSeedOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RngSeedOpRequestPayload> type() {
        return RngSeedOpRequestPayload.class;
    }

    @Override
    protected RngSeedOpRequestPayload createDefault() {
        return RngSeedOpRequestPayload.builder()
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    protected RngSeedOpRequestPayload createVariant() {
        return RngSeedOpRequestPayload.builder()
                .data(DataByteString.of(new byte[]{4, 5, 6}))
                .build();
    }
}
