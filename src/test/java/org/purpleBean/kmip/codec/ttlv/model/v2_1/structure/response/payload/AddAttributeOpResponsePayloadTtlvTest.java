package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.AddAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AddAttributeOpResponsePayload Ttlv Serialization Tests")
class AddAttributeOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<AddAttributeOpResponsePayload> {

    @Override
    public Class<AddAttributeOpResponsePayload> type() {
        return AddAttributeOpResponsePayload.class;
    }

    @Override
    public AddAttributeOpResponsePayload createDefault() {
        return AddAttributeOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }

    @Override
    public AddAttributeOpResponsePayload createVariant() {
        return AddAttributeOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("other-uid")).build();
    }
}