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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.GetConstraintsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetConstraintsOpRequestPayload Ttlv Serialization Tests")
class GetConstraintsOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<GetConstraintsOpRequestPayload> {

    @Override
    public Class<GetConstraintsOpRequestPayload> type() {
        return GetConstraintsOpRequestPayload.class;
    }

    @Override
    public GetConstraintsOpRequestPayload createDefault() {
        return GetConstraintsOpRequestPayload.builder().build();
    }

    @Override
    public GetConstraintsOpRequestPayload createVariant() {
        return GetConstraintsOpRequestPayload.builder().build();
    }
}