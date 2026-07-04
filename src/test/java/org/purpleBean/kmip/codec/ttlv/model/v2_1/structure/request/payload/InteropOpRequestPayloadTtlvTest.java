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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.InteropOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InteropOpRequestPayload Ttlv Serialization Tests")
class InteropOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<InteropOpRequestPayload> {

    @Override
    public Class<InteropOpRequestPayload> type() {
        return InteropOpRequestPayload.class;
    }

    @Override
    public InteropOpRequestPayload createDefault() {
        return InteropOpRequestPayload.builder()
                .interopFunction(InteropFunction.Standard.BEGIN.inst())
                .build();
    }

    @Override
    public InteropOpRequestPayload createVariant() {
        return InteropOpRequestPayload.builder()
                .interopFunction(InteropFunction.Standard.BEGIN.inst())
                .build();
    }
}