package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngRetrieveOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RngRetrieveOpRequestPayload Json Serialization Tests")
class RngRetrieveOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<RngRetrieveOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RngRetrieveOpRequestPayload> type() {
        return RngRetrieveOpRequestPayload.class;
    }

    @Override
    protected RngRetrieveOpRequestPayload createDefault() {
        return RngRetrieveOpRequestPayload.builder()
                .dataLength(DataLength.of(16))
                .build();
    }

    @Override
    protected RngRetrieveOpRequestPayload createVariant() {
        return RngRetrieveOpRequestPayload.builder()
                .dataLength(DataLength.of(32))
                .build();
    }
}
