package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.MacOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacOpRequestPayload Json Serialization Tests")
class MacOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<MacOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<MacOpRequestPayload> type() {
        return MacOpRequestPayload.class;
    }

    @Override
    protected MacOpRequestPayload createDefault() {
        return MacOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    protected MacOpRequestPayload createVariant() {
        return MacOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .data(DataByteString.of(new byte[]{4, 5, 6}))
                .build();
    }
}
