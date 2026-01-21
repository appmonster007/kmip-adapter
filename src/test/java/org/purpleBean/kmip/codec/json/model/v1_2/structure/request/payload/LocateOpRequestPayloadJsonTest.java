package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.LocateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LocateOpRequestPayload Json Serialization Tests")
class LocateOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<LocateOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<LocateOpRequestPayload> type() {
        return LocateOpRequestPayload.class;
    }

    @Override
    protected LocateOpRequestPayload createDefault() {
        return LocateOpRequestPayload.builder().build();
    }

    @Override
    protected LocateOpRequestPayload createVariant() {
        return LocateOpRequestPayload.builder().build();
    }
}
