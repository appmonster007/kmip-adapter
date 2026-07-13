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
    public Class<JoinSplitKeyOpRequestPayload> type() {
        return JoinSplitKeyOpRequestPayload.class;
    }

    @Override
    public JoinSplitKeyOpRequestPayload createDefault() {
        return JoinSplitKeyOpRequestPayload.builder().build();
    }

    @Override
    public JoinSplitKeyOpRequestPayload createVariant() {
        return JoinSplitKeyOpRequestPayload.builder().build();
    }
}