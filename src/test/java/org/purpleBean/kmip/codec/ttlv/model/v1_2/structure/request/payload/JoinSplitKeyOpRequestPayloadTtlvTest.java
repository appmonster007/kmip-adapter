package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.JoinSplitKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("JoinSplitKeyOpRequestPayload Ttlv Serialization Tests")
class JoinSplitKeyOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<JoinSplitKeyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<JoinSplitKeyOpRequestPayload> type() {
        return JoinSplitKeyOpRequestPayload.class;
    }

    @Override
    protected JoinSplitKeyOpRequestPayload createDefault() {
        return JoinSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .build();
    }

    @Override
    protected JoinSplitKeyOpRequestPayload createVariant() {
        return JoinSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174002"))
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174003"))
                .build();
    }
}
