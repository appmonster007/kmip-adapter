package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.JoinSplitKeyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("JoinSplitKeyOpResponsePayload Json Serialization Tests")
class JoinSplitKeyOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<JoinSplitKeyOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<JoinSplitKeyOpResponsePayload> type() {
        return JoinSplitKeyOpResponsePayload.class;
    }

    @Override
    protected JoinSplitKeyOpResponsePayload createDefault() {
        return JoinSplitKeyOpResponsePayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
    }

    @Override
    protected JoinSplitKeyOpResponsePayload createVariant() {
        return JoinSplitKeyOpResponsePayload.builder()
                .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .build();
    }
}
