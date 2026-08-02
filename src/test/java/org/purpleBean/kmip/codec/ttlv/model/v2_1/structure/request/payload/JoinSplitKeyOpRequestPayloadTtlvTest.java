package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.JoinSplitKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("JoinSplitKeyOpRequestPayload Ttlv Serialization Tests")
class JoinSplitKeyOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<JoinSplitKeyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
    }

    @Override
    public Class<JoinSplitKeyOpRequestPayload> type() {
        return JoinSplitKeyOpRequestPayload.class;
    }

    @Override
    public JoinSplitKeyOpRequestPayload createDefault() {
        return JoinSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("part-1").build())
                .uniqueIdentifier(UniqueIdentifier.builder().value("part-2").build())
                .build();
    }

    @Override
    public JoinSplitKeyOpRequestPayload createVariant() {
        return JoinSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("part-3").build())
                .uniqueIdentifier(UniqueIdentifier.builder().value("part-4").build())
                .build();
    }
}